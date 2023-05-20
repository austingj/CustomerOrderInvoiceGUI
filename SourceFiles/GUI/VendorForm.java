package GUI;

import ProfileUsers.ProfileFactory;
import ProfileUsers.VendorAccountArray;
import ProfileUsers.Vendor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
/*
This class is to show the form to add Vendors and will validate user input
implemented ProfileFactory to return Vendor profile
@author Austin Jeffery

 */
public class VendorForm  extends JFrame{
    String[] states = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE",
                         "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
                        "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO",
                        "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND",
                        "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN",
                        "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" };

    private JPanel Panel1;
    private JTextField FullName;
    private JLabel FullNameLabel;
    private JLabel StreetAddress;
    private JTextField StreetAdd;
    private JLabel CityL;
    private JTextField City;
    private JLabel StateL;
    private JTextField State;
    private JLabel PhoneL;
    private JTextField Phone;
    private JButton Submit;
    private JButton Return;
    private JComboBox StateList;
    private JLabel lastorderdateL;
    private JTextField LastOrderDate;
    private JLabel SeasonaldDiscountL;
    private JTextField SeasonalDiscountDate;

    public VendorForm(){
        ProfileFactory vendorBuilder = new ProfileFactory();
        setContentPane(Panel1);
        setTitle("Vendor Form");
        setSize(900,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        FullName.setDocument(new JTextFieldMaxSize(20));
        StreetAdd.setDocument(new JTextFieldMaxSize(20));
        City.setDocument(new JTextFieldMaxSize(20));
        Phone.setDocument(new JTextFieldMaxSize(12));

        //load state list
        for(int i = 0;i<50;i++){
            StateList.insertItemAt(states[i],i);
        }

        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vendor v = null;
                //If any user input is invalid, valid = valid +1, at end if valid does not equal zero it will not go through
                try{
                   int valid = 0;
                    String fullname = FullName.getText();
                    valid = checkName(fullname);
                    String street = StreetAdd.getText();
                    String city = City.getText();
                    String state = (String) StateList.getSelectedItem();
                    valid += checkAddress(street,city,state);
                    String phone = Phone.getText();
                    valid += checkPhone(phone);
                    String lastorderdate = LastOrderDate.getText();
                    String seasonaldate = SeasonalDiscountDate.getText();
                    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    Date lastorder = formatter.parse(lastorderdate);
                    Date seasonal = formatter.parse(seasonaldate);
                    String lastDate = formatter.format(new Date(lastorderdate));
                    String seasDate = formatter.format(new Date(seasonaldate));
                    int num = VendorAccountArray.arraySize;
                    //Use factory builder to return vendor object
                    v = (Vendor) vendorBuilder.makeProfile("Vendor");
                    //set Vendor attributes
                    v.setAttributes(num,fullname,street,city,state,phone,lastorder,seasonal);
                    //Add vendor to Vendor Account Array
                    VendorAccountArray.addVendor(v);
                    if(valid > 0){
                        new VendorForm();
                        VendorForm.super.dispose();
                        return;
                    }
                    new PurchaserView();
                    VendorForm.super.dispose();
                    VendorAccountArray.write();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Enter Valid Dates MM/DD/YYYY");
                    ex.printStackTrace();
                    return;
                }
            }
        });
        Return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PurchaserView();

                VendorForm.super.dispose();
            }
        });
    }
    //method to check phone input from user
    public int checkPhone(String phone){
        String temp;
        String dash = String.valueOf(phone.charAt(3));
        String dash2 = String.valueOf(phone.charAt(7));
        if(phone.matches(".*[a-z].*") || (!dash.equals("-")) || (!dash2.equals( "-")) || (phone.length()!=12)){
            JOptionPane.showMessageDialog(null, "Phone number is not valid, must be ###-###-####");
            new VendorForm();
            VendorForm.super.dispose();
            return 1;
        }
        return 0;
    }
    //method to check is name is valid or already in use by other vendors
    public int checkName(String name){
        String temp;
        for(int i =0; i <VendorAccountArray.arraySize; i++){
            temp = VendorAccountArray.searchForUser(i).getFullName();
            if (name.equals(temp)){
                JOptionPane.showMessageDialog(null, "Do not enter a duplicate Vendor Name");
               return 1;
            }
        }
        return 0;
    }
    //method to check address to see if other vendors have duplicate address
    public int checkAddress(String street, String city, String state){
        String tempStreet, tempCity, tempState;
        for(int i =0; i <VendorAccountArray.arraySize; i++){
            tempStreet = VendorAccountArray.searchForUser(i).getStreetAddress();
            tempCity = VendorAccountArray.searchForUser(i).getCity();
            tempState = VendorAccountArray.searchForUser(i).getState();
            if (street.equals(tempStreet) && city.equals(tempCity) && state.equals(tempState)){
                JOptionPane.showMessageDialog(null, "Do not enter a duplicate Vendor Address");
                return 1;
            }
        }
        return 0;
    }


}

