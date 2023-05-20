package CustomerOrderInvoice;

import ItemProfile.ItemProfile;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
@author Austin Jeffery
Quick Customer Order class to help with other features that use these values
 */
public class CustomerOrder {
public int id;
public String custName;
public static ArrayList<ItemProfile> items2 = new ArrayList<>();
    public Date getNeedbydate() {
        return needbydate;
    }

    public void setNeedbydate(Date needbydate) {
        this.needbydate = needbydate;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    private double total;
private Date needbydate;
private Date orderdate;
private int[] quantity = new int[5];
private String[] items = new String[5];
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    //constructor for Customer Order
    public CustomerOrder(int id, String custName, double total, String needbydate, String orderdate, int quantity[], String items[]) {
        this.id = id;
        this.custName = custName;
        this.total = total;

        try {
            this.needbydate = formatter.parse(needbydate);
            this.orderdate = formatter.parse(orderdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.quantity = quantity;
        for(int i = 0; i <5;i++){
            this.items[i] = items[i];
        }
    }

    public CustomerOrder() {
    }
    //output for item quantity
    public String itemquantity(){
        String output ="";
        for(int i = 0; i < 5; i++){
            output += " item: " + this.items[i] + " quantity: " + this.quantity[i];
        }
        return output;
    }
    //Set total with the given customer items and quantity
    public void setTotal()throws IOException {
        //set total
        items2 = getItems.get_items();
        for(int i = 0; i < 5; i++){
            this.total += items2.get(i).getSellingPrice() * this.quantity[i];
        }
    }
    //display output for Customer Order
    public String displayCustomerOrder(){
        String output ="ID: " + this.id + " total: " + this.total + " Need By Date: " + this.needbydate + " Order Date: "  + this.orderdate
                + " \n\n " + itemquantity();
        return output;
    }
}
