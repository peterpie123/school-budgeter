package net.softwareDesign.budgeter;

public class Budget {
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
     */
    private double emergencyPerMonth;

    private Database database;

    public Budget(Database database) {
        this.database = database;
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
