/**
 * Class made by 'Benjamin Pienta'
 **/

package Login;

import Main.MainMenu;
import UserClasses.User;
import UserClasses.UserAccountArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu extends JFrame
{
    private Container c;
    private JLabel menuTitle;
    private JButton confirm;

    // User ID
    protected JLabel userID;
    protected JTextField textUserID;

    // Password
    protected JLabel password;
    protected JTextField textPassword;

    public LoginMenu() {
        setTitle("Login Page");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Menu title
        menuTitle = new JLabel("Login");
        menuTitle.setSize(300, 30);
        menuTitle.setLocation(400, 30);
        c.add(menuTitle);

        // Button that confirms the login of the user
        confirm = new JButton("Confirm");
        confirm.setSize(150, 30);
        confirm.setLocation(340, 350);
        confirm.addActionListener(new ActionListener() {
            // Sends user to page that shows all the actions they can perform
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String idInput = textUserID.getText();

                // Turn password into a string
                String passwordInput = textPassword.getText();

                // Ensure that the UserID is in the array
                if (!UserAccountArray.searchUserID(idInput))
                    JOptionPane.showMessageDialog(null, "User ID is not on file");
                else
                {
                    if (!UserAccountArray.searchUserPassword(passwordInput, idInput))
                        JOptionPane.showMessageDialog(null, "This password is incorrect");
                    else
                    {
                        // These two lines are to test login functionality to the Main Menu.
                        // It also tests to see if the Main Menu only reveals certain buttons to certain user types
                        User testUser = UserAccountArray.searchForUser(idInput, passwordInput);
                        HoldCurrentLoginType.updateUser(testUser);
                        MainMenu.setUserType(HoldCurrentLoginType.getLoggedInUser());

                        // Opens the Main Menu
                        new MainMenu();
                        LoginMenu.super.dispose();
                    }
                }
            }
        });
        c.add(confirm);

        // Label for the User ID box
        userID = new JLabel("Enter User ID");
        userID.setSize(150, 30);
        userID.setLocation(150, 65);
        c.add(userID);

        // Textbox box to insert ID
        textUserID = new JTextField();
        textUserID.setSize(200, 30);
        textUserID.setLocation(150, 90);
        textUserID.setDocument(new MaxCharLimit(6));
        c.add(textUserID);

        // Label for the Password Box
        password = new JLabel("Enter Password");
        password.setSize(150, 30);
        password.setLocation(525, 65);
        c.add(password);

        // Textbox to enter the password
        textPassword = new JTextField();
        textPassword.setSize(200, 30);
        textPassword.setLocation(525, 90);
        textPassword.setDocument(new MaxCharLimit(16));
        c.add(textPassword);

        setVisible(true);
    }
}
