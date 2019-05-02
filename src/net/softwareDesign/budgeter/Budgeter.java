package net.softwareDesign.budgeter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
 */
public class Budgeter extends Application  {
    

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Budgeter.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Budgeter.java");
        stage.show();
    }
    @FXML
    private void calculate(ActionEvent event){
        System.out.println("button works");
    }
}
