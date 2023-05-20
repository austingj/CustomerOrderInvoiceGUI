package CustomerOrderInvoice;

import ItemProfile.ItemProfile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import ItemProfile.getItems;
/*
@author Austin Jeffery

 */
public class CustomerInvoice
{
    public static int invoiceid = 0;//increase invoice id for every invoice made
    public CustomerInvoice() {

    }

    public static void setInvoiceid(int invoiceid) {
        CustomerInvoice.invoiceid = invoiceid;
    }

    public static int getInvoiceID()
    {
        return invoiceid;
    }

    public String getInvoiceDate() {
        return formatter.format(invoiceDate);
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getOrderDate() {
        return formatter.format(this.OrderDate);
    }

    public void setOrderDate(Date orderDate) {
        OrderDate = orderDate;
    }

    public void setCustOrdernumber(int custOrdernumber) {
        this.custOrdernumber = custOrdernumber;
    }

    public String getTotalinvoiceamount() {
        DecimalFormat f = new DecimalFormat("##.00");
        return "$ " + f.format(this.totalinvoiceamount);
    }

    public void setTotalinvoiceamount(double totalinvoiceamount) {
        this.totalinvoiceamount = totalinvoiceamount;
    }

    public static int getInvoiceid() {
        return invoiceid;
    }

    public int getCustOrdernumber() {
        return custOrdernumber;
    }
    public Date invoiceDate;
    private Date OrderDate;
    private int custOrdernumber;
    private double totalinvoiceamount;
    public String[] itemname;
    public int[] quantity;
    private double total;
    public static ArrayList<ItemProfile> items = new ArrayList<>();
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    public CustomerInvoice(String orderdate, int customerordernumb, String[] items, int[] quant) {
        try {
            this.OrderDate = formatter.parse(orderdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.custOrdernumber = customerordernumb;
        this.itemname = items;
        this.quantity = quant;
        this.invoiceid++;
        this.invoiceDate = new Date(System.currentTimeMillis());
    }

    public double getTotal() {
        return this.totalinvoiceamount;
    }

    public void setTotal(double total) {
        this.totalinvoiceamount = total;
    }
    //DetailsPerItem returns string output to list item name, quantity and subprice for that item
    //then lists the sales tax and total item
    public String DetailsPerItem() throws IOException {
        items = getItems.get_items();
        String details= "";
        DecimalFormat f = new DecimalFormat("##.00");
        double total =0;
        double[] subtotal = new double[5];
        for(int i = 0;i<5; i++){
            subtotal[i] = items.get(i).getSellingPrice() * this.quantity[i];
            total = total + subtotal[i];
        }
        total = (total + total*0.06);
        for(int i = 0; i <5; i++){
            details+= " " + itemname[i] + " "  + " Quantity: " + quantity[i] + " total: $" + f.format(subtotal[i]);
        }
        setTotal(total);
        return details +  " Sales tax: $" + f.format(total*0.06) + " Total: $" + f.format(total);
    }
}
