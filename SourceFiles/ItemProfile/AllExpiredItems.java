/**
 * Class made by 'Benjamin Pienta'
 **/

package ItemProfile;

import GUI.DeleteItemGUI;
import GUI.UpdateItemSelectorGUI;
import Login.HoldCurrentLoginType;
import Login.HoldPagesVisited;
import Login.LoginMenu;
import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class AllExpiredItems extends JFrame
{
    private JTable itemTable;
    private JButton logout;
    private JButton editItems;
    private JButton deleteItems;

    // Today's date
    Date todaysDate = new Date();

    public AllExpiredItems()
    {
        setTitle("All Expired Items");
        setBounds(300, 90, 1100, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // 2D arraylist that will store each item's data
        ArrayList<ArrayList<String>> itemInfo = new ArrayList<>();
        int row = 0; // rows of the new 2d arraylist

        // Columns for the table
        String[] columns = {"Item ID", "Name", "Vendor ID", "Selling Price", "Item Category", "Quantity On Hand", "Unit of Measurement",
         "Expiration Date", "Purchase Price"};

        // Store all the information into the 2D array
        for (ItemProfile item : Main.items)
        {
            if (todaysDate.after(item.getExpireDate()))
            {
                itemInfo.add(new ArrayList<String>());

                // Store all variables from the items
                String id = item.getItemID();
                String name = item.getItemName();
                String vendorID = item.getVendorID();
                String sellingPrice = Double.toString(item.getSellingPrice());
                String itemCategory = item.getItemCategory();
                String quantityOnHand = Double.toString(item.getQuantityonHand());
                String unitOfMeasurement = item.getUnitOfMeasurement();
                String expirationDate = item.getExpireDateString();
                String purchasePrice = Double.toString(item.getPurchasePrice());

                itemInfo.get(row).add(id);
                itemInfo.get(row).add(name);
                itemInfo.get(row).add(vendorID);
                itemInfo.get(row).add(sellingPrice);
                itemInfo.get(row).add(itemCategory);
                itemInfo.get(row).add(quantityOnHand);
                itemInfo.get(row).add(unitOfMeasurement);
                itemInfo.get(row).add(expirationDate);
                itemInfo.get(row).add(purchasePrice);

                row++;
            }
        }

        // Transfer the Arraylist data to the 2D string for ease of adding to the table
        String[][] normal2DStringArray = new String[row][9];

        // Transfer all data from the 2D arraylist to the normal 2D String array
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                normal2DStringArray[i][0] = itemInfo.get(i).get(0);
                normal2DStringArray[i][1] = itemInfo.get(i).get(1);
                normal2DStringArray[i][2] = itemInfo.get(i).get(2);
                normal2DStringArray[i][3] = itemInfo.get(i).get(3);
                normal2DStringArray[i][4] = itemInfo.get(i).get(4);
                normal2DStringArray[i][5] = itemInfo.get(i).get(5);
                normal2DStringArray[i][6] = itemInfo.get(i).get(6);
                normal2DStringArray[i][7] = itemInfo.get(i).get(7);
                normal2DStringArray[i][8] = itemInfo.get(i).get(8);
            }
        }

        // Make the table
        itemTable = new JTable(normal2DStringArray, columns);
        itemTable.setSize(900, 300);

        JScrollPane scrollPanel = new JScrollPane(itemTable);
        add(scrollPanel);

        // Add the extra buttons to the bottom of the page
        add(extraButtons(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel extraButtons()
    {
        JPanel panel = new JPanel();

        // Button that logs the user out
        logout = new JButton("Log Out");
        logout.setSize(150, 30);
        logout.setLocation(700, 20);
        logout.addActionListener(new ActionListener() {
            // Logs the user out and sends them to the login screen
            @Override
            public void actionPerformed(ActionEvent e)
            {
                HoldCurrentLoginType.updateUser(null);
                HoldPagesVisited.resetNumberOfPagesVisited();

                new LoginMenu();
                AllExpiredItems.super.dispose();
            }
        });
        panel.add(logout);

        // Button that sends the user to the Update Items page
        editItems = new JButton("Update Items Page");
        editItems.setSize(150, 30);
        editItems.setLocation(500, 20);
        editItems.addActionListener(new ActionListener()
        {
            // Sends the user to the Update Items page and closes this
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new UpdateItemSelectorGUI("Update Item");
                AllExpiredItems.super.dispose();
            }
        });
        panel.add(editItems);

        // Button that sends the user to the Delete Items page
        deleteItems = new JButton("Delete Items Page");
        deleteItems.setSize(150, 30);
        deleteItems.setLocation(200, 20);
        deleteItems.addActionListener(new ActionListener()
        {
            // Sends the user to the Update Items page and closes this
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new DeleteItemGUI("Delete Item");
                AllExpiredItems.super.dispose();
            }
        });
        panel.add(deleteItems);

        return panel;
    }
}
