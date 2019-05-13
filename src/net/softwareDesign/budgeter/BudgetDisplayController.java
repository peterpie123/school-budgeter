package net.softwareDesign.budgeter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BudgetDisplayController {
    @FXML private Label months;

    public void initialize(){

        months.setText("test");
    }
}
