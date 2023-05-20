/**
 * Class made by 'Benjamin Pienta'
 **/

package Login;

import Main.MainMenu;
import ProfileUsers.Vendor;
import ProfileUsers.VendorAccountArray;
import UserClasses.CheckStrings.CheckUserPassword;
import UserClasses.UserAccountArray;
import UserClasses.UserWriteToCSV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordChange extends JFrame
{
    private Container c;
    private JLabel menuTitle;
    private JButton confirm;
    private JButton logout;
    private JButton backToMenu;

    // Password Variables
    protected JLabel passwordLabel;
    protected JTextField textPassword;
    protected JLabel oldPasswordLabel;
    protected JTextField oldPassword;

    public PasswordChange()
    {
        setTitle("Password Change");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Menu title
        menuTitle = new JLabel("Change password");
        menuTitle.setSize(400, 30);
        menuTitle.setLocation(400, 30);
        c.add(menuTitle);

        // Label for the Old Password box
        oldPasswordLabel = new JLabel("Enter Your OLD Password");
        oldPasswordLabel.setSize(150, 30);
        oldPasswordLabel.setLocation(150, 75);
        c.add(oldPasswordLabel);

        // Textbox for the Old Password
        oldPassword = new JTextField();
        oldPassword.setSize(200, 30);
        oldPassword.setLocation(150, 100);
        oldPassword.setDocument(new MaxCharLimit(16));
        c.add(oldPassword);

        // Label for the Password box
        passwordLabel = new JLabel("Enter NEW Password");
        passwordLabel.setSize(150, 30);
        passwordLabel.setLocation(150, 180);
        c.add(passwordLabel);

        // Textbox for the Password
        textPassword = new JTextField();
        textPassword.setSize(200, 30);
        textPassword.setLocation(150, 205);
        textPassword.setDocument(new MaxCharLimit(16));
        c.add(textPassword);

        // Button that confirms the password change
        confirm = new JButton("Confirm");
        confirm.setSize(150, 30);
        confirm.setLocation(340, 350);
        confirm.addActionListener(new ActionListener() {
            // Sends user to page that shows all the actions they can perform
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Variable from input
                String passwordInput = textPassword.getText();
                String oldPasswordInput = oldPassword.getText();

                // Check if the password input is legal
                if (!CheckUserPassword.checkPassword(passwordInput))
                {
                    // Check if the old password inputted is correct
                    if (oldPasswordInput.equals(HoldCurrentLoginType.getLoggedInUser().getPassword()))
                    {
                        // If the password is long enough, change the user password
                        if (passwordInput.length() >= 8)
                        {

                            // Ensure the user is not trying to re-enter their old password
                            if (!HoldCurrentLoginType.getLoggedInUser().getPassword().equals(passwordInput))
                            {
                                // Update user in array and in .csv
                                HoldCurrentLoginType.getLoggedInUser().setPassword(passwordInput);
                                UserWriteToCSV.writeUsersToCSV(UserAccountArray.getUsers());

                                new MainMenu();
                                PasswordChange.super.dispose();
                            }
                            else
                                JOptionPane.showMessageDialog(null, "The new password is the same as the old password");
                        }
                        else
                            JOptionPane.showMessageDialog(null, "The new password is too short");
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Enter in your Correct Original Password");
                }
                else
                    JOptionPane.showMessageDialog(null, "There cannot be spaces in the password");
            }
        });
        c.add(confirm);

        // This is here so that when a new user logs in, they are forced to change their password
        if (HoldPagesVisited.getNumberOfPagesVisited() != 0)
        {
            // Button that exits to the menu
            backToMenu = new JButton("Exit To Menu");
            backToMenu.setSize(150, 30);
            backToMenu.setLocation(340, 450);
            backToMenu.addActionListener(new ActionListener() {
                // Sends user back to the menu
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MainMenu();
                    PasswordChange.super.dispose();
                }
            });
            c.add(backToMenu);

            // Button that logs the user out
            logout = new JButton("Log Out");
            logout.setSize(150, 30);
            logout.setLocation(700, 20);
            logout.addActionListener(new ActionListener() {
                // Logs the user out and sends them to the login screen
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    HoldCurrentLoginType.updateUser(null);
                    HoldPagesVisited.resetNumberOfPagesVisited();

                    // Reset 'hasNotUpdated' for all vendors
                    for (Vendor vendor: VendorAccountArray.vendors)
                        vendor.hasNotUpdated = true;

                    new LoginMenu();
                    PasswordChange.super.dispose();
                }
            });
            c.add(logout);
        }

        setVisible(true);
    }
}
