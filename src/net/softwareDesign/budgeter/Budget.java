package net.softwareDesign.budgeter;

import java.util.Date;

public class Budget {
    /**
     * Proportion of disposable income going to emergency savings
     */
    private static final double EMERGENCY_PROPORTION = .1;
    /**
     * Excess income for having fun!
     */
    private double surplusIncome;
    /**
     * Money per month to retirement
     */
    private double retirementPerMonth;
    /**
     * Money per month to emergency savings
     * Fixed at 10% of disposable income
     */
    private double emergencyPerMonth;

    private Database database;

    public Budget(Database database) {
        this.database = database;
        emergencyPerMonth = database.getDisposableIncome() * EMERGENCY_PROPORTION;
        surplusIncome = database.getDisposableIncome() - emergencyPerMonth;
    }

    /**
     * @param proportion The proportion of surplus income sending to retirement
     * @return The amount of money in your account when you retire
     */
    public double setRetirementPercentage(double proportion) {
        return calculatePossibleRetirement(surplusIncome * proportion);
    }

    private double calculatePossibleRetirement(double monthlyAmount){
        Date currentDate = new Date();
        int monthsRemaining = (database.getRetireDate().getYear() - currentDate.getYear()) * 12
                + database.getRetireDate().getMonth() - currentDate.getMonth();
        double possibleAmount = database.calculateExpectedRetirementSavings() +
                Math.pow(1.005, monthsRemaining + 1) / 0.005 * monthlyAmount;
        return possibleAmount;
    }

    public double getSurplusIncome() {
        return surplusIncome;
    }

    public double getRetirementPerMonth() {
        return retirementPerMonth;
    }

    public double getEmergencyPerMonth() {
        return emergencyPerMonth;
    }

    public int getMonthlyIncome() {
        return database.getMonthlyIncome();
    }

    public int getMonthlyUtilities() {
        return database.getMonthlyUtilities();
    }

    public int getFoodExpenses() {
        return database.getFoodExpenses();
    }
}
