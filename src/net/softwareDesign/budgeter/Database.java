package net.softwareDesign.budgeter;

import java.util.ArrayList;
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

    private ArrayList<Debt> debt;

    public Database(int monthlyIncome, int monthlyUtilities,
                    int foodExpenses, Date retireDate, int retirementSavings, int emergencySavings) {
        this.monthlyIncome = monthlyIncome;
        this.monthlyUtilities = monthlyUtilities;
        this.foodExpenses = foodExpenses;
        this.retireDate = retireDate;
        this.retirementSavings = retirementSavings;
        this.emergencySavings = emergencySavings;
        debt = new ArrayList<>();
    }

    public double getDisposableIncome(){

        double disposableIncome = monthlyIncome - monthlyUtilities - foodExpenses;
        for(Debt d:debt){

            disposableIncome -= d.getCurrentPayment();

        }
        return disposableIncome;


    }

    public void addDebt(Debt toAdd) {
        debt.add(toAdd);
    }
    public ArrayList<Debt> getDebt() {
        return debt;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public int getMonthlyUtilities() {
        return monthlyUtilities;
    }

    public int getFoodExpenses() {
        return foodExpenses;
    }

    public Date getRetireDate() {
        return retireDate;
    }

    public double getAge() {
        return age;
    }

    public int getRetirementSavings() {
        return retirementSavings;
    }

    public int getEmergencySavings() {
        return emergencySavings;
    }

    public double calculateExpectedRetirementSavings() {
        Date currentDate = new Date();
        int monthsRemaining = (retireDate.getYear() - currentDate.getYear()) * 12 + retireDate.getMonth() - currentDate.getMonth();
        double expectedRetirement = Math.pow(1.005, monthsRemaining) * retirementSavings;
        return expectedRetirement;
    }


}
