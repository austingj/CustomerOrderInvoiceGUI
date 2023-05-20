/**
 * Class made by 'Benjamin Pienta'
 **/

package UserClasses;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class GetUsersFromCSV
{
    public static void getUsersFromCSV()
    {
        ArrayList<User> userList = new ArrayList<>();
        String line = "";
        try
        {
            // Initialize the reader
            BufferedReader csvReader = new BufferedReader(new FileReader("Resources/users.csv"));
            csvReader.readLine(); // Skip the header of the file

            while ((line = csvReader.readLine()) != null)
            {
                // Store variables of csv in a string
                String[] variables = line.split(",");

                // Initialize all variables for the User class
                String lastName = variables[0];
                String firstName = variables[1];
                String userID = variables[2];
                String userRole = variables[3];
                String password = variables[4];

                // Initialize the firstLogin boolean for the user class
                boolean firstLogin;
                if (variables[5].equals("true"))
                    firstLogin = true;
                else
                    firstLogin = false;

                // Initialize all variables to the user object
                User user = UserFactory.userFactory(userRole);
                user.setLastName(lastName);
                user.setFirstName(firstName);
                user.setUserID(userID);
                user.setUserRole(userRole);
                user.setPassword(password);
                user.setFirstLogin(firstLogin);

                // Add the new user to the array
                UserAccountArray.addUser(user);
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


    }
}
