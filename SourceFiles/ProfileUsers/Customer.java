package ProfileUsers;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
/*
Class for Customer stub code
@author Austin

 */
public class Customer extends Profile{
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    public Customer(){
        this.userID = 0;
        this.fullName = "";
        this.streetAddress = "";
        this.city = "";
        this.state = "";
        this.phone = "";
        this.balance = 0;
        this.lastPaidAmount = 0;
        this.lastOrderDate = null;
    }

    public Customer(int id, String name, String street, String city, String state, String phone, double bal, double lastpaid,String lastorder) throws ParseException {
        this.userID = id;
        this.fullName = name;
        this.streetAddress = street;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.balance = bal;
        this.lastPaidAmount = lastpaid;
        this.lastOrderDate = formatter.parse(lastorder);
    }
    public void updateBalance(double bal){
        this.balance = this.balance- bal;
    }
}
