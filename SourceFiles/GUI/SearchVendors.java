package GUI;

import javax.swing.*;
import ProfileUsers.VendorAccountArray;
import ProfileUsers.Vendor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
Search Vendors class
@author Austin Jeffery

 */
public class SearchVendors extends JFrame{
    private JLabel Enterid;
    private JLabel entername;
    private JTextField IDTextfield;
    private JTextField NameTextField;
    private JLabel fullname;
    private JLabel idlabel;
    private JLabel StreetL;
    private JLabel cityL;
    private JLabel StateL;
    private JLabel PhoneL;
    private JLabel balL;
    private JLabel LastpaidL;
    private JLabel lastOrderL;
    private JLabel seasonalL;
    private JButton searchButton;
    private JButton returnButton;
    private JPanel SearchPane;
    private JLabel nL;

    SearchVendors(){
        setContentPane(SearchPane);
        setTitle("Search Form");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                if(IDTextfield.getText().matches(".*[a-z].*")) {
                    JOptionPane.showMessageDialog(null, "Please enter Vendor name or ID");
                    return;
                }
                if(IDTextfield.getText().equals("") && NameTextField.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter Vendor name or ID");
                    return;
                }
                String id = IDTextfield.getText();
                String name = NameTextField.getText();
                Vendor v = new Vendor();
                if(id.equals("") && !name.equals("")){
                    System.out.println(name);
                    v = VendorAccountArray.searchForUser(name);
                    if(v==null){
                        JOptionPane.showMessageDialog(null, "The " + name + " profile vendor is not found");
                        return;
                    }
                    IDTextfield.setText(String.valueOf(v.getUserID()));
                    i = Integer.parseInt(IDTextfield.getText());
                }
                else if(!id.equals("") && name.equals("")){
                    i = Integer.parseInt(id);
                    if(VendorAccountArray.searchForUser(i) == null){
                        JOptionPane.showMessageDialog(null, "id: " + id + "\n Vendor not found");
                        return;
                    }
                    v = VendorAccountArray.searchForUser(i);
                    NameTextField.setText(v.getFullName());
                    name = NameTextField.getText();
                }

                i = Integer.parseInt(IDTextfield.getText());

                if(VendorAccountArray.searchForUser(i)==null){
                    return;
                }
                    else{
                        v = VendorAccountArray.searchForUser(i);
                        int k = v.getUserID();

                        if(!v.getFullName().equals(name) || k != i){
                            JOptionPane.showMessageDialog(null, "Vendor ID and name do not match!");
                        }
                        else{
                            getvalues(v);
                        }
                    }

            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PurchaserView();
                SearchVendors.super.dispose();

            }
        });
    }

    private void getvalues(Vendor v) {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        fullname.setText(v.getFullName());
        idlabel.setText(String.valueOf(v.getUserID()));
        StreetL.setText(v.getStreetAddress());
        cityL.setText(v.getCity());
        StateL.setText(v.getState());
        PhoneL.setText(v.getPhone());
        balL.setText(String.valueOf(v.getBalance()));
        LastpaidL.setText(String.valueOf(v.getLastPaidAmount()));
        String lastorderdate = String.valueOf(v.getLastOrderDate());
        String seasondate =String.valueOf(v.getSeasonalDiscount());
        String lastDate = formatter.format(new Date(lastorderdate));
        String seasDate = formatter.format(new Date(seasondate));
        lastOrderL.setText(lastDate);
        seasonalL.setText(seasDate);


    }
}
