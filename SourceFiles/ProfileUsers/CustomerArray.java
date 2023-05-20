package ProfileUsers;

import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/*
Class for Array of customers to be used across the project
@author Austin Jeffery
 */
public class CustomerArray {
    public static int arraySize = 0;
    public static Customer[] customers = new Customer[arraySize];
    public static void print() {
        for(int i = 0; i <arraySize; i++){
            System.out.println(customers[i].getFullName());
        }
    }
    public int getLength()
    {
        return arraySize;
    }
    public static void updateCustomer(Customer customer,int i){
        customers[i] = customer;
    }
    public static void addCustomer(Customer customer)
    {
        increaseArraySize();
        customers[arraySize - 1] = customer;
    }
    public static void deleteCustomer(Customer customer)
    {
        Customer[] temporaryHold = customers;
        arraySize= arraySize-1;
        Customer[] copy = new Customer[arraySize];
        int index = customer.getUserID();
        int k = 0;
        for(int i = 0; i < arraySize+1; i++){
            if(i == index){
                continue;
            }
            copy[k++] = temporaryHold[i];
        }
        customers = new Customer[arraySize];
        customers = copy;
        for(int i= 0;i<arraySize;i++){
            customers[i].setUserID(i);
        }
    }


    private static void increaseArraySize()
    {
        Customer[] temporaryHold = customers;
        arraySize += 1;
        customers = new Customer[arraySize];
        for (int i = 0; i < arraySize - 1; i++)
        {
            customers[i] = temporaryHold[i];
        }
    }

    public static Customer searchForUser(int id)
    {
        Customer toBeFound = null;
        for (int i = 0; i < arraySize; i++)
        {
            if (customers[i].getUserID() == id)
            {
                toBeFound = customers[i];
            }
        }
        return toBeFound;
    }
    public static Customer searchForUser(String name)
    {
        Customer toBeFound = null;

        for (int i = 0; i < arraySize; i++)
        {
            if (customers[i].getFullName().equals(name))
            {
                toBeFound = customers[i];
            }
        }
        return toBeFound;
    }

}
