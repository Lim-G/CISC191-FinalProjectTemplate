package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;

/**
 * This class is for the creation of the GUI that hosts the Parking Lot array.
 */
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

    /**
     * This Method is for clearing all inoput and output fields
     */
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

    /**
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) {

        Insets LABEL_PADDING = new Insets(10);

        /**
         * The initialization of buttons and then setting up the action events
         * What the buttons do is in the name
         */
        //----------------------------------------------------// Returns Car info at given row and column coordinates
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
        //----------------------------------------------------// Parks a newly created car using elements from the input fields
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
        //----------------------------------------------------// Finds a car using a user given license plate field
        FindCarButton findCarButton = new FindCarButton();
        findCarButton.setOnAction(e -> {
            String carSpot = parkingLot.findCar(licensePlateField.getText());
            outputText.setText(carSpot);
        });
        findCarButton.setPadding(LABEL_PADDING);
        //----------------------------------------------------// Prints the entire lot, requires no input from user
        PrintLotButton printLotButton = new PrintLotButton();
        printLotButton.setOnAction(e -> {
            String printedLot = parkingLot.printParkingLot();
            outputText.setText(printedLot);
        });
        printLotButton.setPadding(LABEL_PADDING);
        //----------------------------------------------------// Removes car at user given row and column coordinates
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
        //----------------------------------------------------// Clears all input and output fields.
        ClearAllButton clearAllButton = new ClearAllButton();
        clearAllButton.setOnAction(e -> {
            clearAllSpaces();
        });
        clearAllButton.setPadding(LABEL_PADDING);
        //----------------------------------------------------//

        //The creation of the input fields using HBoxes to combine a label and a text field
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

        //Rest of this is setting up the GUI scene elements.
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
