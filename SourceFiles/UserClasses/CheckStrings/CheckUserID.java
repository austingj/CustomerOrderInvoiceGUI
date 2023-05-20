/**
 * Class made by 'Benjamin Pienta'
 **/

package UserClasses.CheckStrings;

// This class checks a user's string to only have letters and numbers
public class CheckUserID
{
    public static boolean checkID(String userID)
    {
        boolean badID = false;

        // Iterate through the string
        for (int i = 0; i < userID.length(); i++)
        {
            // If any character in the string is not a letter or digit, badID = true;
            if (!(Character.isDigit(userID.charAt(i))) && !(Character.isLetter(userID.charAt(i))))
                badID = true;
        }

        return badID;
    }
}
