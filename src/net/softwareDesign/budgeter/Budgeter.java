package net.softwareDesign.budgeter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

//import java.awt.*;

/**
 * Here's how you learn how to manage money!
 * @version alpha 1.0
 */
public class Budgeter extends Application  {
    @FXML private TextField income;
    @FXML private TextField utilities;
    @FXML private TextField food;
    @FXML private DatePicker retire;
    @FXML private TextField savings;
    @FXML private TextField emergency;
    @FXML private Button submit;


    /**
     * Overall pane used for the application
     */
    private static GridPane pane;

    private Database database;

    @FXML private TableView <Debt> debtTable;
    @FXML private TableColumn<Debt, Integer> aprCol;
    @FXML private TableColumn<Debt, Integer> valueCol;
    @FXML private TableColumn<Debt, Integer> paymentCol;
    @FXML private TextField apr;
    @FXML private TextField value;
    @FXML private TextField payment;

    ObservableList<Debt> debtList = FXCollections.observableArrayList();

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
    private void calculate(ActionEvent event) throws IOException {
        System.out.println(income.getText());
        System.out.println(Integer.parseInt(utilities.getText()));
        System.out.println(Integer.parseInt(food.getText()));
        System.out.println(Date.from(Instant.from(retire.getValue().atStartOfDay(ZoneId.systemDefault()))));
        System.out.println(Integer.parseInt(savings.getText()));
        System.out.println(Integer.parseInt(emergency.getText()));

        database = new Database(Integer.parseInt(income.getText()),
                Integer.parseInt(utilities.getText()), Integer.parseInt(food.getText()),
                Date.from(Instant.from(retire.getValue().atStartOfDay(ZoneId.systemDefault()))),
                Integer.parseInt(savings.getText()), Integer.parseInt(emergency.getText()));
        for(Debt d:debtList){
            database.addDebt(d);
        }
        root = FXMLLoader.load(getClass().getResource("budgetDisplay.fxml"));
    }

    @FXML
    private void addDebt(ActionEvent event){
        aprCol.setCellValueFactory(new PropertyValueFactory<>("apr"));
        valueCol.setCellValueFactory(new PropertyValueFactory<>("currentValue"));
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("currentPayment"));

        debtList.add(new Debt(Double.valueOf(value.getText()),Double.valueOf(apr.getText()),Double.valueOf(payment.getText())));
        debtTable.setItems(debtList);
    }


}
