package edu.sdccd.cisc191.template;
import javafx.scene.control.Button;

public class CarInfoButton extends Button{
    private int row;
    private int col;

    public CarInfoButton(int row, int col){
        this.row = row;
        this.col = col;
        setPrefWidth(200);
        setMinWidth(200);
        setText("Get Car Info");
    }
}
