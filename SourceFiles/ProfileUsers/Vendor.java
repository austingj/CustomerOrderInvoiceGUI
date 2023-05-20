package ProfileUsers;

import ObserverInterface.ObserveVendorSale;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
/*
Class for Vendor
@author Austin

 */
public class Vendor extends Profile{

    // Observer Variables
    public ArrayList<ObserveVendorSale> saleObservers = new ArrayList<>();
    public boolean hasNotUpdated = true;
    private Date seasonalDiscountsStartDate; //mm/dd/yyyy
    public static final String RESOURCES_ITEMS_CSV = "Resources/vendors.csv";
    public static final String CSVHeaderLine = "userID,fullName,streetAddress,city,state,phone,balance,lastPaidAmount,lastOrderDate,seasonalDiscountsStartDate";
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    public Vendor(){
        this.userID = 0;
        this.fullName = "";
        this.streetAddress = "";
        this.city = "";
        this.state = "";
        this.phone = "";
        this.balance = 0;
        this.lastPaidAmount = 0;
        this.seasonalDiscountsStartDate = null;
        this.lastOrderDate = null;
    }


    public Vendor(int userID, String fullName, String streetAddress, String city, String state, String phone) {
        this.userID = userID; //auto generated max 6 character
        this.fullName = fullName;//must be unique
        this.streetAddress = streetAddress;//must be unique
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.balance = 0;
        this.lastPaidAmount = 0;
    }

    public Vendor(int num, String fullname, String street, String city, String state, String phone, Date lastorder, Date seasonal) {
        this.userID = num; //auto generated max 6 character
        this.fullName = fullname;//must be unique
        this.streetAddress = street;//must be unique
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.balance = 0;
        this.lastPaidAmount = 0;
        this.lastOrderDate = lastorder;
        this.seasonalDiscountsStartDate = seasonal;
    }

    public Vendor(int parseInt, String value, String value1, String value2, String value3, String value4, double parseDouble, double value5, String value6, String value7) throws ParseException {
        this.userID = parseInt; //auto generated max 6 character
        this.fullName = value;//must be unique
        this.streetAddress = value1;//must be unique
        this.city = value2;
        this.state = value3;
        this.phone = value4;
        this.balance = parseDouble;
        this.lastPaidAmount = value5;
        this.lastOrderDate = formatter.parse(value6);
        this.seasonalDiscountsStartDate = formatter.parse(value7);
    }

    public String toString(){
        return print() + "";
    }

    public Date getSeasonalDiscount() {
        return seasonalDiscountsStartDate;
    }

    public void setSeasonalDiscount(Date seasonalDiscount) {
        this.seasonalDiscountsStartDate = seasonalDiscount;
    }

    public void addToBalance(Double balance){this.balance+=balance;}

    // Add Observer for sales to array
    public void registerSaleObserver(ObserveVendorSale saleObserver)
    {
        saleObservers.add(saleObserver);
    }

    // Delete Observer for sales from array
    public void removeSaleObserver(ObserveVendorSale saleObserver)
    {
        // Gets the index of the observer, then removes them
        int observerToDelete = saleObservers.indexOf(saleObserver);
        saleObservers.remove(observerToDelete);
    }

    // Updates all users within the observer array
    public void updateSaleObservers()
    {
        // Updates all needed observers
        for (ObserveVendorSale observeVendorSale: saleObservers)
        {
            // If the vendor has NOT sent out an update yet
            if (hasNotUpdated)
            {
                hasNotUpdated = false;
                observeVendorSale.update(this.fullName);
            }
        }
    }

    public void setAttributes(int num, String fullname, String street, String city, String state, String phone, Date lastorder, Date seasonal) {
        this.userID = num; //auto generated max 6 character
        this.fullName = fullname;//must be unique
        this.streetAddress = street;//must be unique
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.balance = 0;
        this.lastPaidAmount = 0;
        this.lastOrderDate = lastorder;
        this.seasonalDiscountsStartDate = seasonal;
    }


}
