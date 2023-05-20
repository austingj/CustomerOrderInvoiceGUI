/**
 * Class made by 'Benjamin Pienta'
 **/

package UserClasses;
import Login.*;
import Main.MainMenu;
import ProfileUsers.Vendor;
import ProfileUsers.VendorAccountArray;
import UserClasses.CheckStrings.CheckUserID;
import UserClasses.CheckStrings.CheckUserPassword;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserCreationPage extends JFrame
{
    private Container c;
    private JLabel menuTitle;
    private JButton confirm;
    private JButton logout;
    private JButton backToMenu;

    // User Type
    protected JLabel userType;
    protected JComboBox userTypeList;

    // firstName
    protected JLabel firstName;
    protected JTextField textFirstName;

    // Last Name Variables
    protected JLabel lastName;
    protected JTextField textLastName;

    // User ID Variables
    protected JLabel userID;
    protected JTextField textUserID;

    // Password Variables
    protected JLabel password;
    protected JTextField textPassword;

    // Lists for the JComboBox for owners and Administrators respectively
    private String userTypeListOwner[]
            = {"Owner", "Administrator", "Inventory Manager", "Accountant", "Purchaser", "Sales Person"};
    private String userTypeListAdministrator[]
            = {"Inventory Manager", "Accountant", "Purchaser", "Sales Person"};

    public UserCreationPage() {
        setTitle("Create User Profile");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Menu title
        menuTitle = new JLabel("Create Account");
        menuTitle.setSize(300, 30);
        menuTitle.setLocation(400, 30);
        c.add(menuTitle);

        // Button that confirms the submission of the new user
        confirm = new JButton("Confirm");
        confirm.setSize(150, 30);
        confirm.setLocation(340, 350);
        confirm.addActionListener(new ActionListener() {
            // Sends user to page that shows all the actions they can perform
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Variables from input
                String firstNameInput = textFirstName.getText();
                String lastNameInput = textLastName.getText();
                String userRoleInput = (String)userTypeList.getSelectedItem();
                String passwordInput = textPassword.getText();
                String userIDInput = textUserID.getText();

                // Check if the user id input is legal
                if (!CheckUserID.checkID(userIDInput))
                {
                    // Check that the password is legal
                    if (!CheckUserPassword.checkPassword(passwordInput))
                    {
                        // check if the password input is large enough
                        if (passwordInput.length() >= 8)
                        {
                            // Make sure the user id is not already taken
                            if (!UserAccountArray.searchUserID(userIDInput) && !userIDInput.equals(""))
                            {
                                // Initialize the new user being made
                                User user = UserFactory.userFactory(userRoleInput);
                                user.setUserID(userIDInput);
                                user.setFirstName(firstNameInput);
                                user.setLastName(lastNameInput);
                                user.setPassword(passwordInput);
                                user.setUserRole(userRoleInput);
                                user.setFirstLogin(true); // When a new user is made, they will have never logged in

                                // Add the user to the array and write them to the .csv
                                UserAccountArray.addUser(user);
                                UserWriteToCSV.writeUsersToCSV(UserAccountArray.getUsers());

                                // Opens the Main Menu
                                new MainMenu();
                                UserCreationPage.super.dispose();
                            }
                            else
                                JOptionPane.showMessageDialog(null, "User ID already exists or is blank");
                        }
                        else
                            JOptionPane.showMessageDialog(null, "The password is too short");
                    }
                    else
                        JOptionPane.showMessageDialog(null, "There cannot be spaces in the password");
                }
                else
                    JOptionPane.showMessageDialog(null, "The User ID input is not just letters and digits");

            }
        });
        c.add(confirm);

        // Label for the User Type dropdown box
        userType = new JLabel("Account Type");
        userType.setSize(150, 30);
        userType.setLocation(150, 65);
        c.add(userType);

        // Dropdown list for the User Types
        if (HoldCurrentLoginType.getLoggedInUser() instanceof Owner)
        {
            userTypeList = new JComboBox<>(userTypeListOwner);
            userTypeList.setSize(125, 30);
            userTypeList.setLocation(150, 100);
            c.add(userTypeList);
        }
        else if (HoldCurrentLoginType.getLoggedInUser() instanceof Administrator)
        {
            userTypeList = new JComboBox<>(userTypeListAdministrator);
            userTypeList.setSize(125, 30);
            userTypeList.setLocation(150, 100);
            c.add(userTypeList);
        }

        // Label for the firstName Box
        firstName = new JLabel("Enter First Name");
        firstName.setSize(150, 30);
        firstName.setLocation(525, 65);
        c.add(firstName);

        // Textbox for the first name
        textFirstName = new JTextField();
        textFirstName.setSize(200, 30);
        textFirstName.setLocation(525, 90);
        textFirstName.setDocument(new MaxCharLimit(15));
        c.add(textFirstName);

        // Label for the lastName Box
        lastName = new JLabel("Enter Last Name");
        lastName.setSize(150, 30);
        lastName.setLocation(525, 115);
        c.add(lastName);

        // Textbox for the last name
        textLastName = new JTextField();
        textLastName.setSize(200, 30);
        textLastName.setLocation(525, 140);
        textLastName.setDocument(new MaxCharLimit(15));
        c.add(textLastName);

        // Label for the User ID box
        userID = new JLabel("Enter User ID");
        userID.setSize(250, 30);
        userID.setLocation(150, 130);
        c.add(userID);

        // Textbox for the User ID
        textUserID = new JTextField();
        textUserID.setSize(200, 30);
        textUserID.setLocation(150, 155);
        textUserID.setDocument(new MaxCharLimit(6));
        c.add(textUserID);

        // Label for the Password box
        password = new JLabel("Enter Password");
        password.setSize(150, 30);
        password.setLocation(150, 180);
        c.add(password);

        // Textbox for the Password
        textPassword = new JTextField();
        textPassword.setSize(200, 30);
        textPassword.setLocation(150, 205);
        textPassword.setDocument(new MaxCharLimit(16));
        c.add(textPassword);

        // Button that logs the user out
        logout = new JButton("Log Out");
        logout.setSize(150, 30);
        logout.setLocation(700, 20);
        logout.addActionListener(new ActionListener() {
            // Test button closes the menu
            @Override
            public void actionPerformed(ActionEvent e)
            {
                HoldCurrentLoginType.updateUser(null);
                HoldPagesVisited.resetNumberOfPagesVisited();

                for (Vendor vendor: VendorAccountArray.vendors)
                    vendor.hasNotUpdated = true;

                new LoginMenu();
                UserCreationPage.super.dispose();
            }
        });
        c.add(logout);

        // Button that exits to the menu
        backToMenu = new JButton("Exit To Menu");
        backToMenu.setSize(150, 30);
        backToMenu.setLocation(340, 450);
        backToMenu.addActionListener(new ActionListener() {
            // Sends user back to the menu
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                UserCreationPage.super.dispose();
            }
        });
        c.add(backToMenu);

        setVisible(true);
    }
}
