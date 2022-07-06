package edu.sdccd.cisc191.template;
import javafx.scene.control.Button;


public class ParkCarButton extends Button {

    private int row;
    private int col;
    private String make;
    private String model;
    private String year;
    private String licensePlate;
    private String color;

    public ParkCarButton(int row, int col, String make, String model, String year, String licensePlate, String color) {
        this.row = row;
        this.col = col;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.color = color;
        setPrefWidth(200);
        setMinWidth(200);
        setText("Park Car");
    }
}
