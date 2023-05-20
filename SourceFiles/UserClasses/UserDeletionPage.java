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

/*
This class was originally going to just extend the UserEditPage and have required tweaks,
but the program kept messing up when an administrator tried to use this page...
But for some reason copying over the edit page and making the changes needed there into here works just fine for some reason...
I really don't know...
 */
public class UserDeletionPage extends JFrame
{
    protected Container c;
    protected JLabel menuTitle;
    protected JButton selectUser;
    protected JButton deleteUser;
    protected JButton logout;
    protected JButton backToMenu;
    protected int userSlotInArrayList;
    protected boolean userSelected = false;

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
    protected JLabel allUsersLabel;
    protected JComboBox allUsersDropBox;

    // List of All Users
    protected String[] allUsersIDs;

    UserDeletionPage()
    {
        setTitle("Edit User Profile");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        createArrayCopy();

        c = getContentPane();
        c.setLayout(null);

        // Menu title
        menuTitle = new JLabel("Edit Account");
        menuTitle.setSize(400, 30);
        menuTitle.setLocation(400, 30);
        c.add(menuTitle);

        // Button that selects the user for Editing
        selectUser = new JButton("Select");
        selectUser.setSize(150, 30);
        selectUser.setLocation(340, 450);
        selectUser.addActionListener(new ActionListener() {
            // Adds all items from selected user to the box
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String selectedID = (String)allUsersDropBox.getSelectedItem();

                if (UserAccountArray.searchUserID(selectedID))
                {
                    userSlotInArrayList = UserAccountArray.getUserPlace(selectedID);
                    userSelected = true;

                    // Set all editable variables from the user
                    textPassword.setText(UserAccountArray.getUsers().get(userSlotInArrayList).getPassword());
                    textFirstName.setText(UserAccountArray.getUsers().get(userSlotInArrayList).getFirstName());
                    textLastName.setText(UserAccountArray.getUsers().get(userSlotInArrayList).getLastName());
                    menuTitle.setText("User is a(n) " + UserAccountArray.getUsers().get(userSlotInArrayList).getUserRole());
                }
                else
                    JOptionPane.showMessageDialog(null, "ID not found");
            }
        });
        c.add(selectUser);

        // Button that deletes a selected user
        deleteUser = new JButton("Delete User");
        deleteUser.setSize(150, 30);
        deleteUser.setLocation(140, 450);
        deleteUser.addActionListener(new ActionListener() {
            // Updates the user for the new array
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // If a user has been selected, remove them from the database
                if (userSelected)
                {
                    // If the user has deleted them self, send them to the login screen
                    // Don't delete yourself from the database ;)
                    if (HoldCurrentLoginType.getLoggedInUser() == UserAccountArray.getUsers().get(userSlotInArrayList))
                    {
                        HoldCurrentLoginType.updateUser(null);
                        HoldPagesVisited.resetNumberOfPagesVisited();

                        for (Vendor vendor: VendorAccountArray.vendors)
                            vendor.hasNotUpdated = true;

                        // Remove the user from the program
                        UserAccountArray.removeUser(userSlotInArrayList);
                        UserWriteToCSV.writeUsersToCSV(UserAccountArray.getUsers());
                        userSelected = false;

                        new LoginMenu();
                        UserDeletionPage.super.dispose();
                    }
                    else
                    {
                        // Remove the user from the program
                        UserAccountArray.removeUser(userSlotInArrayList);
                        UserWriteToCSV.writeUsersToCSV(UserAccountArray.getUsers());
                        userSelected = false;
                        new UserDeletionPage();
                        UserDeletionPage.super.dispose();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Select a User from the ID list first");
            }
        });
        c.add(deleteUser);

        // Label for the firstName Box
        firstNameLabel = new JLabel("Enter First Name");
        firstNameLabel.setSize(150, 30);
        firstNameLabel.setLocation(525, 65);
        c.add(firstNameLabel);

        // Textbox for the first name
        textFirstName = new JTextField();
        textFirstName.setSize(200, 30);
        textFirstName.setLocation(525, 90);
        textFirstName.setDocument(new MaxCharLimit(15));
        textFirstName.setEditable(false);
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
        textLastName.setDocument(new MaxCharLimit(15));
        textLastName.setEditable(false);
        c.add(textLastName);

        // Label for the User ID list
        allUsersLabel = new JLabel("List Of Users to Edit");
        allUsersLabel.setSize(150, 30);
        allUsersLabel.setLocation(150, 130);
        c.add(allUsersLabel);

        // Dropbox of Users editable
        allUsersDropBox = new JComboBox<>(allUsersIDs);
        allUsersDropBox.setSize(200, 30);
        allUsersDropBox.setLocation(150, 155);
        c.add(allUsersDropBox);

        // Label for the Password box
        passwordLabel = new JLabel("Enter Password");
        passwordLabel.setSize(150, 30);
        passwordLabel.setLocation(150, 180);
        c.add(passwordLabel);

        // Textbox for the Password
        textPassword = new JTextField();
        textPassword.setSize(200, 30);
        textPassword.setLocation(150, 205);
        textPassword.setDocument(new MaxCharLimit(16));
        textPassword.setEditable(false);
        c.add(textPassword);

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

                for (Vendor vendor: VendorAccountArray.vendors)
                    vendor.hasNotUpdated = true;

                new LoginMenu();
                UserDeletionPage.super.dispose();
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
                UserDeletionPage.super.dispose();
            }
        });
        c.add(backToMenu);

        setVisible(true);
    }

    // Creates the array list for this GUI depending on the user logged in
    protected void createArrayCopy()
    {
        if (HoldCurrentLoginType.getLoggedInUser() instanceof Owner)
        {
            allUsersIDs = new String[UserAccountArray.getUsers().size()];
            for (int i = 0; i < UserAccountArray.getUsers().size(); i++)
                allUsersIDs[i] = UserAccountArray.getUsers().get(i).getUserID();
        }
        else
        {
            // Get the allotted size for Administrators to edit
            int numOfAllowedEdits = 0;
            for (int i = 0; i < UserAccountArray.getUsers().size(); i++)
            {
                if (!(UserAccountArray.getUsers().get(i) instanceof Owner || UserAccountArray.getUsers().get(i) instanceof Administrator))
                    numOfAllowedEdits++;
            }
            allUsersIDs = new String[numOfAllowedEdits];

            int placeInAllUsersIDs = 0;
            for (int i = 0; i < UserAccountArray.getUsers().size(); i++)
            {
                if (!(UserAccountArray.getUsers().get(i) instanceof Owner || UserAccountArray.getUsers().get(i) instanceof Administrator))
                {
                    allUsersIDs[placeInAllUsersIDs] = UserAccountArray.getUsers().get(i).getUserID();
                    placeInAllUsersIDs++;
                }
            }
        }
    }
}
