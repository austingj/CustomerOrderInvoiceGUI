/**
 * Class made by 'Benjamin Pienta'
 **/

package UserClasses;

import java.util.ArrayList;

public class UserAccountArray
{
    private static ArrayList<User> users = new ArrayList<User>();

    // Adds a user to the User Array
    public static void addUser(User user)
    {
        users.add(user);
    }

    // Returns a specific user from the array
    public static User searchForUser(String id, String password)
    {
        User toBeFound = null;

        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getUserID().equals(id))
                if(users.get(i).getPassword().equals(password))
                    toBeFound = users.get(i);
        }

        return toBeFound;
    }

    // Returns true if the ID is found
    public static boolean searchUserID(String id)
    {
        boolean foundID = false;

        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getUserID().equals(id))
                foundID = true;

        return foundID;
    }

    // Returns true if the password is found
    public static boolean searchUserPassword(String password, String id)
    {
        boolean foundID = false;

        for (int i = 0; i < users.size(); i++)
            if (users.get(i).getPassword().equals(password) && users.get(i).getUserID().equals(id))
                foundID = true;

        return foundID;
    }

    // Returns the place in the array that a given User is in
    public static int getUserPlace(String id)
    {
        int placeInArray = 0;

        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getUserID().equals(id))
                placeInArray = i;
        }

        return placeInArray;
    }

    // Deletes a user at a given spot
    public static void removeUser(int slot)
    {
        users.remove(slot);
    }

    // Returns the User ArrayList
    public static ArrayList<User> getUsers()
    {
        return users;
    }

    // Edits a user with the given parameters
    public static void editUser(int slot, User user)
    {
        removeUser(slot);
        users.add(user);
    }

    // Searches for a user by a given name
    public static int searchUserByName(String firstName, String lastName)
    {
        int userFound = -1;

        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getFirstName().equals(firstName) && users.get(i).getLastName().equals(lastName))
                userFound = i;
        }

        return userFound;
    }
}
