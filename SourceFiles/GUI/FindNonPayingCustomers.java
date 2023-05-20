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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FindNonPayingCustomers extends JFrame
{
    // Main variables for the GUI
    private Container c;
    private JLabel menuTitle;
    private ArrayList<String> nonPayingNames = new ArrayList<>();
    private JTextArea uncooperativeCustomers;

    // Buttons
    private JButton logout;
    private JButton exit;

    // Current date
    Date todaysDate = new Date(2023, Calendar.FEBRUARY,20);

    public FindNonPayingCustomers()
    {
        setTitle("Non-Paying Customers");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Shows everyone who has not paid
        uncooperativeCustomers = new JTextArea();
        uncooperativeCustomers.setSize(500,250);
        uncooperativeCustomers.setLocation(200, 75);
        uncooperativeCustomers.setEditable(false);
        c.add(uncooperativeCustomers);
        initializeNonPayingList();

        // Menu title
        menuTitle = new JLabel("All Customers That Have Not payed");
        menuTitle.setSize(300, 30);
        menuTitle.setLocation(400, 30);
        c.add(menuTitle);

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
                FindNonPayingCustomers.super.dispose();
            }
        });
        c.add(logout);

        // Button that exits to the main menu
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
                FindNonPayingCustomers.super.dispose();
            }
        });
        c.add(exit);

        setVisible(true);
    }

    // Method initializes the list of non-paying customers
    private void initializeNonPayingList()
    {
        Calendar cal = new GregorianCalendar();
        Date after30;
        // For every customer invoice...
        for (CustomerInvoice invoice : CustomerInvoiceArray.customerInvoices)
        {
            // initialize a date that is 30 days after their invoice date
            cal.setTime(invoice.invoiceDate);
            cal.add(Calendar.DAY_OF_MONTH, 30);
            after30 = cal.getTime();

            // If today is 30 days after their invoice date...
            if (todaysDate.after(after30))
            {
                // Find the customer name...
                for (CustomerOrder customerOrder : CustomerOrderArray.customerorders)
                    // ... by comparing customer order id's to the invoice's customer order id
                    if (customerOrder.id == invoice.getCustOrdernumber())
                        // If the customer has not already been added, add them to the array
                        if (!(nonPayingNames.contains(customerOrder.custName)))
                        {
                            nonPayingNames.add(customerOrder.custName);
                            uncooperativeCustomers.append(customerOrder.custName + "\n");
                        }
            }
        }
    }
}
