package edu.sdccd.cisc191.template;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class CustomTextPanel{
    public Label makeLabel = new Label("Make:");
    public TextField myTextField = new TextField();

    public HBox customBox;

    public CustomTextPanel(String givenLabel) {
        makeLabel.setText(givenLabel);
        HBox customBox = new HBox(makeLabel, myTextField);
        customBox.setSpacing(10);
    }

    public HBox getHBox() {
        return this.customBox;
    }

    public String getText() {
        return myTextField.getText();
    }

    public void setText(String givenText) {
        myTextField.setText(givenText);
    }

}