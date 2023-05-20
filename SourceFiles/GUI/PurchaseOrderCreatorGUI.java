package GUI;

import ItemProfile.ItemProfile;
import PurchaseOrder.PurchaseOrder;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import PurchaseOrder.writePurchaseOrders;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import ItemProfile.writeCSV;
import ItemProfile.GenerateItemID;

import static Main.Main.*;
import static ProfileUsers.VendorAccountArray.vendors;


/*
@author Andrew James
 */

public class PurchaseOrderCreatorGUI extends JFrame{


    private JPanel mainPanel;
    private JList selectedItems;
    private JList itemList;
    private JButton addButton;
    private JButton submitPurchaseOrderButton;
    private JDatePicker datePicker;

    public PurchaseOrderCreatorGUI(String title,String vendorName){

        super(title);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setBounds(100,100,800,500);
        setLocationRelativeTo(null);
        double salesTax = 0.06;
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date currentDate = new Date(System.currentTimeMillis());
        String vendorID = null;
        DefaultListModel<ItemProfile> model = new DefaultListModel();
        itemList.setModel(model);

        //iterate over vendor and item list. if vendor has items that are in stock and not exipred will be added to the aval items to choose from
        for(ItemProfile item : items)
        {
            for (int i = 0; i < vendors.length; i++)
            {
                if((vendorName == vendors[i].getFullName() && ((item.getExpireDate().compareTo(currentDate)) > 0)))
                {
                    vendorID = String.valueOf(vendors[i].getUserID());
                    if(vendorID.matches(item.getVendorID()) && item.getQuantityonHand()>0){

                        model.addElement(item);
                    }
                }
            }
        }

        //returns a response if vendor has no items. won't let user create a purchase order.
        if (itemList.getModel().getSize() == 0){
            JOptionPane.showMessageDialog(null, "Vendor Selected has no current items! Returning to menu.");
            JFrame Menu = new PurchaseOrderMenuGUI("Purchaser Menu");
            Menu.setVisible(true);
            this.dispose();
        }

        DefaultListModel model2 = new DefaultListModel();
        selectedItems.setModel(model2);

        //add button moves items to different list so user can view what selected items they have
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedItems.getModel().getSize() !=5)
                {
                    JFrame ItemInfo = new PurchaseOrderItemInfoGUI("Item Info",itemList.getSelectedValue().toString());
                    ItemInfo.setVisible(true);
                    model2.addElement(itemList.getSelectedValue());
                    model.removeElement(itemList.getSelectedValue());
                }
            }
        });


        submitPurchaseOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.lang.String needByDate = (datePicker.getModel().getMonth() + 1) + "/" + (datePicker.getModel().getDay()) + "/" + (datePicker.getModel().getYear());
                Date currentDate = new Date(System.currentTimeMillis());
                Date expireDateFor;
                try {
                    expireDateFor = formatter.parse(needByDate);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                // can't have a needbydate in the past.
                if ((expireDateFor.compareTo(currentDate)) < 0){
                    JOptionPane.showMessageDialog(null, "Date invalid. Please put a Need By Date for a future date!");
                    return;
                }else if(selectedItems.getModel().getSize() ==0)
                {
                    JOptionPane.showMessageDialog(null, "Please select at least one item!");
                    return;
                }else{

                    //check if generated purchaseorder is unique.
                    boolean doesExist = true;
                    java.lang.String PurchaseID = GenerateItemID.GenerateItemID();
                    while (doesExist) {
                        for (PurchaseOrder itemTest : PurchaseOrders) {
                            if (Objects.equals(itemTest.getPurchaseOrderID(), PurchaseID)) {
                                doesExist = true;
                                PurchaseID = GenerateItemID.GenerateItemID();
                            } else {
                                doesExist = false;
                            }
                        }

                    }

                    //calling upon the ItemPurchaseOrderStorage class. Needed this to store items when passive between JFrames.
                    PurchaseOrder order = new PurchaseOrder();
                    double balance = 0;
                    for(ItemPurchaseOrderStore items1 : ItemPurchaseOrderStorage){
                        order.addPurchaseItems(items1.getItemID());
                        balance += (items1.getamountPurchased()*items1.getBalance());
                        for(ItemProfile items2: items){
                            if(items1.getItemID().equals(items2.getItemID()))
                            {
                                //will deduct currently selected items from what is currently on hand
                                items2.setQuantityonHand(items2.getQuantityonHand()-items1.getamountPurchased());
                            }
                        }
                    }

                    //calculate balance with sales tax
                    balance = balance * (1+salesTax);
                    try {
                        String vendorID2 = null;
                        for (int i = 0; i < vendors.length; i++)
                        {
                            if(vendorName == vendors[i].getFullName())
                            {
                                vendorID2 = String.valueOf(vendors[i].getUserID());
                                vendors[i].addToBalance(balance);

                            }
                        }
                        //create the new order
                        order.createPurchaseOrder(PurchaseID,vendorID2,balance,needByDate);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    //clear the item storage array so it will be clean next time we use it
                    ItemPurchaseOrderStorage.removeAll(ItemPurchaseOrderStorage);
                    //add item to purchaseorders arraylist and display successfull creation to user
                    PurchaseOrders.add(order);
                    JOptionPane.showMessageDialog(null, "Successfully created purchased order!");

                    //check if two items go out out stock. IF so we return a message alerting the user two items went out of stock
                    int totalOutOfStock = 0;
                    for(ItemProfile item : items)
                    {
                        if(item.getQuantityonHand() == 0){
                            totalOutOfStock+=1;
                        }
                    }
                    if(totalOutOfStock >= 2){
                        JOptionPane.showMessageDialog(null, "Two items have gone out of stock!");
                    }

                    PurchaseOrderCreatorGUI.super.dispose();
                    JFrame PurchaseMenu = new PurchaseOrderMenuGUI("Purchase Order");
                    PurchaseMenu.setVisible(true);
                    //write to CSV
                    writePurchaseOrders.write_items(PurchaseOrders);
                    writeCSV.write_items(items);
                }

            }
        });
    }



}
