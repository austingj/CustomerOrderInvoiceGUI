/**
 * Class made by 'Benjamin Pienta'
 **/

package GUI;

import CustomerOrderInvoice.CustomerInvoice;
import CustomerOrderInvoice.CustomerInvoiceArray;
import ItemProfile.ItemProfile;
import Login.HoldCurrentLoginType;
import Login.HoldPagesVisited;
import Login.LoginMenu;
import Main.Main;
import Main.MainMenu;
import ProfileUsers.Vendor;
import ProfileUsers.VendorAccountArray;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FindItemProfits extends JFrame
{
    private JPanel jPanel;
    private JButton logOutButton;
    private JButton exitToMenuButton;
    private JDatePicker firstDate;
    private JDatePicker secondDate;
    private JComboBox itemComboBox;
    private JButton selectItemButton;
    DateFormat formatDate = new SimpleDateFormat("MM/dd/yyyy");

    // Variables for items array
    private int arraySize = 0;
    private String[] itemNames;
    private boolean alreadyExists;

    public FindItemProfits()
    {
        setTitle("Find Item Profits");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(jPanel);
        setResizable(false);
        addItems();

        for (int i = 0; i < arraySize; i++)
            itemComboBox.insertItemAt(itemNames[i], i);

        setVisible(true);

        selectItemButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String selectedItem = (String)itemComboBox.getSelectedItem();
                String firstDateString = (firstDate.getModel().getMonth() + 1) + "/" + (firstDate.getModel().getDay()) + "/" + (firstDate.getModel().getYear());
                String secondDateString = (secondDate.getModel().getMonth() + 1) + "/" + (secondDate.getModel().getDay()) + "/" + (secondDate.getModel().getYear());;

                Date firstSelectedDate = new Date();
                Date secondSelectedDate = new Date();

                // Make the selected dates in date format
                try
                {
                    firstSelectedDate = formatDate.parse(firstDateString);
                } catch (ParseException ex)
                {
                    ex.printStackTrace();
                }
                try
                {
                    secondSelectedDate = formatDate.parse(secondDateString);
                } catch (ParseException ex)
                {
                    ex.printStackTrace();
                }

                // Make sure all fields are set
                if (selectedItem ==  null)
                    JOptionPane.showMessageDialog(null, "Select an Item!");
                else
                {
                    if (firstSelectedDate.after(secondSelectedDate) || firstSelectedDate.equals(secondSelectedDate))
                        JOptionPane.showMessageDialog(null, "First Date must be BEFORE Second Date");
                    else
                    {
                        int qty = 0;
                        double price = 0;
                        int placeInArray = 0;
                        double profit = 0;

                        for (CustomerInvoice customerInvoice : CustomerInvoiceArray.customerInvoices)
                        {
                            if (!(customerInvoice.invoiceDate.before(firstSelectedDate) || customerInvoice.invoiceDate.after(secondSelectedDate)))
                            {
                                placeInArray = 0;
                                for (String item : customerInvoice.itemname)
                                {
                                    if (item.equals(selectedItem))
                                    {
                                        qty = customerInvoice.quantity[placeInArray];
                                        for (ItemProfile itemProfile : Main.items)
                                            if (itemProfile.getItemName().equals(selectedItem))
                                                price = itemProfile.getSellingPrice();
                                        profit = qty * price;
                                    }
                                    placeInArray++;
                                }

                                // Show in a pop-up the total profit from that price
                                JOptionPane.showMessageDialog(null, "The profit for " + selectedItem + " is : " + profit);
                            }
                            else
                                JOptionPane.showMessageDialog(null, "There were no profits for the range");
                        }
                    }
                }
            }
        });

        // Sends the user back to the main menu
        exitToMenuButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new MainMenu();
                FindItemProfits.super.dispose();
            }
        });


        // Logs the user out of the program
        logOutButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                HoldCurrentLoginType.updateUser(null);
                HoldPagesVisited.resetNumberOfPagesVisited();

                for (Vendor vendor: VendorAccountArray.vendors)
                    vendor.hasNotUpdated = true;

                new LoginMenu();
                FindItemProfits.super.dispose();
            }
        });
    }

    private String[] addItems()
    {
        for (CustomerInvoice customerInvoice : CustomerInvoiceArray.customerInvoices)
        {
            for (String item : customerInvoice.itemname)
            {
                if (arraySize == 0)
                {
                    arraySize++;
                    itemNames = new String[arraySize];
                    itemNames[0] = item;
                }
                else
                {
                    if (!foundItem(item))
                    {
                        arraySize++;
                        String[] oldList = itemNames;
                        itemNames = new String[arraySize];
                        recreateArray(oldList);
                        itemNames[arraySize - 1] = item;
                    }
                }
            }
        }

        return itemNames;
    }

    private boolean foundItem(String itemName)
    {
        alreadyExists = false;

        for (String name : itemNames)
        {
            if (name.equals(itemName))
            {
                alreadyExists = true;
                return alreadyExists;
            }
        }

        return alreadyExists;
    }

    private void recreateArray(String[] oldList)
    {
        String[] newArray = oldList;

        for (int i = 0; i < arraySize - 1; i++)
        {
            itemNames[i] = newArray[i];
        }
    }
}
