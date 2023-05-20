package GUI;


import ProfileUsers.Vendor;
import ProfileUsers.VendorAccountArray;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
This is the class for Listing Vendors
@author Austin

 */
public class ListVendors extends JFrame{
    private JPanel Panel1;
    private JLabel FullNameLabel;

    private JButton Return;
    private JPanel ListVendors;
    private JLabel FullNameL;
    private JTextArea textArea1;


    ListVendors() {
        setContentPane(ListVendors);
        setTitle("Vendor Form");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        if(VendorAccountArray.searchForUser(0) == null){
            new PurchaserView();
            ListVendors.super.dispose();
        }
        //so far just list first profile in array.
        for(int i = 0; i < VendorAccountArray.arraySize; i++) {
            String name = VendorAccountArray.vendors[i].getFullName();
            if(name.equals(null)){
                continue;
            }
            textArea1.append(name + "\n");
        }
        VendorAccountArray.print();
        Return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PurchaserView();
                ListVendors.super.dispose();
            }
        });
    }

}
