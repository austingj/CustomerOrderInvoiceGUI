package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ItemProfile.ItemProfile;

import static Main.Main.items;

/*
@author: Andrew James
 */


public class ItemTableGUI extends JFrame {
    JPanel panel1;
    private JTable table1;
    java.lang.String header[] = {"Item ID", "Item Name", "Quantity on Hand", "Selling Price", "Purchased Price", "Expire Date"};
    private void createUIComponents() {
        DefaultTableModel model = new DefaultTableModel(0, 6){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        model.setColumnIdentifiers(header);
        table1 = new JTable(model);

        for (ItemProfile item : items) {
            Object[] row = {item.getItemID(),item.getItemName(),item.getQuantityonHand(),item.getSellingPrice(),item.getPurchasePrice(),item.getExpireDateString()};
            model.addRow(row);
        }

    }
}
