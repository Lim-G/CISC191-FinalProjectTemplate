package edu.sdccd.cisc191.template;
import javafx.scene.control.Button;

public class RemoveCarButton extends Button{
    private int row;
    private int col;

    public RemoveCarButton(int row, int col){
        this.row = row;
        this.col = col;
        setPrefWidth(200);
        setMinWidth(200);
        setText("Remove Car at");
    }
}
