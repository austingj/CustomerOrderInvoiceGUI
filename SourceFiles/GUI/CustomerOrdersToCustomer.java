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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerOrdersToCustomer extends JFrame
{
    // JFrame variables
    private Container c;
    private JLabel menuTitle;
    private JComboBox choosableCustomers;
    private JButton logout;
    private JButton exit;
    private JButton select;

    // List to store all names with orders
    private ArrayList<String> customersToChoose = new ArrayList<>();

    public CustomerOrdersToCustomer()
    {
        initializeValidCustomers();
        setTitle("Customers with Customer Orders");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Menu title
        menuTitle = new JLabel("Customers with Orders");
        menuTitle.setSize(400, 30);
        menuTitle.setLocation(400, 30);
        c.add(menuTitle);


        // Dropbox of choosable people
        choosableCustomers = new JComboBox<>();
        choosableCustomers.setSize(125, 30);
        choosableCustomers.setLocation(150, 100);
        c.add(choosableCustomers);
        for (String names : customersToChoose)
            choosableCustomers.addItem(names);

        // Button that logs the user out
        logout = new JButton("Log Out");
        logout.setSize(150, 30);
        logout.setLocation(700, 20);
        logout.addActionListener(new ActionListener()
        {
            // Logs the user out and sends them to the login screen
            @Override
            public void actionPerformed(ActionEvent e)
            {
                HoldCurrentLoginType.updateUser(null);
                HoldPagesVisited.resetNumberOfPagesVisited();

                for (Vendor vendor: VendorAccountArray.vendors)
                    vendor.hasNotUpdated = true;

                new LoginMenu();
                CustomerOrdersToCustomer.super.dispose();
            }
        });
        c.add(logout);

        // Button that sends the user to the main menu
        exit = new JButton("Exit to Main Menu");
        exit.setSize(150, 30);
        exit.setLocation(340, 450);
        exit.addActionListener(new ActionListener()
        {
            // Sends user to main menu
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new MainMenu();
                CustomerOrdersToCustomer.super.dispose();
            }
        });
        c.add(exit);

        // Button that exits the program
        select = new JButton("Select User");
        select.setSize(150, 30);
        select.setLocation(340, 350);
        select.addActionListener(new ActionListener()
        {
            // Closes the menu
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String customerName = (String)choosableCustomers.getSelectedItem();
                new CustomerOrderList(customerName);
                CustomerOrdersToCustomer.super.dispose();
            }
        });
        c.add(select);

        setVisible(true);
    }

    private void initializeValidCustomers()
    {
        for (CustomerOrder customerOrder : CustomerOrderArray.customerorders)
            if (!(customersToChoose.contains(customerOrder.custName)))
            {
                customersToChoose.add(customerOrder.custName);
            }
    }
}
