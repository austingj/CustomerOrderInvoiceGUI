package CustomerOrderInvoice;
/*
Class for Array of customer invoices to be used across the project
@author Austin Jeffery
 */
public class CustomerInvoiceArray {
    public static int arraySize = 0;
    public static CustomerInvoice[] customerInvoices = new CustomerInvoice[arraySize];

    public int getLength()
    {
        return arraySize;
    }
    public static void updateCustomerInvoice(CustomerInvoice customer,int i){
        customerInvoices[i] = customer;
    }
    public static void addCustomerInvoice(CustomerInvoice customerinvoice)
    {
        increaseArraySize();
        customerInvoices[arraySize - 1] = customerinvoice;
    }
    private static void increaseArraySize()
    {
        CustomerInvoice[] temporaryHold = customerInvoices;
        arraySize += 1;
        customerInvoices = new CustomerInvoice[arraySize];
        for (int i = 0; i < arraySize - 1; i++)
        {
            customerInvoices[i] = temporaryHold[i];
        }
    }

    public static CustomerInvoice searchForOrder(int id)
    {
        CustomerInvoice toBeFound = null;
        for (int i = 0; i < arraySize; i++)
        {
            if (customerInvoices[i].getCustOrdernumber() == id)
            {
                toBeFound = customerInvoices[i];
            }
        }
        return toBeFound;
    }


}