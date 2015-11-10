package model;
/**
 * Money created by Shannor.
 * Class to hold Money item
 * Will be held by Player and the stores
 */
public class Money implements java.io.Serializable {
    /**
     * Variable to hold the amount of food owned.
     */
    private int amount;
    /**
     * Constructor for Money.
     * Init to the default amount.
     */
    public Money() {
        this.amount = 0;
    }
    /**
     * constructor for mountain panes.
     * @param player Player.
     */
    public Money(final Player player) {
        if (player.getRace().equals("Flapper")) {
            this.amount = 1600;
        } else if (player.getRace().equals("Human")) {
            this.amount = 600;
        } else {
            this.amount = 1000;
        }
    }
    /**
     * Sets the amount to a new variable.
     * @param i The new amount desired for money.
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