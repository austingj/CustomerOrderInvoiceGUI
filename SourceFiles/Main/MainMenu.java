/**
 * Class made by 'Benjamin Pienta'
 **/

package Main;

import CustomerOrderInvoice.CustomerInvoice;
import CustomerOrderInvoice.CustomerInvoiceArray;
import GUI.*;
import Login.PasswordChange;
import Login.HoldCurrentLoginType;
import Login.HoldPagesVisited;
import Login.LoginMenu;
import ObserverInterface.ObserveVendorSale;
import ProfileUsers.Vendor;
import ProfileUsers.VendorAccountArray;
import UserClasses.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class MainMenu extends JFrame
{
    // Main variables for the GUI
    private Container c;
    private JLabel menuTitle;
    private JButton exit;
    private JButton addUser;
    private JButton editUser;
    private JButton searchUser;
    private JButton logout;
    private JButton changePassword;
    private JButton showAllUsers;
    private JButton purchaserView;
    private JButton itemMenu;
    private JButton accountantView;
    private JButton itemProfits;
    private JButton nonPaying;
    private JButton allCustomersWithOrders;
    private JButton allCustomersWithInvoices;
    private static User currentUser = null;

    // Variable to store the date
    Date todaysDate = new Date();

    public MainMenu()
    {
        setTitle("Main Menu");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Menu title
        menuTitle = new JLabel("Menu");
        menuTitle.setSize(300, 30);
        menuTitle.setLocation(400, 30);
        c.add(menuTitle);

        // Button that exits the program
        exit = new JButton("Exit");
        exit.setSize(150, 30);
        exit.setLocation(340, 350);
        exit.addActionListener(new ActionListener()
        {
            // Closes the menu
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MainMenu.super.dispose();
            }
        });
        c.add(exit);

        // If Owners or Administrators are logged in, reveal these buttons
        if (currentUser instanceof Owner || currentUser instanceof Administrator)
        {
            // Button that takes allowed users to the 'Add User' page
            addUser = new JButton("Add User");
            addUser.setSize(150, 30);
            addUser.setLocation(340, 250);
            addUser.addActionListener(new ActionListener()
            {
                // Test button closes the menu
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    HoldPagesVisited.incrementPagesVisited();
                    new UserCreationPage();
                    MainMenu.super.dispose();
                }
            });
            c.add(addUser);

            // Button that takes allowed users to the 'Edit User' page
            editUser = new JButton("Edit User");
            editUser.setSize(150, 30);
            editUser.setLocation(340, 150);
            editUser.addActionListener(new ActionListener()
            {
                // Test button closes the menu
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    HoldPagesVisited.incrementPagesVisited();
                    new UserEditPage();
                    MainMenu.super.dispose();
                }
            });
            c.add(editUser);

            // Button that takes allowed users to the 'Search User' page
            searchUser = new JButton("Search User");
            searchUser.setSize(150, 30);
            searchUser.setLocation(140, 150);
            searchUser.addActionListener(new ActionListener()
            {
                // Opens the Search User page
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    HoldPagesVisited.incrementPagesVisited();
                    new UserSearchPage();
                    MainMenu.super.dispose();
                }
            });
            c.add(searchUser);

            // Button that shows all users employed
            showAllUsers = new JButton("Show Users");
            showAllUsers.setSize(150, 30);
            showAllUsers.setLocation(100, 500);
            showAllUsers.addActionListener(new ActionListener()
            {
                // Open User list
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    HoldPagesVisited.incrementPagesVisited();
                    new UserListDisplay();
                    MainMenu.super.dispose();
                }
            });
            c.add(showAllUsers);
        }

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
                MainMenu.super.dispose();
            }
        });
        c.add(logout);

        // Button changes the user's password upon use
        changePassword = new JButton("Change Password");
        changePassword.setSize(150, 30);
        changePassword.setLocation(700, 500);
        changePassword.addActionListener(new ActionListener()
        {
            // Changes the User's password
            @Override
            public void actionPerformed(ActionEvent e)
            {
                HoldPagesVisited.incrementPagesVisited();
                new PasswordChange();
                MainMenu.super.dispose();
            }
        });
        c.add(changePassword);

        if (HoldCurrentLoginType.getLoggedInUser() instanceof Owner || HoldCurrentLoginType.getLoggedInUser() instanceof Purchaser)
        {
            // Button that sends the user to the purchaser view
            purchaserView = new JButton("Vendor Actions");
            purchaserView.setSize(150, 30);
            purchaserView.setLocation(100, 250);
            purchaserView.addActionListener(new ActionListener()
            {
                // Open the purchaser view
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    HoldPagesVisited.incrementPagesVisited();
                    new PurchaserView();
                    MainMenu.super.dispose();
                }
            });
            c.add(purchaserView);

            // Button that sends the user to the items menu
            itemMenu = new JButton("Item Actions");
            itemMenu.setSize(150, 30);
            itemMenu.setLocation(100, 350);
            itemMenu.addActionListener(new ActionListener()
            {
                // Open the purchaser view
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    HoldPagesVisited.incrementPagesVisited();
                    new ItemMenuGUI("Items Menu");
                    MainMenu.super.dispose();
                }
            });
            c.add(itemMenu);
        }

        if (HoldCurrentLoginType.getLoggedInUser() instanceof Owner || HoldCurrentLoginType.getLoggedInUser() instanceof Accountant)
        {
            // Button that sends the user to the accountant view
            accountantView = new JButton("Accountant Actions");
            accountantView.setSize(150, 30);
            accountantView.setLocation(600, 250);
            accountantView.addActionListener(new ActionListener()
            {
                // Open the accountant view
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    HoldPagesVisited.incrementPagesVisited();
                    new AccountantView();
                    MainMenu.super.dispose();
                }
            });
            c.add(accountantView);
        }

        if (HoldCurrentLoginType.getLoggedInUser() instanceof Owner)
        {
            // Button that exits the program
            itemProfits = new JButton("Item Profits");
            itemProfits.setSize(150, 30);
            itemProfits.setLocation(600, 350);
            itemProfits.addActionListener(new ActionListener() {
                // Test button closes the menu
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (CustomerInvoiceArray.customerInvoices.length == 0)
                        JOptionPane.showMessageDialog(null, "There Are No Invoices to Track Profits With");
                    else
                    {
                        new FindItemProfits();
                        MainMenu.super.dispose();
                    }
                }
            });
            c.add(itemProfits);

            // Button that sends the user to see all the baaaad customers
            nonPaying = new JButton("Non-Paying Customers");
            nonPaying.setSize(200, 30);
            nonPaying.setLocation(600, 150);
            nonPaying.addActionListener(new ActionListener()
            {
                // Open the list of shame
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (CustomerInvoiceArray.customerInvoices.length == 0)
                        JOptionPane.showMessageDialog(null, "There Are No Invoices to See Who Hasn't Paid");
                    else
                    {
                        HoldPagesVisited.incrementPagesVisited();
                        new FindNonPayingCustomers();
                        MainMenu.super.dispose();
                    }
                }
            });
            c.add(nonPaying);

            // Button that sends the user to select a customer to find all orders to the customer
            allCustomersWithOrders = new JButton("Find Customer Orders");
            allCustomersWithOrders.setSize(200, 30);
            allCustomersWithOrders.setLocation(340, 450);
            allCustomersWithOrders.addActionListener(new ActionListener()
            {
                // Sends user to map all orders to a customer
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    new CustomerOrdersToCustomer();
                    MainMenu.super.dispose();
                }
            });
            c.add(allCustomersWithOrders);
        }

        if (HoldCurrentLoginType.getLoggedInUser() instanceof Purchaser)
        {
            //add create purchase order button
            JButton CreatePurchaseOrder = new JButton("Create Purchase Order");
            CreatePurchaseOrder.setSize(250,30);
            CreatePurchaseOrder.setLocation(300,400);
            c.add(CreatePurchaseOrder);
            CreatePurchaseOrder.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    c.hide();
                    JFrame PurchaseOrderMenu = new PurchaseOrderMenuGUI("Purchase Order");
                    PurchaseOrderMenu.setVisible(true);
                    MainMenu.super.dispose();
                }
            });


        }

        if (HoldCurrentLoginType.getLoggedInUser() instanceof Accountant)
        {
            // Button that sends the user to select a customer to find all orders to the customer
            allCustomersWithInvoices = new JButton("Find Customer Invoices");
            allCustomersWithInvoices.setSize(200, 30);
            allCustomersWithInvoices.setLocation(340, 450);
            allCustomersWithInvoices.addActionListener(new ActionListener()
            {
                // Sends user to map all orders to a customer
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (CustomerInvoiceArray.customerInvoices.length == 0)
                        JOptionPane.showMessageDialog(null, "There Are No Invoices to See See a List Of!");
                    else
                    {
                        new CustomerInvoicesToCustomer();
                        MainMenu.super.dispose();
                    }
                }
            });
            c.add(allCustomersWithInvoices);
        }

        setVisible(true);

        // If this is the first time a user has used the system, send them to change their password
        // and update their profile
        if (HoldCurrentLoginType.getLoggedInUser().isFirstLogin())
        {
            new PasswordChange();

            // Change firstLogin to false for the user
            for (User user: UserAccountArray.getUsers())
                if (HoldCurrentLoginType.getLoggedInUser() == user)
                {
                    user.setFirstLogin(false);
                }

            UserWriteToCSV.writeUsersToCSV(UserAccountArray.getUsers());
            MainMenu.super.dispose();
        }

        // Having the conditional after the 'setVisible' loads the menu THEN shows
        // the pop-ups
        if (HoldPagesVisited.getNumberOfPagesVisited() == 0)
        {
            // If the logged-in user is meant to be updated, tell that user a sale is occurring.
            for (Vendor vendor : VendorAccountArray.vendors)
                for (ObserveVendorSale observe : vendor.saleObservers)
                    if (HoldCurrentLoginType.getLoggedInUser() == observe && todaysDate.after(vendor.getSeasonalDiscount()))
                        vendor.updateSaleObservers();
        }
    }

    public static void setUserType(User user)
    {
        currentUser = user;
    }
}
