package model;

/**
 * Created by rileyauten on 9/30/15.
 * Land Resource class
 */
public class Ore {
    /**
     * Variable to hold the amount of Ore Owned.
     */
    private int amount;

    /**
     * Constructor for Ore.
     * Init to the default amount.
     */
    public Ore() {
        super();
        this.amount = 0; //default
    }

    /**
     * Sets the amount to a new variable.
     * @param newAmount The new amount desired for Ore.
     */
    final void setAmount(final int newAmount) {
        this.amount = newAmount;
    }

    /**
     * Returns the current value stored in amount.
     * @return amount
     */
    final int getAmount() {
        return this.amount;
    }
}
