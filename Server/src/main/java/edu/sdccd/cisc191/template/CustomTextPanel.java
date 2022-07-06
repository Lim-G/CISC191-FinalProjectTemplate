package edu.sdccd.cisc191.template;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javax.swing.*;
import java.awt.*;

public class CustomTextPanel{
    public JLabel myLabel;
    public JTextField myTextField = new JTextField();
    public JPanel panel = new JPanel(new BorderLayout());

    public CustomTextPanel(String givenLabel) {
        myLabel.setText(givenLabel);
        panel.add(myLabel,BorderLayout.WEST);
        panel.add(myTextField,BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(200, 50));
    }

    public String getText() {
        return myTextField.getText();
    }

    public void setText(String givenText) {
        myTextField.setText(givenText);
    }

}