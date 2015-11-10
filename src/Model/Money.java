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
     * Variable to hold money value.
     */
    private static final int M1 = 1600;
    /**
     * Variable to hold the amount of food owned.
     */
    private static final int M2 = 600;
    /**
     * Variable to hold the amount of food owned.
     */
    private static final int M3 = 1000;
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
        switch (player.getRace()) {
        case "Flapper":
            this.amount = M1;
            break;
        case "Human":
            this.amount = M2;
            break;
        default:
            this.amount = M3;
            break;
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
