/**
 * Class made by 'Benjamin Pienta'
 **/

package UserClasses;

import Login.HoldCurrentLoginType;
import Login.HoldPagesVisited;
import Login.LoginMenu;
import Login.MaxCharLimit;
import Main.MainMenu;
import ProfileUsers.Vendor;
import ProfileUsers.VendorAccountArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserSearchPage extends JFrame
{
    private Container c;
    private JLabel menuTitle;
    private JButton selectUser;
    private JButton logout;
    private JButton backToMenu;

    // User Type
    protected JLabel userTypeLabel;
    protected JTextField textUserType;

    // firstName
    protected JLabel firstNameLabel;
    protected JTextField textFirstName;

    // Last Name Variables
    protected JLabel lastNameLabel;
    protected JTextField textLastName;

    // Password Variables
    protected JLabel passwordLabel;
    protected JTextField textPassword;

    // List of Users
    protected JLabel userIDLabel;
    protected JTextField textUserId;

    public UserSearchPage()
    {
        setTitle("Search For user");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Menu title
        menuTitle = new JLabel("Search User");
        menuTitle.setSize(400, 30);
        menuTitle.setLocation(400, 30);
        c.add(menuTitle);

        // Button that selects user to be searched
        selectUser = new JButton("Select");
        selectUser.setSize(150, 30);
        selectUser.setLocation(340, 450);
        selectUser.addActionListener(new ActionListener() {
            // Displays all information of searched user
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String firstName = textFirstName.getText();
                String lastName = textLastName.getText();
                int slotOfUser = UserAccountArray.searchUserByName(firstName, lastName);

                // If the user is found, display information, if not, tell the searcher
                if (slotOfUser != -1)
                {
                    textUserId.setText(UserAccountArray.getUsers().get(slotOfUser).getUserID());
                    textUserType.setText(UserAccountArray.getUsers().get(slotOfUser).getUserRole());
                    textPassword.setText(UserAccountArray.getUsers().get(slotOfUser).getPassword());
                }
                else
                    JOptionPane.showMessageDialog(null, "The " + firstName + " " + lastName + " profile is not found");
            }
        });
        c.add(selectUser);

        // Label for the User Type dropdown box
        userTypeLabel = new JLabel("Account Type");
        userTypeLabel.setSize(150, 30);
        userTypeLabel.setLocation(150, 65);
        c.add(userTypeLabel);

        // Text field for the User Type
        textUserType = new JTextField();
        textUserType.setEditable(false);
        textUserType.setSize(150, 30);
        textUserType.setLocation(150, 90);
        c.add(textUserType);

        // Label for the firstName Box
        firstNameLabel = new JLabel("Enter First Name");
        firstNameLabel.setSize(150, 30);
        firstNameLabel.setLocation(525, 65);
        c.add(firstNameLabel);

        // Textbox for the first name
        textFirstName = new JTextField();
        textFirstName.setSize(200, 30);
        textFirstName.setLocation(525, 90);
        textFirstName.setDocument(new MaxCharLimit(16));
        c.add(textFirstName);

        // Label for the lastName Box
        lastNameLabel = new JLabel("Enter Last Name");
        lastNameLabel.setSize(150, 30);
        lastNameLabel.setLocation(525, 115);
        c.add(lastNameLabel);

        // Textbox for the last name
        textLastName = new JTextField();
        textLastName.setSize(200, 30);
        textLastName.setLocation(525, 140);
        textLastName.setDocument(new MaxCharLimit(16));
        c.add(textLastName);

        // Label for the User ID list
        userIDLabel = new JLabel("User ID");
        userIDLabel.setSize(150, 30);
        userIDLabel.setLocation(150, 130);
        c.add(userIDLabel);

        // Dropbox of Users editable
        textUserId = new JTextField();
        textUserId.setEditable(false);
        textUserId.setSize(200, 30);
        textUserId.setLocation(150, 155);
        c.add(textUserId);

        // Label for the Password box
        passwordLabel = new JLabel("Password");
        passwordLabel.setSize(150, 30);
        passwordLabel.setLocation(150, 180);
        c.add(passwordLabel);

        // Textbox for the Password
        textPassword = new JTextField();
        textPassword.setEditable(false);
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
                UserSearchPage.super.dispose();
            }
        });
        c.add(logout);

        // Button that exits to the menu
        backToMenu = new JButton("Exit To Menu");
        backToMenu.setSize(150, 30);
        backToMenu.setLocation(540, 450);
        backToMenu.addActionListener(new ActionListener() {
            // Sends user back to the menu
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                UserSearchPage.super.dispose();
            }
        });
        c.add(backToMenu);

        setVisible(true);
    }
}
