package ItemProfile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/*
@author: Andrew James
 */

public class writeCSV extends ItemProfile {
    public static void write_items(ArrayList<ItemProfile> items){

            try (FileWriter writer = new FileWriter(ItemProfile.RESOURCES_ITEMS_CSV, false)) //overwrites the .CSV with the new values
            {
                writer.write(ItemProfile.ITEMS_CSV_HEADER); //writes the header line to the CSV first

                for (ItemProfile item : items) //iterates over arraylist and writes the formatter CSV line
                {
                    writer.write('\n' + ItemProfileCSV.CSVFormatter(item));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}

