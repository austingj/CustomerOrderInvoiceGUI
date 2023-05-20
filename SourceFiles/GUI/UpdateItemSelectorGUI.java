package GUI;

import ItemProfile.ItemProfile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Main.Main.items;

public class UpdateItemSelectorGUI extends JFrame {


    private JButton updateItemButton;
    private JComboBox itemBox;
    private JPanel mainPanel;

    public UpdateItemSelectorGUI(java.lang.String title) {

        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        setSize(500, 500);
        setLocationRelativeTo(null);

        for(ItemProfile item : items){

            itemBox.addItem(item);
        }

        updateItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {JFrame UpdateItemFrame = new UpdateItemGUI("Update Item", itemBox.getSelectedItem());
              UpdateItemFrame.setVisible(true);
              UpdateItemSelectorGUI.super.dispose();
            }
        });
    }
}
