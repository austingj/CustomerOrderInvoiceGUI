package ProfileUsers;

import UserClasses.Owner;
import UserClasses.User;
import UserClasses.UserAccountArray;

import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/*
Class for Array of vendors to be used across the project
@author Austin Jeffery

Observer code added by:
@Benjamin Pienta
 */
public class VendorAccountArray {
    public static int arraySize = 0;
    public static Vendor[] vendors = new Vendor[arraySize];
    public static void init(){
        try {
            String line = "";
            BufferedReader reader = new BufferedReader(new FileReader(Vendor.RESOURCES_ITEMS_CSV));
            reader.readLine(); // skips header line of CSV
            while ((line = reader.readLine()) != null)
            {
                String[] values = line.split(","); // splits the line at the commas and then stores each value in an array of Strings.
                Vendor item = new Vendor(Integer.parseInt(values[0]),values[1],values[2],values[3],values[4],values[5],Double.parseDouble(values[6]),Double.parseDouble(values[7]),values[8],values[9]); // creating new itemProfile

                // For loop adds all required observers to the vendor
                for (User user: UserAccountArray.getUsers())
                {
                    if (user instanceof Owner)
                    {
                        item.registerSaleObserver(user);
                    }
                }

                // Add the vendor to the array
                VendorAccountArray.addVendor(item);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(){

            try (FileWriter writer = new FileWriter(Vendor.RESOURCES_ITEMS_CSV, false)) //overwrites the .CSV with the new values
            {
                writer.write(Vendor.CSVHeaderLine); //writes the header line to the CSV first
                for (Vendor item : vendors) //iterates over arraylist and writes the formatter CSV line
                {
                    writer.write('\n' + VendorCSV.CSVFormatter(item));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void print() {
        for(int i = 0; i <arraySize; i++){
            System.out.println(vendors[i].getFullName());
        }

    }

    public int getLength()
    {
        return arraySize;
    }
    public static void updateVendor(Vendor vendor,int i){
        vendors[i] = vendor;
    }
    public static void addVendor(Vendor vendor)
    {
        increaseArraySize();
        vendors[arraySize - 1] = vendor;
    }
    public static void deleteVendor(Vendor vendor)
    {
        Vendor[] temporaryHold = vendors;
        arraySize= arraySize-1;
        Vendor[] copy = new Vendor[arraySize];
        int index = vendor.getUserID();
        int k = 0;
        for(int i = 0; i < arraySize+1; i++){
            if(i == index){
                continue;
            }
            copy[k++] = temporaryHold[i];
        }
        vendors = new Vendor[arraySize];
        vendors = copy;
        for(int i= 0;i<arraySize;i++){

            vendors[i].setUserID(i);
           // System.out.println(vendors[i].getFullName() + " id: " + vendors[i].getUserID());
            }
    }


    private static void increaseArraySize()
    {
        Vendor[] temporaryHold = vendors;

        arraySize += 1;
        vendors = new Vendor[arraySize];
        for (int i = 0; i < arraySize - 1; i++)
        {
            vendors[i] = temporaryHold[i];
        }
    }

    public static Vendor searchForUser(int id)
    {
        Vendor toBeFound = null;

        for (int i = 0; i < arraySize; i++)
        {
            if (vendors[i].getUserID() == id)
            {
                toBeFound = vendors[i];
            }
        }

        return toBeFound;
    }
    public static Vendor searchForUser(String name)
    {
        Vendor toBeFound = null;

        for (int i = 0; i < arraySize; i++)
        {
            if (vendors[i].getFullName().equals(name))
            {
                toBeFound = vendors[i];
            }
        }

        return toBeFound;
    }

}
