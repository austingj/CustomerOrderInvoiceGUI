package GUI;

import ItemProfile.ItemProfile;
import PurchaseOrder.PurchaseOrder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static Main.Main.PurchaseOrders;

/*
@author Andrew James
 */
public class PurchaseOrderTableGUI {
    JPanel panel1;
    private JTable table1;

    //creates a table that displays all purchase order info. third coulmn allows users to view order by vendor
    java.lang.String header[] = {"Purchase ID", "Need By Date", "Vendor ID"};
    private void createUIComponents() {

        DefaultTableModel model = new DefaultTableModel(0, 2){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        model.setColumnIdentifiers(header);
        table1 = new JTable(model);

        for (PurchaseOrder item : PurchaseOrders) {
            Object[] row = {item.getPurchaseOrderID(), item.getNeedByDateString(),item.getVendorID()};
            model.addRow(row);
        }

    }
}
