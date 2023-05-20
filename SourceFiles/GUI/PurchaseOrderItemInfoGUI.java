package GUI;

import ItemProfile.ItemProfile;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Main.Main.*;

/*
@author Andrew James
 */

public class PurchaseOrderItemInfoGUI extends JFrame {


    private JButton submitButton;
    private JTextField quantity;
    private JLabel currentQuantity;
    private JPanel mainPanel;
    private JLabel UOMlabel;
    private JLabel priceOfItem;

    public PurchaseOrderItemInfoGUI(java.lang.String title, java.lang.String item){

        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setBounds(100,100,800,500);
        setLocationRelativeTo(null);
        java.lang.String itemID = item.substring(0,6);
        ItemProfile selectedItem = new ItemProfile();
        for(ItemProfile item1: items)
        {
            if(itemID.matches(item1.getItemID()))
            {
                selectedItem = item1;
            }
        }

        currentQuantity.setText(Double.toString(selectedItem.getQuantityonHand()));
        UOMlabel.setText(selectedItem.getUnitOfMeasurement());
        priceOfItem.setText(Double.toString(selectedItem.getPurchasePrice()));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //input verification as outline in requirements. no negative or 0 value
                if (!quantity.getText().matches("-?\\d+(\\.\\d+)?") || quantity.getText() == null || Double.parseDouble(quantity.getText()) <= 0 ||
                        Double.parseDouble(quantity.getText()) > Double.parseDouble(currentQuantity.getText())) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Quantity");
                    }else{

                    /*calling on the itempurchasestorage class. I use this to store the data we get from the user here so we can user it when we generate an order.
                    Probably an easier/efficent way of doing this but Java does not allow pass by reference and I am still learning :(
                     */
                    ItemPurchaseOrderStore thisitem = new ItemPurchaseOrderStore();
                    thisitem.setItemID(itemID);
                    thisitem.setAmountPurchased(Double.parseDouble(quantity.getText()));
                    thisitem.setBalance(Double.parseDouble(priceOfItem.getText()));
                    ItemPurchaseOrderStorage.add(thisitem);
                    JOptionPane.showMessageDialog(null, "Item added to Purchase Order!");
                    PurchaseOrderItemInfoGUI.super.dispose();
                }
            }
        });

    }
}
