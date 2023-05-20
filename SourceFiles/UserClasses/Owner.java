package UserClasses;

import javax.swing.*;

public class Owner extends User
{
    @Override
    public void update(String fullName)
    {
        // Displays the name of the vendor that is having a sale
        JOptionPane.showMessageDialog(null, fullName + " Is Having A Sale!");
    }
}
