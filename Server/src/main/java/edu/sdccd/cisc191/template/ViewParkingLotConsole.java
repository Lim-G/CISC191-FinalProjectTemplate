package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;

public class ViewParkingLotConsole extends Application {

    private CarInfoButton carInfoButton;
    private ParkCarButton parkCarButton;
    private FindCarButton findCarButton;
    private PrintLotButton printLotButton;
    private RemoveCarButton removeCarButton;
    private ClearAllButton clearAllButton;

    private TextField makeField = new TextField();
    private Label makeLabel = new Label("Make:");
    private TextField modelField = new TextField();
    private Label modelLabel = new Label("Model:");
    private TextField licensePlateField = new TextField();
    private Label licensePlateLabel = new Label("License Plate:");
    private TextField colorField = new TextField();
    private Label colorLabel = new Label("Color:");
    private TextField yearField = new TextField();
    private Label yearLabel = new Label("Year:");
    private TextField rowField = new TextField();
    private Label rowLabel = new Label("Row:");
    private TextField columnField = new TextField();
    private Label columnLabel = new Label("Column:");


    private ParkingLot parkingLot = new ParkingLot();

    private TextArea outputText = new TextArea();


    public void clearAllSpaces() {
        makeField.setText("");
        modelField.setText("");
        colorField.setText("");
        yearField.setText("");
        licensePlateField.setText("");
        rowField.setText("");
        columnField.setText("");
        outputText.setText("");
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        Insets LABEL_PADDING = new Insets(10);

        /**
         * The initialization of buttons and then setting up the action events
         * What the buttons do is in the name
         */
        //----------------------------------------------------//
        CarInfoButton carInfoButton = new CarInfoButton();
        carInfoButton.setOnAction(e -> {
            if (parkingLot.isCar(Integer.parseInt(rowField.getText()), Integer.parseInt(columnField.getText()))) {
                String carInfo = parkingLot.getCarInfo(Integer.parseInt(rowField.getText()), Integer.parseInt(columnField.getText()));
                outputText.setText(carInfo);
            }
            else {
                outputText.setText("Nothing There.");
            }
        });
        carInfoButton.setPadding(LABEL_PADDING);
        //----------------------------------------------------//
        ParkCarButton parkCarButton = new ParkCarButton();
        parkCarButton.setOnAction(e -> {
            Car tempCar = new Car(makeField.getText(), modelField.getText(), licensePlateField.getText(), colorField.getText(), yearField.getText());
            if (parkingLot.isCar(Integer.parseInt(rowField.getText()), Integer.parseInt(columnField.getText()))) {
                outputText.setText("Spot is taken");
            }
            else{
                parkingLot.parkCar(tempCar, Integer.parseInt(rowField.getText()), Integer.parseInt(columnField.getText()));
                outputText.setText("Parked");
            }
        });
        parkCarButton.setPadding(LABEL_PADDING);
        //----------------------------------------------------//
        FindCarButton findCarButton = new FindCarButton();
        findCarButton.setOnAction(e -> {
            String carSpot = parkingLot.findCar(licensePlateField.getText());
            outputText.setText(carSpot);
        });
        findCarButton.setPadding(LABEL_PADDING);
        //----------------------------------------------------//
        PrintLotButton printLotButton = new PrintLotButton();
        printLotButton.setOnAction(e -> {
            String printedLot = parkingLot.printParkingLot();
            outputText.setText(printedLot);
        });
        printLotButton.setPadding(LABEL_PADDING);
        //----------------------------------------------------//
        RemoveCarButton removeCarButton = new RemoveCarButton();
        removeCarButton.setOnAction(e -> {
            if (parkingLot.outOfBounds(Integer.parseInt(rowField.getText()), Integer.parseInt(columnField.getText()))) {
                outputText.setText("Out of Bounds");
            }
            else {
                parkingLot.removeCar(Integer.parseInt(rowField.getText()), Integer.parseInt(columnField.getText()));
                outputText.setText("Removed.");
            }
        });
        removeCarButton.setPadding(LABEL_PADDING);
        //----------------------------------------------------//
        ClearAllButton clearAllButton = new ClearAllButton();
        clearAllButton.setOnAction(e -> {
            clearAllSpaces();
        });
        clearAllButton.setPadding(LABEL_PADDING);
        //----------------------------------------------------//

        HBox makeBox = new HBox(makeLabel, makeField);
        makeBox.setSpacing(10);
        HBox modelBox = new HBox(modelLabel, modelField);
        modelBox.setSpacing(10);
        HBox licensePLateBox = new HBox(licensePlateLabel, licensePlateField);
        licensePLateBox.setSpacing(10);
        HBox colorBox = new HBox(colorLabel, colorField);
        colorBox.setSpacing(10);
        HBox yearBox = new HBox(yearLabel, yearField);
        yearBox.setSpacing(10);
        HBox rowBox = new HBox(rowLabel, rowField);
        rowBox.setSpacing(10);
        HBox columnBox = new HBox(columnLabel, columnField);
        columnBox.setSpacing(10);


        BorderPane root = new BorderPane();

        VBox buttonBox = new VBox(carInfoButton, parkCarButton, findCarButton, printLotButton, removeCarButton, clearAllButton);
        buttonBox.setPadding(LABEL_PADDING);
        buttonBox.setSpacing(20);

        VBox inputBox = new VBox(makeBox, modelBox, licensePLateBox, colorBox, yearBox, rowBox, columnBox);
        inputBox.setPadding(LABEL_PADDING);
        inputBox.setSpacing(20);

        HBox centerBox = new HBox(buttonBox, inputBox);

        outputText.setPrefHeight(5000);
        outputText.setPrefWidth(200);

        root.setCenter(centerBox);
        root.setRight(outputText);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Parking Lot Console");
        primaryStage.show();
    }
}
