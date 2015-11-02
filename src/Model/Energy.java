package model;

/**
 * Created by Shannor on 9/20/2015.
 * Class to hold Energy types
 * Will be held by Players and Stores
 */
public class Energy {
    /**
     * Variable to hold the amount of energy owned.
     */
    private int amount;

    /**
     * Variable that holds the starting amount.
     */
    private final static int startAmount = 4;

    /**
     * Constructor for Energy.
     * Init to the default amount.
     */
    public Energy() {
        this.amount = startAmount;
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
