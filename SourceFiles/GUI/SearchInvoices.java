package GUI;

import CustomerOrderInvoice.CustomerInvoice;
import CustomerOrderInvoice.CustomerInvoiceArray;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/*
@author Austin Jeffery
 */
public class SearchInvoices extends JFrame{
    private JPanel SearchInvoices;
    private JTextField InvoiceId;
    private JLabel output;
    private JButton searchInvoicesButton;
    private JButton returnButton;
    private JLabel total;
    private JLabel invDate;
    private JLabel OrderDate;
    private JLabel OrderNum;

    public SearchInvoices() {
        setContentPane(SearchInvoices);
        setTitle("Search Form");
        setSize(1600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        //Search for invoices for the given Customer order
        searchInvoicesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(InvoiceId.getText());
                if(CustomerInvoiceArray.searchForOrder(id)==null){
                    JOptionPane.showMessageDialog(null, "Invoice ID: " + id + " Not Found");
                    return;
                }
                CustomerInvoice invoice = new CustomerInvoice();
                invoice = CustomerInvoiceArray.searchForOrder(id);
                try {
                    invoice.DetailsPerItem();
                    output.setText(invoice.DetailsPerItem());
                    total.setText(String.valueOf(invoice.getTotalinvoiceamount()));
                    invDate.setText(String.valueOf(invoice.getInvoiceDate()));
                    OrderDate.setText(String.valueOf(invoice.getOrderDate()));
                    OrderNum.setText(String.valueOf(invoice.getCustOrdernumber()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AccountantView();
                SearchInvoices.super.dispose();
            }
        });
    }
}
