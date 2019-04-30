package net.softwareDesign.budgeter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//import java.awt.*;

/**
 * Here's how you learn how to manage money!
 */
public class Budgeter extends Application  {
    /**
     * Overall pane used for the application
     */
    private static GridPane pane;
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        pane = new GridPane();

        //income
        Text txtIncome = new Text("Enter your monthly income: ");
        TextField tfIncome = new TextField();

        //monthly expenses
        Text txtUtilites = new Text("Enter your utiliy bill:");
        TextField tfUtilities = new TextField();
        Text txtFood = new Text("Enter your food expenses:");
        TextField tfFood = new TextField();

        GridPane inputPane = new GridPane();
        inputPane.add(txtIncome, 0,0);
        inputPane.add(tfIncome, 1,0);
        inputPane.add(txtUtilites, 0,1);
        inputPane.add(tfUtilities, 1,1);
        inputPane.add(txtFood, 0,2);
        inputPane.add(tfFood, 1,2);

        pane.add(inputPane, 0, 0);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
