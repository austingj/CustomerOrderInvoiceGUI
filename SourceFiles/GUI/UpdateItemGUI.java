package GUI;

import ItemProfile.writeCSV;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Main.Main.items;
public class UpdateItemGUI extends JFrame {


    private JPanel mainPanel;
    private JCheckBox itemNameCheckBox;
    private JCheckBox vendorIDCheckBox;
    private JCheckBox sellingPriceCheckBox;
    private JCheckBox itemCategoryCheckBox;
    private JCheckBox expireDateCheckBox;
    private JCheckBox purchasePriceCheckBox;
    private JCheckBox unitOfMeasurementCheckBox;
    private JCheckBox quantityOnHandCheckBox;
    private JComboBox VendorIDBox;
    private JTextField itemName;
    private JTextField sellingPrice;
    private JComboBox ItemCategoryCB;
    private JTextField purchasePrice;
    private JComboBox UOMCB;
    private JTextField quantityOnHand;
    private JDatePicker expireDateForm;
    private JButton updateItemButton;
    private JLabel itemNameLabel;
    private JLabel itemIDLabel;

    public UpdateItemGUI(String title, Object itemInfo) {
        super(title);

        itemName.setDocument(new JTextFieldMaxSize(20));
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        setSize(750, 750);
        setLocationRelativeTo(null);
        int indexOfItem = items.indexOf(itemInfo);
        VendorIDBox.setEditable(false);
        itemName.setEditable(false);
        sellingPrice.setEditable(false);
        ItemCategoryCB.setEditable(false);
        purchasePrice.setEditable(false);
        UOMCB.setEditable(false);
        quantityOnHand.setEditable(false);
        expireDateForm.setEnabled(false);

        itemNameLabel.setText("Item Name: " + items.get(indexOfItem).getItemName());
        itemIDLabel.setText("ID: " + items.get(indexOfItem).getItemID());

        itemNameCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (itemNameCheckBox.isSelected()) {
                    itemName.setEditable(true);

                } else {
                    itemName.setEditable(false);
                }
            }
        });
        vendorIDCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vendorIDCheckBox.isSelected()) {
                    VendorIDBox.setEditable(true);
                } else {
                    VendorIDBox.setEditable(false);
                }
            }
        });
        sellingPriceCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sellingPriceCheckBox.isSelected()) {
                    sellingPrice.setEditable(true);
                } else {
                    sellingPrice.setEditable(false);
                }
            }
        });
        itemCategoryCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (itemCategoryCheckBox.isSelected()) {
                    ItemCategoryCB.setEnabled(true);
                } else {
                    ItemCategoryCB.setEnabled(false);
                }
            }
        });
        expireDateCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (expireDateCheckBox.isSelected()) {
                    expireDateForm.setEnabled(true);
                } else {
                    expireDateForm.setEnabled(false);
                }
            }
        });


        purchasePriceCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (purchasePriceCheckBox.isSelected()) {
                    purchasePrice.setEditable(true);
                } else {
                    purchasePrice.setEditable(false);
                }
            }
        });
        unitOfMeasurementCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (unitOfMeasurementCheckBox.isSelected()) {
                    UOMCB.setEnabled(true);
                } else {
                    UOMCB.setEnabled(false);
                }
            }
        });
        quantityOnHandCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quantityOnHandCheckBox.isSelected()) {
                    quantityOnHand.setEditable(true);
                } else {
                    quantityOnHand.setEditable(false);
                }
            }
        });
        updateItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (expireDateCheckBox.isSelected())
                {
                    String expireDate = (expireDateForm.getModel().getMonth() + 1) + "/" + (expireDateForm.getModel().getDay()) + "/" + (expireDateForm.getModel().getYear());
                    Date currentDate = new Date(System.currentTimeMillis());
                    Date expireDateFor;
                    try {
                        expireDateFor = formatter.parse(expireDate);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }

                    if ((expireDateFor.compareTo(currentDate)) < 0) {
                        JOptionPane.showMessageDialog(null, "Date invalid. Please put an expiry date for a future date");
                        return;
                    }
                    items.get(indexOfItem).setExpireDate(expireDateFor);
                }

                if (itemNameCheckBox.isSelected())
                {
                    if (itemName.getText().matches("-?\\d+(\\.\\d+)?") || itemName.getText() == null) {
                        JOptionPane.showMessageDialog(null, "Please enter valid Item Name");
                        return;
                    }
                    items.get(indexOfItem).setItemName(itemName.getText());
                }

                if (sellingPriceCheckBox.isSelected())
                {
                    if (!sellingPrice.getText().matches("-?\\d+(\\.\\d+)?") || sellingPrice.getText() == null || Double.parseDouble(sellingPrice.getText()) < 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid Selling Price");
                        return;
                    }
                    items.get(indexOfItem).setSellingPrice(Double.parseDouble(sellingPrice.getText()));
                }

                if (itemCategoryCheckBox.isSelected()) {
                    if (ItemCategoryCB.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "Please Select an item category");
                        return;
                    }
                    items.get(indexOfItem).setItemCategory(ItemCategoryCB.getSelectedItem().toString());
                }

                if (purchasePriceCheckBox.isSelected())
                {

                    if (!purchasePrice.getText().matches("-?\\d+(\\.\\d+)?") || purchasePrice.getText() == null || Double.parseDouble(purchasePrice.getText()) < 0) {
                        JOptionPane.showMessageDialog(null, "Please enter valid Purchase Price");
                        return;
                    }
                    items.get(indexOfItem).setPurchasePrice(Double.parseDouble(purchasePrice.getText()));
                }

                if (unitOfMeasurementCheckBox.isSelected())
                {

                    if (UOMCB.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "Please Select an item category");
                        return;

                    }
                    items.get(indexOfItem).setUnitOfMeasurement(UOMCB.getSelectedItem().toString());
                }
                if (quantityOnHandCheckBox.isSelected())
                {

                    if (!quantityOnHand.getText().matches("-?\\d+(\\.\\d+)?") || quantityOnHand.getText() == null || Double.parseDouble(quantityOnHand.getText()) < 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid Quantity");
                        return;
                    }
                    items.get(indexOfItem).setQuantityonHand(Double.parseDouble(quantityOnHand.getText()));
                }

                JOptionPane.showMessageDialog(null, "Item ID: " + items.get(indexOfItem).getItemID() + " updated!");
                writeCSV.write_items(items);
                UpdateItemGUI.super.dispose();
            }


        });

    }
}
