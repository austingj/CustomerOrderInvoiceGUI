package GUI;

import CustomerOrderInvoice.CustomerInvoice;
import CustomerOrderInvoice.CustomerInvoiceArray;
import CustomerOrderInvoice.CustomerOrder;
import CustomerOrderInvoice.CustomerOrderArray;
import ItemProfile.getItems;
import ProfileUsers.CustomerArray;
import ItemProfile.ItemProfile;
import javax.swing.*;
import ProfileUsers.Customer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
/*
@Author Austin Jeffery
Form for searching through the customer array list
 */
public class SearchCustomers extends JFrame {
    private JButton searchCustomerButton;
    private JPanel SearchCustomerPanel;
    private JTextField takeCustomerName;
    private JLabel CustOrderLabel;
    private JButton createInvoiceButton;
    private JButton returnButton;
    public static ArrayList<ItemProfile> items = new ArrayList<>();
    SearchCustomers() throws IOException {
        setContentPane(SearchCustomerPanel);
        setTitle("Search Form");
        setSize(1600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        items = getItems.get_items();
        final int[] Custorderid = new int[1];
        final CustomerOrder[] coarray = new CustomerOrder[500000];
        final String[] inp = new String[1];

        //Once search button is clicked finds the input from text field
        //set the label to display customer order information
        searchCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = takeCustomerName.getText();
                if(CustomerArray.searchForUser(input)==null) {
                    JOptionPane.showMessageDialog(null, "Customer: " + input + " Not Found");
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Customer: " + input + " has been Found!");
                }
                Customer c = new Customer();
                c = CustomerArray.searchForUser(input);
                int id = c.getUserID();
                CustomerOrder co = new CustomerOrder();
                co = CustomerOrderArray.searchForOrder(id);
                inp[0] = input;
                CustOrderLabel.setText(co.displayCustomerOrder());
                Custorderid[0] = co.getId();
                coarray[id] = co;
            }
        });

        createInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //generate invoice for that customer order
                System.out.println(Custorderid[0]);
                CustomerOrder co = new CustomerOrder();
                int id = Custorderid[0];
                Customer c = new Customer();
                c = CustomerArray.searchForUser(inp[0]);
                co = coarray[id];
                String orderdate = String.valueOf(co.getOrderdate());
                DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                String orDate = formatter.format(new Date(orderdate));
                int customerordernumb = co.getId();
                String items[] = co.getItems();
                int quant[] = co.getQuantity();
                //check if order already has invoice
                if(CustomerInvoiceArray.searchForOrder(id) != null){
                    JOptionPane.showMessageDialog(null, "There is already an invoice for this Customers Order!");
                    return;
                }
                CustomerInvoice invoice = new CustomerInvoice(orDate,customerordernumb,items,quant);
                CustomerInvoiceArray.addCustomerInvoice(invoice);
                double total = invoice.getTotal();
                JOptionPane.showMessageDialog(null, "Customer Invoice has been created!");
                try {
                    new ViewCustomerInvoices(id);
                    c.updateBalance(total);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                return;
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AccountantView();
                SearchCustomers.super.dispose();
            }
        });
    }


}
