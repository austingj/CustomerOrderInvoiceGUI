/**
 * Class made by 'Benjamin Pienta'
 **/

package GUI;

import CustomerOrderInvoice.CustomerInvoice;
import CustomerOrderInvoice.CustomerInvoiceArray;
import CustomerOrderInvoice.CustomerOrder;
import CustomerOrderInvoice.CustomerOrderArray;
import Login.HoldCurrentLoginType;
import Login.HoldPagesVisited;
import Login.LoginMenu;
import Main.MainMenu;
import ProfileUsers.Vendor;
import ProfileUsers.VendorAccountArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CustomerInvoiceList extends JFrame
{
    // JFrame variables
    private JTable customerTable;
    private JButton backToMenu;
    private JButton logout;

    // Date formatter variable
    DateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");

    public CustomerInvoiceList(String name) throws IOException {
        setTitle("All Customer Invoices for " + name);
        setBounds(200, 90, 2500, 600);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");

        // 2D arraylist that will store each customer invoice's data
        ArrayList<ArrayList<String>> invoiceInfo = new ArrayList<>();
        int row = 0; // rows of the new 2d arraylist

        // Singular column
        String[] columns = {"ID", "Invoice Date", "Order Date", "Total Invoice Amount", "Items"};

        // For every customer invoice for the given customer, add all their details to an array
        for (CustomerInvoice customerInvoice : CustomerInvoiceArray.customerInvoices)
            for (CustomerOrder customerOrder : CustomerOrderArray.customerorders)
                if (customerInvoice.getCustOrdernumber() == customerOrder.getId())
                {
                    invoiceInfo.add(new ArrayList<>());
                    String stringID = String.valueOf(customerInvoice.getInvoiceID());
                    invoiceInfo.get(row).add(stringID);
                    invoiceInfo.get(row).add(customerInvoice.getInvoiceDate());
                    invoiceInfo.get(row).add(customerInvoice.getOrderDate());
                    invoiceInfo.get(row).add(customerInvoice.getTotalinvoiceamount());
                    invoiceInfo.get(row).add(customerInvoice.DetailsPerItem());
                    row++;
                }

        // Transfer the Arraylist data to the 2D string for ease of adding to the table
        String[][] normal2DStringArray = new String[row][5];

        // Transfer all data from the 2D arraylist to the normal 2D String array
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                normal2DStringArray[i][0] = invoiceInfo.get(i).get(0);
                normal2DStringArray[i][1] = invoiceInfo.get(i).get(1);
                normal2DStringArray[i][2] = invoiceInfo.get(i).get(2);
                normal2DStringArray[i][3] = invoiceInfo.get(i).get(3);
                normal2DStringArray[i][4] = invoiceInfo.get(i).get(4);
            }
        }

        // Make the table
        customerTable = new JTable(normal2DStringArray, columns);
        customerTable.setSize(2500, 300);

        JScrollPane scrollPanel = new JScrollPane(customerTable);
        add(scrollPanel);

        // Add the return button
        add(extraButtons(), BorderLayout.SOUTH);

        setVisible(true);
    }

    // Extra buttons for the table
    private JPanel extraButtons()
    {
        JPanel extraButtons = new JPanel();

        // Button that exits to the menu
        backToMenu = new JButton("Exit To Menu");
        backToMenu.setSize(150, 30);
        backToMenu.setLocation(340, 450);
        backToMenu.addActionListener(new ActionListener() {
            // Sends user back to the menu
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                CustomerInvoiceList.super.dispose();
            }
        });
        extraButtons.add(backToMenu);

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

                // Reset 'hasNotUpdated' for all vendors
                for (Vendor vendor: VendorAccountArray.vendors)
                    vendor.hasNotUpdated = true;

                new LoginMenu();
                CustomerInvoiceList.super.dispose();
            }
        });
        extraButtons.add(logout);

        return extraButtons;
    }
}
