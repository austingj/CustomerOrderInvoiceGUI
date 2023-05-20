package ItemProfile;

/*
@author: Andrew James
 */


public class ItemProfileCSV extends ItemProfile {

    public static java.lang.String CSVFormatter(ItemProfile item){

        java.lang.String CSVFormat = item.itemID + "," + item.getItemName() + "," + item.getVendorID() + "," + item.getSellingPrice() + "," + item.getItemCategory() + "," + item.getQuantityonHand()
                + "," + item.getUnitOfMeasurement() + "," + item.getExpireDateString() + "," + item.getPurchasePrice();

        //Formatter for CSV output.

        return CSVFormat;
    }


}
