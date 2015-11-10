package model;

/**
 * Created by Shannor on 9/20/2015.
 * Class to hold Energy types
 * Will be held by Players and Stores
 */
public class Energy implements java.io.Serializable {
    /**
     * Variable to hold the amount of energy owned.
     */
    private int amount;
    /**
     * Variable that holds the starting amount.
     */
    private static final int START_AMOUNT = 4;
    /**
     * Constructor for Energy.
     * Init to the default amount.
     */
    public Energy() {
        this.amount = START_AMOUNT;
    }
    /**
     * Sets the amount to a new variable.
     * @param i The new amount desired for energy.
     */
    final void setAmount(final int i) {
        this.amount = i;
    }
    /**
     * Returns the current value stored in amount.
     * @return amount
     */
    final int getAmount() {
        return this.amount;
    }
}
