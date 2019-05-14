package net.softwareDesign.budgeter;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

//import java.awt.*;

/**
 * Program gets the users monthly income, expenses, retirement date, savings, and any debts and generates a budget.
 * <br>
 * The program first takes in the aforementioned fields and using them generates the necessary information for a budget.
 * The user is able to edit the amount of money they want to save and this is also used to create a visual for the user.
 *
 * @author Stuart Williams, Peter Geertsema, Joseph Day
 * @version 1.0
 */
public class Budgeter extends Application{

    /**
     *   Fields from the scene, used to create the budget
     */
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

    /**
     * Back end classes to process information
     */
    private Database database;
    private Budget budget;
    /**
     * Debt table fields
     */
    @FXML private TableView <Debt> debtTable;
    @FXML private TableColumn<Debt, Integer> aprCol;
    @FXML private TableColumn<Debt, Integer> valueCol;
    @FXML private TableColumn<Debt, Integer> paymentCol;
    @FXML private TextField apr;
    @FXML private TextField value;
    @FXML private TextField payment;
    /**
     * Retirement slider and output
     */
    @FXML private Slider retireSlider;
    @FXML private Label outputLabel;

    /**
     * list of debts the user has
     */
    ObservableList<Debt> debtList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }//end main

    /**
     * Root for the scene
     */
    Parent root;
    public void start(Stage stage) throws IOException {
        //scene done through Scene builder, see .fxml file
        root = FXMLLoader.load(getClass().getResource("Budgeter.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Budgeter.java");
        stage.show();
    }//end start

    /**
     * Stack pane used to transition between fields pane and results pane
     */
    @FXML private StackPane stackPane;
    /**
     * Method to handle button presses.
     * <br>
     * The method handles the calculate action event for the submit button.
     * The database is generated based on the fields and debts
     * @param event
     */
    @FXML
    private void calculate(ActionEvent event) throws IOException {
        /*
        System.out.println(income.getText());
        System.out.println(Integer.parseInt(utilities.getText()));
        System.out.println(Integer.parseInt(food.getText()));
        System.out.println(Date.from(Instant.from(retire.getValue().atStartOfDay(ZoneId.systemDefault()))));
        System.out.println(Integer.parseInt(savings.getText()));
        System.out.println(Integer.parseInt(emergency.getText()));
         */
        //creates a new database
        database = new Database(Integer.parseInt(income.getText()),
                Integer.parseInt(utilities.getText()), Integer.parseInt(food.getText()),
                Date.from(Instant.from(retire.getValue().atStartOfDay(ZoneId.systemDefault()))),
                Integer.parseInt(savings.getText()), Integer.parseInt(emergency.getText()));

        //adds all debts from the table into the database
        for(Debt d:debtList){
            database.addDebt(d);
        }

        //brings the results pane to front
        stackPane.getChildren().get(0).toFront();
    }//end calculate

    /**
     * Budget is displayed in a pie chart
     */
    @FXML private PieChart pieChart;
    @FXML private RadioButton yesButton;
    @FXML private RadioButton noButton;

    /**
     * Generates a visual for the budget.
     * <br>
     * The user selects the percentage of money they want to save.
     * Using this data and the generated budget, a pie chart is
     * created along with the projected amount saved by retirement.
     *
     * @param event
     * @throws InterruptedException
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void getRetire(ActionEvent event) throws InterruptedException, IOException, URISyntaxException {
        //Budget created from database
        budget = new Budget(database);

        //Savings by retirement based off percent saved
        double savings = Math.round(budget.setRetirementPercentage(retireSlider.getValue()/100));
        outputLabel.setText("You will have "+ savings +" in the bank when you retire.");

        //Visual used to display the budget breakdown to the user
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList();
        pieData.add(new PieChart.Data("Food", database.getFoodExpenses()));
        pieData.add(new PieChart.Data("Utilities", database.getMonthlyUtilities()));
        pieData.add(new PieChart.Data("Disposable Income", budget.getSurplusIncome()));
        pieData.add(new PieChart.Data("Retirement Savings", budget.getRetirementPerMonth()));
        pieData.add(new PieChart.Data("Emergency Savings", budget.getEmergencyPerMonth()));
        pieChart.setData(pieData);

        //Radio buttons used to check if user is following budget
        ToggleGroup tg = new ToggleGroup();
        yesButton.setToggleGroup(tg);
        noButton.setToggleGroup(tg);
        if (noButton.isSelected()){
            Shock shock = new Shock();
        }

    }//end getRetire

    /**
     * Add debt adds debt to the debt table.
     * <br>
     * Debt fields are added to the table view then added to the debt list.
     * @param event
     */
   @FXML
    private void addDebt(ActionEvent event){
        //add fields to table
        aprCol.setCellValueFactory(new PropertyValueFactory<>("apr"));
        valueCol.setCellValueFactory(new PropertyValueFactory<>("currentValue"));
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("currentPayment"));

        //add debt to debt list and store list in table
        debtList.add(new Debt(Double.valueOf(value.getText()),Double.valueOf(apr.getText()),Double.valueOf(payment.getText())));
        debtTable.setItems(debtList);
    }//end addDebt
}//end Class
