package PurchaseOrder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
@author Andrew James
 */
public class PurchaseOrder
{
    public static final java.lang.String PURCHASE_ORDERS_CSV = "Resources/purchaseorders.csv";
    public static final java.lang.String PURCHASE_ORDERS_ITEMS_CSV = "Resources/purchaserordersitem.csv";
    public static final java.lang.String PURCHASE_ORDER_CSV_HEADER = "purchaseOrderID,vendorID,balance,needByDate";
    public static final java.lang.String PURCHASE_ORDER_ITEMS_CSV_HEADER = "itemID,purchaseOrderID";

    java.lang.String purchaseOrderID;
    double totalCost;
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    java.lang.String vendorID;
    Date needByDate;
    ArrayList<String> purchasedItems = new ArrayList<>();
    ArrayList<Double> costOfItems = new  ArrayList<>();
    void setPurchaseOrderID(java.lang.String purchaseOrderID){this.purchaseOrderID = purchaseOrderID;}
    public void setTotalCost(double totalCost){this.totalCost = totalCost;}
    void setNeedByDate(Date needByDate){this.needByDate = needByDate;}
    public void addPurchaseItems(String item){purchasedItems.add(item);}
    public java.lang.String getPurchaseOrderID(){return purchaseOrderID;}
    double getTotalCost(){return totalCost;}
    Date getNeedByDate(){return needByDate;}
    void setVendorID(java.lang.String vendorID){this.vendorID=vendorID;}
    public java.lang.String getVendorID(){return vendorID;}
    public ArrayList getPurchasedItems(){return purchasedItems;}
    public String getNeedByDateString(){return formatter.format(needByDate);}
    public void createPurchaseOrder(java.lang.String purchaseOrderID, java.lang.String vendorID, double balance, java.lang.String needByDate) throws ParseException {
        this.purchaseOrderID = purchaseOrderID;
        this.vendorID=vendorID;
        this.totalCost=balance;
        this.needByDate=formatter.parse(needByDate);
    }

}
