package Main;

import CustomerOrderInvoice.CustomerOrder;
import CustomerOrderInvoice.CustomerOrderArray;
import GUI.ItemPurchaseOrderStore;
import PurchaseOrder.getPurchaseOrders;
import ItemProfile.ItemProfile;
import Login.LoginMenu;
import ProfileUsers.*;
import PurchaseOrder.PurchaseOrder;
import UserClasses.GetUsersFromCSV;
import ItemProfile.getItems;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

public class Main
{
    //Final touches hehe xd
    public static ArrayList<ItemProfile> items = new ArrayList<>();
    public static ArrayList<ItemPurchaseOrderStore> ItemPurchaseOrderStorage = new ArrayList<>();
    public static ArrayList<PurchaseOrder> PurchaseOrders = new ArrayList<>();

    public static void main(java.lang.String[] args) throws IOException, ParseException
    {
        items = getItems.get_items();
        PurchaseOrders = getPurchaseOrders.get_orders();

        //Enter fake values for customer to work with and fake customer orders
        try {
            Customer c1 =  new Customer( 0,  "Harry",  "321 drive",  "Chicago",  "IL", "312-565-4949"
                    ,12000,25.5,"12/7/2022");
            Customer c2 = new Customer( 1,  "Joe",  "123 Street",  "Detroit",  "MI", "313-151-1515"
                    ,50000,55.5,"12/2/2022");
            CustomerArray.addCustomer(c1);
            CustomerArray.addCustomer(c2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String custitems[] = new String[5];
        int quantity[] = new int[5];
        int quantity2[] = new int[5];
        Random rn = new Random();
        for(int i=0;i<5;i++){
            custitems[i] = items.get(i).getItemName();
            quantity[i] = rn.nextInt((9+1))+1;
        }
        CustomerOrder co1 = new CustomerOrder(0, "Harry", 0.0,"12/9/2022","12/7/2022",quantity,custitems);
        co1.setTotal();
        CustomerOrderArray.addCustomerOrder(co1);
        for(int i=0;i<5;i++){
            custitems[i] = items.get(i).getItemName();
            quantity2[i] = rn.nextInt((9+1))+1;
        }
        CustomerOrder co2 = new CustomerOrder(1,"Joe", 0.0,"12/9/2022","12/7/2022",quantity2,custitems);
        co2.setTotal();
        CustomerOrderArray.addCustomerOrder(co2);
        GetUsersFromCSV.getUsersFromCSV();
        VendorAccountArray.init();
        //new PurchaserView();
        //new AccountantView();
        //UserAccountArray.testAccount();
        new LoginMenu();
    }
}
