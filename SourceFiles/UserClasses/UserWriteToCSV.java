/**
 * Class made by 'Benjamin Pienta'
 **/

package UserClasses;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserWriteToCSV
{
    // Write the current list of Users to the user file
    public static void writeUsersToCSV(ArrayList<User> userList)
    {
        String userHeader = "lastName,firstName,userID,userRole,password,firstLogin";

        // Write all the users to the .csv file
        try(FileWriter writeToFile = new FileWriter("Resources/users.csv", false);)
        {
            // Write the header
            writeToFile.write(userHeader);

            for (User user: userList)
                writeToFile.write('\n' + UserCSVFormat.userCSVFormat(user));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
