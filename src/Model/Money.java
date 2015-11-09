package model;

/**
 * Created by Shannor on 9/20/2015.
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
    public Money(Player player) {
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