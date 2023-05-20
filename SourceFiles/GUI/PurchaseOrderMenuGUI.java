package GUI;

import Login.HoldCurrentLoginType;
import Login.HoldPagesVisited;
import Login.LoginMenu;
import Main.MainMenu;
import ProfileUsers.Vendor;
import ProfileUsers.VendorAccountArray;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/*
@author Andrew James
 */

import static Main.Main.ItemPurchaseOrderStorage;

public class PurchaseOrderMenuGUI extends JFrame {
    private JComboBox vendorList;
    private JButton submitButton;
    private JPanel mainPanel;
    private JButton viewPurchaseOrdersButton;
    private JButton returnToMainMenuButton;
    private JButton logoutButton;

    public PurchaseOrderMenuGUI(String title) {
        super(title);
        ItemPurchaseOrderStorage.removeAll(ItemPurchaseOrderStorage);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setBounds(100,100,500,500);
        setLocationRelativeTo(null);
        Date currentDate = new Date(System.currentTimeMillis());
        //add vendors to the vendorCB so they can be selected
        for(int i = 0; i < VendorAccountArray.vendors.length; i++)
        {
            vendorList.addItem(VendorAccountArray.vendors[i].getFullName());
        }
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedVendor = vendorList.getSelectedItem().toString();
                new PurchaseOrderCreatorGUI("Create Order", selectedVendor);
                PurchaseOrderMenuGUI.super.dispose();
            }
        });

        //View all purchase orders. Can view by vendor by viewing the table
        viewPurchaseOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedVendor = vendorList.getSelectedItem().toString();
                JFrame OrderTable = new  JFrame("Show Items");
                OrderTable.setSize(500,500);
                OrderTable.add(new PurchaseOrderTableGUI().panel1);
                OrderTable.setVisible(true);
                OrderTable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                OrderTable.setLocationRelativeTo(null);
            }
        });
        returnToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                PurchaseOrderMenuGUI.super.dispose();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HoldCurrentLoginType.updateUser(null);
                HoldPagesVisited.resetNumberOfPagesVisited();

                // Reset 'hasNotUpdated' for all vendors
                for (Vendor vendor: VendorAccountArray.vendors)
                    vendor.hasNotUpdated = true;

                new LoginMenu();
                PurchaseOrderMenuGUI.super.dispose();
            }
        });
    }
}
