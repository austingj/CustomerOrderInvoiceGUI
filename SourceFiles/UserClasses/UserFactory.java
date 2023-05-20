/**
 * Class made by 'Benjamin Pienta'
 **/

package UserClasses;

public class UserFactory
{
    public static User userFactory(String userType)
    {
        if (userType.equals("Owner"))
            return new Owner();
        else if (userType.equals("Administrator"))
            return new Administrator();
        else if (userType.equals("InventoryManager"))
            return new InventoryManager();
        else if (userType.equals("Purchaser"))
            return new Purchaser();
        else if (userType.equals("Sales Person"))
            return new SalesPerson();
        else if (userType.equals("Accountant"))
            return new Accountant();
        else
            return null;
        /*
        return switch (userType) {
            case "Owner" -> new Owner();
            case "Administrator" -> new Administrator();
            case "InventoryManager" -> new InventoryManager();
            case "Purchaser" -> new Purchaser();
            case "SalesPerson" -> new SalesPerson();
            case "Accountant" -> new Accountant();
            default -> null;
        };
        */
    }
}
