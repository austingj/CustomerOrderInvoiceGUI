package ItemProfile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


/* It"s important to make sure you are using the correct file path. Make sure you are defining it as Resources/<file name>.
   The Resources folder is marked as the Resource dirc so the project will read files from there without needing to put entire filepath.
* */


/*
@author: Andrew James
 */
public class getItems extends ItemProfile {
        public static ArrayList<ItemProfile> get_items() throws IOException {

            java.lang.String line = "";
            ArrayList<ItemProfile> items = new ArrayList<>(); //arraylist of items that we will store items from CSV in
            BufferedReader reader2;
            BufferedReader reader3;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(ItemProfile.RESOURCES_ITEMS_CSV));
                reader2 = new BufferedReader(new FileReader(ItemProfile.PURCHASE_ORDERS_CSV));
                reader3 = new BufferedReader(new FileReader(ItemProfile.INVOICE_CSV));
                reader.readLine(); // skips header line of CSV
                reader2.readLine();
                reader3.readLine();
                while ((line = reader.readLine()) != null) {
                    java.lang.String[] values = line.split(","); // splits the line at the commas and then stores each value in an array of Strings.
                    ItemProfile item = new ItemProfile(); // creating new itemProfile
                    item.createItem(values[0], values[1], values[2], Double.parseDouble(values[3]), values[4], Double.parseDouble(values[5]), values[6], values[7], Double.parseDouble(values[8]));
                    //line above takes the CSV values and creates an item.
                    items.add(item); //add newly created item to arraylist
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            while ((line = reader2.readLine()) != null) {

                java.lang.String[] values = line.split(",");

                for(ItemProfile item: items){
                    if(values[0].matches(item.getItemID())){
                        item.addpurchaseOrder(values[1]);
                    }
                }
            }
            while ((line = reader3.readLine()) != null) {

                java.lang.String[] values = line.split(",");

                for(ItemProfile item: items){

                    if(values[0].matches(item.getItemID())){
                        item.addInvoice(values[1]);
                    }
                }
            }
            return items; //return the created arraylist
        }

}
