package UserClasses;

import ObserverInterface.ObserveVendorSale;

public abstract class User implements ObserveVendorSale
{
    String lastName;
    String firstName;
    String userID;
    String userRole;
    String password;
    boolean firstLogin = true;

    // Getter and Setters
    public String getLastName()
    {
        return lastName;
    }
    public   void setLastName(String lname)
    {
        lastName = lname;
    }

    public String getFirstName()
    {
        return firstName;
    }
    public  void setFirstName(String fname)
    {
        firstName = fname;
    }

    public String getUserRole()
    {
        return userRole;
    }
    public  void setUserRole(String uRole)
    {
        userRole = uRole;
    }

    public String getUserID()
    {
        return userID;
    }
    public void setUserID(String id)
    {
        userID = id;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPassword(String pass)
    {
        password = pass;
    }

    public boolean isFirstLogin()
    {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin)
    {
        this.firstLogin = firstLogin;
    }
}
