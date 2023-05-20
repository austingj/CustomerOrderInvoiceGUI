package ItemProfile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/*
@author: Andrew James
 */

public class ItemProfile
{
     public static final String RESOURCES_ITEMS_CSV = "Resources/items.csv";
     public static final String ITEMS_CSV_HEADER = "itemID,itemName,vendorID,sellingPrice,itemCategory,quantityonHand,unitOfMeasurement,expireDate,purchasePrice";
     public static final String PURCHASE_ORDERS_CSV = "Resources/purchaseorders.csv";
     public static final String INVOICE_CSV = "Resources/invoices.csv";
     String itemID;
     String itemName;
     String vendorID;
     double sellingPrice;
     String itemCategory;
     double purchasePrice;

     String unitOfMeasurement;
     double quantityonHand;
     Date expireDate;
     DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

     ArrayList<String> purchaseOrders = new ArrayList<>();
     ArrayList<String> invoices = new ArrayList<>();

     public void setItemID(String itemID) {this.itemID = itemID;}
     public void setPurchasePrice(double purchasePrice) {this.purchasePrice = purchasePrice;}
     public void setUnitOfMeasurement(String unitOfMeasurment){this.unitOfMeasurement = unitOfMeasurment;}
     public void setItemName(String itemName) {this.itemName = itemName;}
     public void setVendorID(String vendorID) {this.vendorID = vendorID;}
     public void setSellingPrice(double sellingPrice) {this.sellingPrice = sellingPrice;}
     public void setItemCategory(String itemCategory){this.itemCategory = itemCategory;}
     public void setQuantityonHand(double quantityonHand){this.quantityonHand = quantityonHand;}
     public void setExpireDate(Date expireDate){this.expireDate = expireDate;}

     public void addpurchaseOrder(String purchaseID) {this.purchaseOrders.add(purchaseID);}
     public void addInvoice(String invoiceID) {this.invoices.add(invoiceID);}
     public String getExpireDateString()
     {
          return formatter.format(expireDate);
     }
     public Date getExpireDate (){return expireDate;}

     public double getPurchasePrice(){return purchasePrice;}

     public String getItemCategory() {
          return itemCategory;
     }

     public String getUnitOfMeasurement() {
          return unitOfMeasurement;
     }
     public double getSellingPrice() {
          return sellingPrice;
     }

     public double getQuantityonHand() {
          return quantityonHand;
     }

     public String getVendorID() {
          return vendorID;
     }

     public String getItemID() {
          return itemID;
     }

     public String getItemName() {
          return itemName;
     }

     public int getTotalInvoices() {
          return invoices.size();
     }

     public int getTotalPurchaseOrders() {
          return purchaseOrders.size();
     }



     public ArrayList getInvoices() {return invoices;}

     public ArrayList getPurchaseOrders() {return purchaseOrders;}


     public void createItem(String itemID, String itemName, String vendorID, double sellingPrice, String itemCategory, double quantityonHand, String unitOfMeasurment, String expireDate, double purchasePrice) throws ParseException {
          this.itemID = itemID;
          this.itemName = itemName;
          this.vendorID = vendorID;
          this.sellingPrice = sellingPrice;
          this.itemCategory = itemCategory;
          this.unitOfMeasurement = unitOfMeasurment;
          this.quantityonHand = quantityonHand;
          this.expireDate = formatter.parse(expireDate);
          this.purchasePrice = purchasePrice;
     } //basically sets everything at once for condensed setter functions. can maybe move this into a separate class so. might be borderline SOLID principle violation.

     public ItemProfile(){
          itemID = "";
          itemName = "test";
          vendorID = "";
          sellingPrice = 0;
          itemCategory = "";
          unitOfMeasurement = "";
          quantityonHand = 0;
          expireDate = new Date();
          purchasePrice = 0;
     }


     public String toString()
     {
          return itemID + " - " + itemName;
     }


}




