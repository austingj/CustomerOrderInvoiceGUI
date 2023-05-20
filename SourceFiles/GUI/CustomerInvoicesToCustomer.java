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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CustomerInvoicesToCustomer extends JFrame
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

    public CustomerInvoicesToCustomer()
    {
        initializeInvoiceList();
        setTitle("Customers with Customer Invoices");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Menu title
        menuTitle = new JLabel("Customers with Invoices");
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
                CustomerInvoicesToCustomer.super.dispose();
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
                CustomerInvoicesToCustomer.super.dispose();
            }
        });
        c.add(exit);

        // Button that sends the user to see all invoices for that customer
        select = new JButton("Select User");
        select.setSize(150, 30);
        select.setLocation(340, 350);
        select.addActionListener(new ActionListener()
        {
            // Sends user to see all invoices for that customer
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String customerName = (String)choosableCustomers.getSelectedItem();
                try {
                    new CustomerInvoiceList(customerName);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                CustomerInvoicesToCustomer.super.dispose();
            }
        });
        c.add(select);

        setVisible(true);
    }

    // Method initializes the list of non-paying customers
    private void initializeInvoiceList()
    {
        // For every customer invoice...
        for (CustomerInvoice invoice : CustomerInvoiceArray.customerInvoices)
        {
            // Find the customer name...
            for (CustomerOrder customerOrder : CustomerOrderArray.customerorders)
                // ... by comparing customer order id's to the invoice's customer order id
                if (customerOrder.id == invoice.getCustOrdernumber())
                    // If the customer has not already been added, add them to the array
                    if (!(customersToChoose.contains(customerOrder.custName)))
                    {
                        customersToChoose.add(customerOrder.custName);
                    }
        }
    }
}
