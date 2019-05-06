package net.softwareDesign.budgeter;

import java.util.Date;

public class Database {

    /**
     * Monthly income, in dollars
     */
    private int monthlyIncome;

    private int monthlyUtilities;

    private int foodExpenses;

    private Date retireDate;
    /**
     * Age of the user, in years
     */
    private double age;
    /**
     * User's current retirement savings
     */
    private int retirementSavings;
    /**
     * User's current emergency savings
     */
    private int emergencySavings;

    public Database(int monthlyIncome, int monthlyUtilities,
                    int foodExpenses, Date retireDate, int retirementSavings) {
        this.monthlyIncome = monthlyIncome;
        this.monthlyUtilities = monthlyUtilities;
        this.foodExpenses = foodExpenses;
        this.retireDate = retireDate;
        this.retirementSavings = retirementSavings;
    }
}
