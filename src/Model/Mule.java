package model;

/**
 * Mule by Ashley.
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
    public final void setAmount(final int i) {
        this.amount = i;
    }
    /**
     * Returns the current value stored in amount.
     * @return amount
     */
    public final int getAmount() {
        return this.amount;
    }
}
