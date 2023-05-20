package GUI;

import ItemProfile.AllExpiredItems;
import Login.HoldCurrentLoginType;
import Login.HoldPagesVisited;
import Login.LoginMenu;
import Main.MainMenu;
import ProfileUsers.Vendor;
import ProfileUsers.VendorAccountArray;
import UserClasses.Purchaser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
@author: Andrew James
 */

public class ItemMenuGUI extends JFrame {
    private JPanel mainPanel;
    private JButton createItemButton;
    private JButton showItemsButton;
    private JButton deleteItemsButton;
    private JButton searchItemsButton;
    private JButton updateItemsButton;

    // Logout and Exit To Menu buttons
    private JButton logout;
    private JButton exitToMenuButton;
    private JButton expiredItems;

    public ItemMenuGUI(String title){
            super(title);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setContentPane(mainPanel);
            this.pack();
            this.setBounds(100,100,500,500);
            setLocationRelativeTo(null);

            createItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame CreateItemFrame = new CreateItemGUI("Create Item");
                CreateItemFrame.setVisible(true);
            }
        });
        showItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame CreateSearchFrame = new  JFrame("Show Items");
                CreateSearchFrame.setSize(10,10);
                CreateSearchFrame.add(new ItemTableGUI().panel1);
                CreateSearchFrame.setVisible(true);
                CreateSearchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                CreateSearchFrame.setLocationRelativeTo(null);
            }
        });
        deleteItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame DeleteItemFrame = new DeleteItemGUI("Delete Item");
                DeleteItemFrame.setVisible(true);

            }
        });
        updateItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame CreateUpdateFrame = new UpdateItemSelectorGUI("Update Item");
                CreateUpdateFrame.setVisible(true);
            }
        });
        searchItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame CreateSearchFrame = new SearchItemsGUI("Search for Item");
                CreateSearchFrame.setVisible(true);
            }
        });

        // Logout and Exit To Menu buttons
        // Logout button defined
        logout.addActionListener(new ActionListener()
        {
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
                ItemMenuGUI.super.dispose();
            }
        });

        // Back To Menu button defined
        exitToMenuButton.addActionListener(new ActionListener()
        {
            // Sends user back to main menu
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new MainMenu();
                ItemMenuGUI.super.dispose();
            }
        });

        // Send the user to the expired items page
        if (HoldCurrentLoginType.getLoggedInUser() instanceof Purchaser)
        {
            expiredItems.setVisible(true);
            expiredItems.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    JFrame createExpiredListFrame = new AllExpiredItems();
                    createExpiredListFrame.setVisible(true);
                }
            });
        }
        else
            expiredItems.setVisible(false);

        // Makes the window visible
        this.setVisible(true);
    }


}
