package GUI;

/*
@author Andrew James
 */
public class ItemPurchaseOrderStore
{

    //This class is used as temp storage for creating purchaseorders
    String itemID;
    double amountPurchased;
    double balance;
    String purchaseID;

    public void setItemID(String itemID){this.itemID=itemID;}
    public void setAmountPurchased (double amountPurchased){this.amountPurchased=amountPurchased;}
    public void setBalance(double balance){this.balance=balance;}
    public void setpurchaseID(String purchaseID){this.purchaseID=purchaseID;}

    public double getBalance(){return balance;}
    public String getItemID(){return itemID;}
    public double getamountPurchased(){return amountPurchased;}
    public String getpurchaseID(){return purchaseID;}


}
