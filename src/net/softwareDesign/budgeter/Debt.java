package net.softwareDesign.budgeter;

import java.net.URI;

/**
 * Interest rate, time until paid off, and value
 */
public class Debt {
    /**
     * Interest per year as a percent
     */
    private double apr;
    /**
     * How much is owed on the debt
     */
    private double currentValue;
    /**
     * How much is being paid per month
     */
    private double currentPayment;

    public Debt(double value, double apr, double monthlyPayment) {
        this.currentValue = value;
        this.apr = apr;
        this.currentPayment = monthlyPayment;
    }

    /**
     * Number of months remaining on the debt
     */
    public int monthsRemaining() {

        double monthlyRate = apr / 100.0 / 12;
        int months = (int) (-Math.log(1-currentValue * monthlyRate / currentPayment) / Math.log(1 + monthlyRate));
        return months;

    }

    public void setCurrentPayment(double currentPayment) {
        this.currentPayment = currentPayment;
    }

    public double getApr() {
        return apr;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public double getCurrentPayment() {
        return currentPayment;
    }

    public static void main(String[] args){

        Debt d = new Debt(240000, 4,1166);
        System.out.println(d.monthsRemaining());

    }
}
