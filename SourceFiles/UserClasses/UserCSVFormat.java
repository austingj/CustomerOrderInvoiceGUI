/**
 * Class made by 'Benjamin Pienta'
 **/

package UserClasses;

public class UserCSVFormat
{
    public static String userCSVFormat(User user)
    {
        // Store data of the user
        String lastName = user.getLastName();
        String firstName = user.getFirstName();
        String userID = user.getUserID();
        String userRole = user.getUserRole();
        String password = user.getPassword();
        String firstLogin;

        // Assigns true or false as a string depending on if the user has logged in for the first time
        if (user.isFirstLogin())
            firstLogin = "true";
        else
            firstLogin = "false";

        String csvFormat = lastName + "," + firstName + "," + userID + "," + userRole + "," + password + "," + firstLogin;

        return csvFormat;
    }
}
