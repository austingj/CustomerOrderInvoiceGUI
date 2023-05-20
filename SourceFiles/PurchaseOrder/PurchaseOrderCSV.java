package PurchaseOrder;

/*
@author Andrew James
 */
public class PurchaseOrderCSV extends PurchaseOrder{
    public static java.lang.String CSVFormatter(PurchaseOrder order){

        java.lang.String CSVFormat = order.getPurchaseOrderID() + "," + order.getVendorID() + "," + order.getTotalCost() + "," + order.getNeedByDateString();

        return CSVFormat;
    }
}
