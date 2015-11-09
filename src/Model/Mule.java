package model;

/**
 * Created by Ashley on 9/30/2015.
 */
public class Mule implements java.io.Serializable {
    /**
     * Variable to hold the amount of food owned.
     */
    private  int amount;
    /**
     * Constructor for Mule.
     * Init to the default amount.
     */
    public Mule() {
        this.amount = 0;
    }
    /**
     * Sets the amount to a new variable.
     * @param i The new amount desired for mule.
     */
    void setAmount(int i) {
        this.amount = i;
    }
    /**
     * Returns the current value stored in amount.
     * @return amount
     */
    int getAmount() {
        return this.amount;
    }
}