/**
 * Class made by 'Benjamin Pienta'
 **/

package GUI;

import CustomerOrderInvoice.CustomerOrder;
import CustomerOrderInvoice.CustomerOrderArray;
import Login.HoldCurrentLoginType;
import Login.HoldPagesVisited;
import Login.LoginMenu;
import Main.MainMenu;
import ProfileUsers.Vendor;
import ProfileUsers.VendorAccountArray;
import UserClasses.UserListDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CustomerOrderList extends JFrame
{
    // JFrame variables
    private JTable customerTable;
    private JButton backToMenu;
    private JButton logout;

    public CustomerOrderList(String name)
    {
        setTitle("All Customer Orders for " + name);
        setBounds(200, 90, 1300, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 2D arraylist that will store each customer order's data
        ArrayList<ArrayList<String>> orderInfo = new ArrayList<>();
        int row = 0; // rows of the new 2d arraylist

        // Singular column
        String[] singleColumn = {"Information"};

        // For every customer order for the given customer, add all their details to an array
        for (CustomerOrder customerOrder : CustomerOrderArray.customerorders)
            if (customerOrder.custName.equals(name))
            {
                orderInfo.add(new ArrayList<>());
                String customerOrderInformation = customerOrder.displayCustomerOrder();
                orderInfo.get(row).add(customerOrderInformation);
                row++;
            }

        // Transfer the Arraylist data to the 2D string for ease of adding to the table
        String[][] normal2DStringArray = new String[row][1];

        // Transfer all data from the 2D arraylist to the normal 2D String array
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < 1; j++)
            {
                normal2DStringArray[i][0] = orderInfo.get(i).get(0);
            }
        }

        // Make the table
        customerTable = new JTable(normal2DStringArray, singleColumn);
        customerTable.setSize(1300, 300);

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
                CustomerOrderList.super.dispose();
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
                CustomerOrderList.super.dispose();
            }
        });
        extraButtons.add(logout);

        return extraButtons;
    }
}
