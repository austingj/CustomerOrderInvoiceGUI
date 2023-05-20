/**
 * Class made by 'Benjamin Pienta'
 **/

package UserClasses;

import Login.HoldCurrentLoginType;
import Login.HoldPagesVisited;
import Login.LoginMenu;
import Main.MainMenu;
import ProfileUsers.Vendor;
import ProfileUsers.VendorAccountArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserListDisplay extends JFrame
{
    private JTable table;
    private JButton backToMenu;
    private JButton logout;

    public UserListDisplay()
    {
        setTitle("All Users");
        setBounds(300, 90, 900, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 2D arraylist that will store each user's data
        ArrayList<ArrayList<String>> userInfo = new ArrayList<>();
        int row = 0; // rows of the new 2d arraylist

        // Columns for the table
        String[] columns = {"Last Name", "First Name", "User ID", "User Role"};

        // Store all the user information into the 2D array list
        String line = "";
        try
        {
            // Initialize the reader
            BufferedReader csvReader = new BufferedReader(new FileReader("Resources/users.csv"));
            csvReader.readLine(); // Skip the header of the file

            while ((line = csvReader.readLine()) != null)
            {
                // Add a row to the 2D array list for user data
                userInfo.add(new ArrayList<String>());

                // Store variables of csv in a string
                String[] variables = line.split(",");

                // Initialize all variables for the User class
                String lastName = variables[0];
                String firstName = variables[1];
                String userID = variables[2];
                String userRole = variables[3];

                userInfo.get(row).add(lastName);
                userInfo.get(row).add(firstName);
                userInfo.get(row).add(userID);
                userInfo.get(row).add(userRole);

                // Increment row count
                row++;
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        // Transfer the Arraylist data to the 2D string for ease of adding to the table
        String[][] normal2DStringArray = new String[row][4];

        // Transfer all data from the 2D arraylist to the normal 2D String array
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                normal2DStringArray[i][0] = userInfo.get(i).get(0);
                normal2DStringArray[i][1] = userInfo.get(i).get(1);
                normal2DStringArray[i][2] = userInfo.get(i).get(2);
                normal2DStringArray[i][3] = userInfo.get(i).get(3);
            }
        }

        // Make the table
        table = new JTable(normal2DStringArray, columns);
        table.setSize(900, 300);

        JScrollPane scrollPanel = new JScrollPane(table);
        add(scrollPanel);

        // Add the return button
        add(exitToMenuButton(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel exitToMenuButton()
    {
        JPanel extraButtons = new JPanel();

        // Button that exits to the menu
        backToMenu = new JButton("Exit To Menu");
        backToMenu.setSize(150, 30);
        backToMenu.setLocation(340, 450);
        backToMenu.addActionListener(new ActionListener() {
            // Sends user back to the menu
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                UserListDisplay.super.dispose();
            }
        });
        extraButtons.add(backToMenu);

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
                UserListDisplay.super.dispose();
            }
        });
        extraButtons.add(logout);

        return extraButtons;
    }
}
