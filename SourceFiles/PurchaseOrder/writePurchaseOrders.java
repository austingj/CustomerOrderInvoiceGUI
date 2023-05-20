package PurchaseOrder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static PurchaseOrder.PurchaseOrderCSV.CSVFormatter;

/*
@author Andrew James
 */

public class writePurchaseOrders extends PurchaseOrder{

    public static void write_items(ArrayList<PurchaseOrder> orders){

        try (FileWriter writer = new FileWriter(PurchaseOrder.PURCHASE_ORDERS_CSV, false)) //overwrites the .CSV with the new values
        {
            writer.write(PurchaseOrder.PURCHASE_ORDER_CSV_HEADER); //writes the header line to the CSV first

            for (PurchaseOrder order : orders) //iterates over arraylist and writes the formatter CSV line
            {
                writer.write('\n' + CSVFormatter(order));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer2 = new FileWriter(PurchaseOrder.PURCHASE_ORDERS_ITEMS_CSV, false)) //overwrites the .CSV with the new values
        {
            writer2.write(PurchaseOrder.PURCHASE_ORDER_ITEMS_CSV_HEADER); //writes the header line to the CSV first

            /*this one was a bit tricky. Involved creating a seperate "storage class". We essentially have two CSV
            We have two CSVs and map the ItemID to the PurchaseOrderID. Was a lot easier to do it this way.
             */
            for (PurchaseOrder order : orders)

            {
                ArrayList<String> temp;
                temp = order.getPurchasedItems();
                for (String item : temp) {
                    writer2.write('\n' + item + "," +order.getPurchaseOrderID());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
