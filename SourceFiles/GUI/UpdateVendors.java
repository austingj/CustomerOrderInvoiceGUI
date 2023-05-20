package GUI;

import ProfileUsers.VendorAccountArray;
import ProfileUsers.Vendor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
GUI for updating vendors.
@author Austin Jeffery

 */
public class UpdateVendors extends  JFrame{
    String[] states = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE",
            "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS",
            "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO",
            "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND",
            "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN",
            "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY" };
    private JLabel SearchL;
    private JTextField VendorIDtextfield;
    private JButton Submit;
    private JTextField fullNameTextField;
    private JTextField streetTextField;
    private JTextField cityTextField;
    private JTextField stateTextField;
    private JTextField phoneTextField;
    private JButton Update;
    private JPanel UpdateVendorsPanel;
    private JTextField Balance;
    private JButton Return;
    private JComboBox StateList;
    private JTextField VendorNameField;
    private JButton Reset;
    private JTextField lastorderDate;
    private JTextField seasonaldate;
    private JTextField lastpaidamount;

    UpdateVendors(){
        fullNameTextField.setDocument(new JTextFieldMaxSize(20));
        streetTextField.setDocument(new JTextFieldMaxSize(20));
        cityTextField.setDocument(new JTextFieldMaxSize(20));
        phoneTextField.setDocument(new JTextFieldMaxSize(12));
        for(int i = 0;i<50;i++){
            StateList.insertItemAt(states[i],i);
        }
        setContentPane(UpdateVendorsPanel);
        setTitle("Vendor Form");
        setSize(1600, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = VendorIDtextfield.getText();
                if(VendorIDtextfield.getText().matches(".*[a-z].*")) {
                    JOptionPane.showMessageDialog(null, "Please enter valid Vendor ID");
                    return;
                }
                if(VendorIDtextfield.getText().equals("") && VendorNameField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter Vendor name or ID");
                    return;
                }
                String ids = VendorIDtextfield.getText();
                String name = VendorNameField.getText();
                Vendor v = new Vendor();
                if(ids.equals("") && !name.equals("")){
                    System.out.println(name);
                    v = VendorAccountArray.searchForUser(name);
                    if(v==null){
                        JOptionPane.showMessageDialog(null, "Please enter valid Vendor name or ID");
                        return;
                    }
                    VendorIDtextfield.setText(String.valueOf(v.getUserID()));
                    ids = VendorIDtextfield.getText();
                }
                if(!ids.equals("") && name.equals("")){
                    int i = Integer.parseInt(ids);
                    if(VendorAccountArray.searchForUser(i) == null){
                        JOptionPane.showMessageDialog(null, "id: " + ids + "\n Vendor not found");
                        return;
                    }
                    v = VendorAccountArray.searchForUser(i);
                    VendorNameField.setText(v.getFullName());
                    name = VendorNameField.getText();
                }
                if(!ids.equals("") && !name.equals("")){//search both text fields
                int i = Integer.parseInt(ids);
                if(VendorAccountArray.searchForUser(i) == null){
                    JOptionPane.showMessageDialog(null, "id: " + ids + "\n Vendor not found");
                    return;
                }
                    v = VendorAccountArray.searchForUser(i);
                    int k = v.getUserID();

                    if(!v.getFullName().equals(name) || k != i){
                        JOptionPane.showMessageDialog(null, "Vendor ID and name do not match!");
                    }
                else {
                    fullNameTextField.setText(VendorAccountArray.searchForUser(i).getFullName());
                    streetTextField.setText(VendorAccountArray.searchForUser(i).getStreetAddress());
                    cityTextField.setText(VendorAccountArray.searchForUser(i).getCity());
                    phoneTextField.setText(VendorAccountArray.searchForUser(i).getPhone());
                    Balance.setText(String.valueOf(VendorAccountArray.searchForUser(i).getBalance()));
                    StateList.setSelectedItem(VendorAccountArray.searchForUser(i).getState());
                            String lastorderdate = String.valueOf(VendorAccountArray.searchForUser(i).getLastOrderDate());
                            String seasondate =String.valueOf(VendorAccountArray.searchForUser(i).getSeasonalDiscount());
                            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            String lastDate = formatter.format(new Date(lastorderdate));
                            String seasDate = formatter.format(new Date(seasondate));
                            lastorderDate.setText(lastDate);
                            seasonaldate.setText(seasDate);
                            lastpaidamount.setText(String.valueOf(VendorAccountArray.searchForUser(i).getLastPaidAmount()));
                }
            }
            }
        });

        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valid = 0;
                String id = VendorIDtextfield.getText();
                String currentName = VendorNameField.getText();
                int i = Integer.parseInt(id);
                String fullname = fullNameTextField.getText();
                valid += checkName(currentName,fullname);
                String street = streetTextField.getText();
                String city = cityTextField.getText();
                String state = (String) StateList.getSelectedItem();
                valid += checkAddress(i,street,city,state);
                String phone = phoneTextField.getText();
                valid += checkPhone(phone);
                String lastpaid = lastpaidamount.getText();
                if(Balance.getText().matches(".*[a-z].*")){
                    JOptionPane.showMessageDialog(null, "Balance is not valid (only enter numbers)");
                    return;
                }
                double bal = Double.parseDouble((Balance.getText()));
                if(valid>0){
                    new UpdateVendors();
                    UpdateVendors.super.dispose();
                    return;
                }
                try {
                    String lastorderdate = lastorderDate.getText();
                    String seasondate = seasonaldate.getText();
                    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    Date lastorder = formatter.parse(lastorderdate);
                    Date seasonal = formatter.parse(seasondate);
                    String lastDate = formatter.format(new Date(lastorderdate));
                    String seasDate = formatter.format(new Date(seasondate));
                    Vendor v = new Vendor(i,fullname,street,city,state,phone,lastorder,seasonal);
                    v.setBalance(bal);
                    v.setLastPaidAmount(Double.parseDouble(lastpaid));
                    VendorAccountArray.updateVendor(v,i);
                    new PurchaserView();
                    UpdateVendors.super.dispose();
                    VendorAccountArray.write();
                } catch (ParseException ex) {
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
                UpdateVendors.super.dispose();
            }
        });

        Reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fullNameTextField.setText("");
                streetTextField.setText("");
                cityTextField.setText("");
                phoneTextField.setText("");
                Balance.setText("");
                StateList.setSelectedItem(null);
                VendorIDtextfield.setText("");
                VendorNameField.setText("");
            }
        });
    }
    public int checkAddress(int id, String street, String city, String state){
        String tempStreet, tempCity, tempState;
        int j = VendorAccountArray.searchForUser(id).getUserID();
        for(int i =0; i <VendorAccountArray.arraySize; i++){
            if(i!=j){
                tempStreet = VendorAccountArray.searchForUser(i).getStreetAddress();
                tempCity = VendorAccountArray.searchForUser(i).getCity();
                tempState = VendorAccountArray.searchForUser(i).getState();
                if (street.equals(tempStreet) && city.equals(tempCity) && state.equals(tempState)){
                    JOptionPane.showMessageDialog(null, "Do not enter a duplicate Vendor Address");
                    return 1;
                }
            }

        }
        return 0;
    }
    public int checkName(String current, String check){
        String temp;
        int j = VendorAccountArray.searchForUser(current).getUserID();
        for(int i =0; i <VendorAccountArray.arraySize; i++){
            temp = VendorAccountArray.searchForUser(i).getFullName();
            if(i!=j){
                if (check.equals(temp)){
                    JOptionPane.showMessageDialog(null, "Do not enter a duplicate Vendor Name");
                    return 1;
                }
            }

        }
        return 0;
    }
    public int checkPhone(String phone){
        String temp;
        String dash = String.valueOf(phone.charAt(3));
        String dash2 = String.valueOf(phone.charAt(7));
        if(phone.matches(".*[a-z].*") || (!dash.equals("-")) || (!dash2.equals( "-")) || (phone.length()!=12)){
            JOptionPane.showMessageDialog(null, "Phone number is not valid, must be ###-###-####");
            new VendorForm();
            UpdateVendors.super.dispose();
            return 1;
        }
        return 0;
    }
}
