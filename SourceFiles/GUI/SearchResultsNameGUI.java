package GUI;

import ItemProfile.ItemProfile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static GUI.SearchItemsGUI.itemNameSearch;
public class SearchResultsNameGUI extends JFrame {

    JPanel panel1;
    private JTable table1;
    java.lang.String header[] = {"Item ID", "Item Name", "Quantity on Hand", "Selling Price", "Purchased Price", "Expire Date"};

    private void createUIComponents() {
        // TODO: place custom component creation code here

        DefaultTableModel model = new DefaultTableModel(0, 6) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        model.setColumnIdentifiers(header);
        table1 = new JTable(model);

        for (ItemProfile item : itemNameSearch) {
            Object[] row = {item.getItemID(),item.getItemName(),item.getQuantityonHand(),item.getSellingPrice(),item.getPurchasePrice(),item.getExpireDateString()};
            model.addRow(row);
        }

    }

}