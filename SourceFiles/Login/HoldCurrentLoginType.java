/**
 * Class made by 'Benjamin Pienta'
 **/

package Login;

import UserClasses.User;

public class HoldCurrentLoginType
{
    private static User loggedInUser = null;

    public static void updateUser(User user)
    {
        loggedInUser = user;
    }

    public static User getLoggedInUser()
    {
        return loggedInUser;
    }
}
