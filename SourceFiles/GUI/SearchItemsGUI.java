package GUI;

import ItemProfile.ItemProfile;
import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static Main.Main.items;
public class SearchItemsGUI extends JFrame{
    private JComboBox searchBy;
    private JPanel panel1;
    private JButton searchButton;
    private JTextField searchItem;
    private JLabel searchByLabel;
    private JDatePicker expireDateForm;
    boolean isFound = false;
    public static ArrayList<ItemProfile> itemNameSearch = new ArrayList<>();
    public SearchItemsGUI(java.lang.String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        setSize(300, 550);
        setLocationRelativeTo(null);
        expireDateForm.setVisible(false);
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");


        searchBy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(searchBy.getSelectedItem().toString()) {
                    case "Item ID":
                        searchByLabel.setText("Search for Item ID:");
                        expireDateForm.setVisible(false);
                        searchItem.setVisible(true);
                        searchItem.setText("");
                        break;
                    case "Item Name":
                        searchByLabel.setText("Search for Item Name:");
                        expireDateForm.setVisible(false);
                        searchItem.setVisible(true);
                        searchItem.setText("");
                        break;
                    case "Expire Date":
                        searchByLabel.setText("Search for Expire Date:");
                        searchItem.setVisible(false);
                        expireDateForm.setVisible(true);
                        break;
                    default:
                        searchByLabel.setText("Search:");
                        searchItem.setText("");
                }
            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(searchBy.getSelectedItem().toString()) {
                    case "Item ID":
                        if(!searchItem.getText().matches("-?\\d+(\\.\\d+)?") || searchItem.getText() == null || searchItem.getText().length() > 6)
                        {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Item ID");
                            isFound = false;
                            return;
                        }else{
                            for(ItemProfile item : items)
                            {
                                if(searchItem.getText().matches(item.getItemID()))
                                {
                                   itemNameSearch.add(item);
                                   isFound = true;
                                }
                            }
                            if(isFound){
                                JFrame CreateSearchResultsFrame = new  JFrame("Show Search Items");
                                CreateSearchResultsFrame.setSize(1250,300);
                                CreateSearchResultsFrame.add(new SearchResultsNameGUI().panel1);
                                CreateSearchResultsFrame.setVisible(true);
                                CreateSearchResultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                CreateSearchResultsFrame.setLocationRelativeTo(null);
                                itemNameSearch.removeAll(itemNameSearch);
                                isFound = false;
                            }else{
                                JOptionPane.showMessageDialog(null, "Item ID " + searchItem.getText() + " is not found!");
                                isFound = false;
                                return;
                            }
                        }
                        break;
                    case "Item Name":
                        if (searchItem.getText().matches("-?\\d+(\\.\\d+)?") || searchItem.getText() == null) {
                            JOptionPane.showMessageDialog(null, "Please enter valid Item Name");
                            isFound = false;
                            return;
                        }else{
                            for(ItemProfile item : items)
                            {
                                if(searchItem.getText().toLowerCase().matches(item.getItemName().toLowerCase()))
                                {
                                    itemNameSearch.add(item);
                                    isFound = true;
                                }
                            }
                            if(isFound){
                                JFrame CreateSearchResultsFrame = new  JFrame("Show Search Items");
                                CreateSearchResultsFrame.setSize(1250,300);
                                CreateSearchResultsFrame.add(new SearchResultsNameGUI().panel1);
                                CreateSearchResultsFrame.setVisible(true);
                                CreateSearchResultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                CreateSearchResultsFrame.setLocationRelativeTo(null);
                                isFound = false;
                                itemNameSearch.removeAll(itemNameSearch);
                            }else{
                                JOptionPane.showMessageDialog(null, "Item Name " + searchItem.getText() + " profile is not found!");
                                isFound = false;
                                return;
                            }

                        }
                        break;
                    case "Expire Date":
                        java.lang.String expireDate = (expireDateForm.getModel().getMonth() + 1) + "/" + (expireDateForm.getModel().getDay()) + "/" + (expireDateForm.getModel().getYear());
                        Date expireDateFor;
                        try {
                            expireDateFor = formatter.parse(expireDate);
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        for(ItemProfile item : items)
                        {
                            if(expireDateFor.compareTo(item.getExpireDate()) == 0)
                            {
                                itemNameSearch.add(item);
                                isFound = true;
                            }
                        }if(isFound)
                        {
                            JFrame CreateSearchResultsFrame = new  JFrame("Show Search Items");
                            CreateSearchResultsFrame.setSize(1250,300);
                            CreateSearchResultsFrame.add(new SearchResultsNameGUI().panel1);
                            CreateSearchResultsFrame.setVisible(true);
                            CreateSearchResultsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            CreateSearchResultsFrame.setLocationRelativeTo(null);
                            isFound = false;
                            itemNameSearch.removeAll(itemNameSearch);

                        }else{
                        JOptionPane.showMessageDialog(null, "No Item with Expire Date: " + expireDate);
                        return;
                        }
                        break;
                    default:
                        searchByLabel.setText("");
                }

            }
        });
    }

}
