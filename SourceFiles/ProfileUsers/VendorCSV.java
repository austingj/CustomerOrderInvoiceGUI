package ProfileUsers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VendorCSV extends Vendor{

    public static String CSVFormatter(Vendor item){
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        String lastorder = String.valueOf(item.getLastOrderDate());
        String seasonaldate = String.valueOf(item.getSeasonalDiscount());

        String lastDate = formatter.format(new Date(lastorder));
        String seasDate = formatter.format(new Date(seasonaldate));
        String CSVFormat = item.getUserID() + "," + item.getFullName() + "," + item.getStreetAddress()
                + "," + item.getCity() + "," + item.getState() + "," + item.getPhone() + "," +
                item.getBalance() + "," + item.getLastPaidAmount() + "," + lastDate
                + "," + seasDate;

        //Formatter for CSV output.

        return CSVFormat;
    }


}
