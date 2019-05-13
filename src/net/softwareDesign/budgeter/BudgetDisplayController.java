package net.softwareDesign.budgeter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BudgetDisplayController {
    @FXML private Label months;
    Budgeter b = new Budgeter();
    public void initialize(){
        int maxMonths = -1;
        for(Debt d: b.database.getDebt()){
            if (maxMonths> d.monthsRemaining()){
                maxMonths = d.monthsRemaining();
            }
        }
        months.setText(maxMonths + "months");
    }
}
