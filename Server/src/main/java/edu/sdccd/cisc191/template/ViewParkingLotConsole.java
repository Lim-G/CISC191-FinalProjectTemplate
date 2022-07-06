package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.awt.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.geometry.Insets;

public class ViewParkingLotConsole extends Application {

    private CustomTextPanel makeTextPanel = new CustomTextPanel("Make:");
    private CustomTextPanel modelTextPanel = new CustomTextPanel("Model:");
    private CustomTextPanel colorTextPanel = new CustomTextPanel("Color:");
    private CustomTextPanel yearTextPanel = new CustomTextPanel("Year:");
    private CustomTextPanel licensePlateTextPanel = new CustomTextPanel("License PLate:");
    private CustomTextPanel rowTextPanel = new CustomTextPanel("Row:");
    private CustomTextPanel columnTextPanel = new CustomTextPanel("Column:");

    private ParkingLot parkingLotA;

    private TextArea outputText = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
