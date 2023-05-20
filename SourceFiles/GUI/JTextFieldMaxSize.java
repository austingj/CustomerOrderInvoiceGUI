package GUI;
import java.awt.FlowLayout;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
/*
This is used to set a max value for JTextFields
@author Austin Jeffery

 */

public class JTextFieldMaxSize extends PlainDocument{
    private int max;

    public JTextFieldMaxSize(int max) {
        super();
        this.max = max;
    }

    JTextFieldMaxSize(int max, boolean upper) {
        super();
        this.max = max;
    }
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;

        if ((getLength() + str.length()) <= max) {
            super.insertString(offset, str, attr);
        }
    }
}
