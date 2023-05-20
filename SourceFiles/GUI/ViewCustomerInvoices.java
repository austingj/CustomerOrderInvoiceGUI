package GUI;

import CustomerOrderInvoice.CustomerInvoice;
import CustomerOrderInvoice.CustomerInvoiceArray;

import javax.swing.*;
import java.io.IOException;

public class ViewCustomerInvoices extends JFrame {
    private JPanel ViewCustomerInvoice;
    private JLabel invoiceL;


    ViewCustomerInvoices(int id) throws IOException {
        setContentPane(ViewCustomerInvoice);
        setTitle("Customer Invoice Form");
        setSize(1600, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        CustomerInvoice invoice = new CustomerInvoice();
        invoice = CustomerInvoiceArray.searchForOrder(id);
        invoice.DetailsPerItem();
        invoiceL.setText(invoice.DetailsPerItem());
    }
}
