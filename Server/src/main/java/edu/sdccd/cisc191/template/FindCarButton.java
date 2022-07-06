package edu.sdccd.cisc191.template;
import javafx.scene.control.Button;

public class FindCarButton extends Button{
    private String licensePlate;

    public FindCarButton(String licensePlate){
        this.licensePlate = licensePlate;
        setPrefWidth(200);
        setMinWidth(200);
        setText("Find Car");
    }
}
