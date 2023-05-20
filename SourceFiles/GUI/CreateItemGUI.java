package GUI;
import ItemProfile.ItemProfile;
import ProfileUsers.VendorAccountArray;
import org.jdatepicker.JDatePicker;
import ItemProfile.GenerateItemID;
import ItemProfile.writeCSV;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static Main.Main.items;

/*
@author: Andrew James
 */

public class CreateItemGUI extends JFrame {
    private JPanel createItemFrame;
    private JTextField itemName;
    private JComboBox vendorIDCB;
    private JTextField sellingPrice;
    private JTextField purchasePrice;
    private JComboBox UnitsCB;
    private JTextField quantity;
    private JButton createItemButton;
    private JComboBox itemCategoriesCB;
    private JDatePicker expireDateForm;

    public CreateItemGUI(java.lang.String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(createItemFrame);
        this.pack();
        setSize(500, 500);
        setLocationRelativeTo(null);
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        //setting max size for item name == 20 characters
        itemName.setDocument(new JTextFieldMaxSize(20));

        //pull vendor IDs from @Austin's feature
        for(int i = 0; i < VendorAccountArray.vendors.length; i++)
        {
            vendorIDCB.addItem(VendorAccountArray.vendors[i].getUserID());
        }
        createItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Format date from the JDatePicker so we can pass it to our item
                java.lang.String expireDate = (expireDateForm.getModel().getMonth() + 1) + "/" + (expireDateForm.getModel().getDay()) + "/" + (expireDateForm.getModel().getYear());
                Date currentDate = new Date(System.currentTimeMillis());
                Date expireDateFor;
                try {
                    expireDateFor = formatter.parse(expireDate);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                //Input verification for all fields.
                if (itemName.getText().matches("-?\\d+(\\.\\d+)?") || itemName.getText() == null) {
                    JOptionPane.showMessageDialog(null, "Please enter valid Item Name");
                    return;
                } else if (!sellingPrice.getText().matches("-?\\d+(\\.\\d+)?") || sellingPrice.getText() == null || Double.parseDouble(sellingPrice.getText()) < 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Selling Price");
                    return;
                } else if (!purchasePrice.getText().matches("-?\\d+(\\.\\d+)?") || purchasePrice.getText() == null || Double.parseDouble(purchasePrice.getText()) < 0) {
                    JOptionPane.showMessageDialog(null, "Please enter valid Purchase Price");
                    return;
                } else if (!quantity.getText().matches("-?\\d+(\\.\\d+)?") || quantity.getText() == null || Double.parseDouble(quantity.getText()) < 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Quantity");
                    return;
                } else if (itemCategoriesCB.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Please Select an item category");
                    return;
                } else if (UnitsCB.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Please Select an item category");
                    return;

                } else if ((expireDateFor.compareTo(currentDate)) < 0) {
                    JOptionPane.showMessageDialog(null, "Date invalid. Please put an expiry date for a future date");
                    return;
                     } else if(vendorIDCB.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "Please Select Vendor!");
                        return;
                     }
                else{
                        ItemProfile item = new ItemProfile();
                        boolean doesExist = true;
                        //verify that ItemID is unique. If not will generate a new one.
                        while (doesExist) {
                            java.lang.String itemID = GenerateItemID.GenerateItemID();
                            for (ItemProfile itemTest : items) {
                                if (Objects.equals(itemTest.getItemID(), itemID)) {
                                    doesExist = true;
                                    itemID = GenerateItemID.GenerateItemID();
                                } else {
                                    doesExist = false;
                                }
                            }
                        }
                        try {
                            item.createItem(GenerateItemID.GenerateItemID(), itemName.getText(), vendorIDCB.getSelectedItem().toString(), Double.parseDouble(sellingPrice.getText()), itemCategoriesCB.getSelectedItem().toString(),
                                    Double.parseDouble(quantity.getText()), UnitsCB.getSelectedItem().toString(), expireDate, Double.parseDouble(purchasePrice.getText()));
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        items.add(item);
                        writeCSV.write_items(items);
                        JOptionPane.showMessageDialog(null, "Item " + item.getItemName() + " successfully created with Item ID: " + item.getItemID());
                        CreateItemGUI.super.dispose();
                    }
                }
            });
    }
}

