package net.softwareDesign.budgeter;

import java.net.URI;

/**
 * Interest rate, time until paid off, and value
 */
public class Debt {
    /**
     * Interest per year
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
        int months = 0;
        double balance = currentValue;
        while(balance > 0) {
            months++;
            //apply interest
            balance *= 1+ (apr/12);
            balance -= currentPayment;

            //give up!
            if(months > 600) {
                return 600;
            }
        }
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
}
