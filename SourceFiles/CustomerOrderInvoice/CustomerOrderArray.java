package CustomerOrderInvoice;
/*
Class for Array of customer Orders to be used across the project
@author Austin Jeffery
 */
public class CustomerOrderArray {
    public static int arraySize = 0;
    public static CustomerOrder[] customerorders = new CustomerOrder[arraySize];

    public int getLength()
    {
        return arraySize;
    }
    public static void addCustomerOrder(CustomerOrder CustomerOrder)
    {
        increaseArraySize();
        customerorders[arraySize - 1] = CustomerOrder;
    }
    private static void increaseArraySize()
    {
        CustomerOrder[] temporaryHold = customerorders;
        arraySize += 1;
        customerorders = new CustomerOrder[arraySize];
        for (int i = 0; i < arraySize - 1; i++)
        {
            customerorders[i] = temporaryHold[i];
        }
    }
    public static CustomerOrder searchForOrder(int id)
    {
        CustomerOrder toBeFound = null;
        for (int i = 0; i < arraySize; i++)
        {
            if (customerorders[i].getId() == id)
            {
                toBeFound = customerorders[i];
            }
        }
        return toBeFound;
    }


}