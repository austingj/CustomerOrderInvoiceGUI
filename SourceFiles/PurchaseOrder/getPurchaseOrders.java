package PurchaseOrder;

import ItemProfile.ItemProfile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static ProfileUsers.VendorAccountArray.vendors;

/*
@author Andrew James
 */
public class getPurchaseOrders extends PurchaseOrder{

    public static ArrayList<PurchaseOrder> get_orders() throws IOException {

        java.lang.String line = "";
        ArrayList<PurchaseOrder> orders = new ArrayList<>(); //arraylist of items that we will store items from CSV in
        BufferedReader reader2;
        BufferedReader reader3;
        try {
            reader2 = new BufferedReader(new FileReader(ItemProfile.PURCHASE_ORDERS_CSV));
            reader2.readLine();
            while ((line = reader2.readLine()) != null) {
                java.lang.String[] values = line.split(","); // splits the line at the commas and then stores each value in an array of Strings.
                PurchaseOrder order = new PurchaseOrder(); // creating new itemProfile
                order.createPurchaseOrder(values[0],values[1],Double.parseDouble(values[2]),values[3]);
                //line above takes the CSV values and creates an order.
                orders.add(order); //add newly created item to arraylist
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        reader3 = new BufferedReader(new FileReader(PurchaseOrder.PURCHASE_ORDERS_ITEMS_CSV));
        while ((line = reader3.readLine()) != null) {
            java.lang.String[] values = line.split(",");
            for(PurchaseOrder order: orders){
                if(values[1].matches(order.getPurchaseOrderID())){
                    order.addPurchaseItems(values[0]);
                }
            }
        }

        //updating vendor balances stored in the CSV for their purchase orders
        for(PurchaseOrder order: orders){

            for(int i = 0; i < vendors.length; i++){
                if(order.getVendorID().matches(Integer.toString(vendors[i].getUserID()))){
                    vendors[i].addToBalance(order.getTotalCost());
                }
            }
        }

        return orders; //return the created arraylist
    }

}


