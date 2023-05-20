package ProfileUsers;


import java.util.Date;
/*
class for abstract Profile
@author Austin Jeffery
 */
public abstract class Profile
{
    int userID;
    String fullName;
    String streetAddress;
    String city;
    String state;
    String phone;
    double balance;
    double lastPaidAmount;
    Date lastOrderDate;//mm/d/yyyy


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getLastPaidAmount() {
        return lastPaidAmount;
    }

    public void setLastPaidAmount(double lastPaidAmount) {
        this.lastPaidAmount = lastPaidAmount;
    }

    public Date getLastOrderDate() {
        return lastOrderDate;
    }

    public void setLastOrderDate(Date lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }



    public String print(){
        String output = ("ID: " + this.getUserID() + " Name: " + this.getFullName() + " State: " + this.getState() + " City: " + this.getCity()
                + " Street: " + this.getStreetAddress() + " Phone: " + this.getPhone() + " Balance: " + this.getBalance());
        return output;
    }

}
