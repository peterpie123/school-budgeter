package net.softwareDesign.budgeter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

//import java.awt.*;

/**
 * Here's how you learn how to manage money!
 * @version alpha 1.0
 */
public class Budgeter extends Application  {
    @FXML private TableView debtTable;
    @FXML private TableColumn aprCol;
    @FXML private TextField apr;
    @FXML private TextField value;
    @FXML private TextField payment;

    public static void main(String[] args) {
        launch(args);
    }
    Parent root;
    public void start(Stage stage) throws IOException {
        //scene done through Scene builder, see .fxml file
        root = FXMLLoader.load(getClass().getResource("Budgeter.fxml"));


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Budgeter.java");
        stage.show();
    }

    /**
     * Method to handle button presses.
     * <br>
     * The method handles the calculate action event for the submit button.
     * At this point, the button only prints a message to the console.
     * Will add functionality later.
     *
     * @param event
     */
    @FXML
    private void calculate(ActionEvent event){
        System.out.println("button works");
    }

    @FXML
    private void addDebt(ActionEvent event){
        
    }
}
