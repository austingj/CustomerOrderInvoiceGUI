/**
 * Class made by 'Benjamin Pienta'
 **/

package UserClasses.CheckStrings;

// This class checks if the password input is illegal
public class CheckUserPassword
{
    public static boolean checkPassword(String password)
    {
        boolean badPassword = false;

        // Iterate through the string
        for (int i = 0; i < password.length(); i++)
        {
            // If any character in the string is not a letter or digit, badID = true;
            if (Character.isWhitespace(password.charAt(i)))
                badPassword = true;
        }

        return badPassword;
    }
}
