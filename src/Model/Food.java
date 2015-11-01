package model;

/**
 * Created by Shannor on 9/20/2015.
 * Food class inherits from AbstractResources
 * Food is given at the start and updated as the game goes on
 */
public class Food extends AbstractResources {

    /**
     * Variable to hold the amount of food owned.
     */
    private int amount;
    /**
     * Variable that holds the starting amount.
     */
    private final int startAmount = 8;

    /**
     * Constructor for Food.
     * Init to the default amount.
     */
    public Food() {
        super();
        this.amount = startAmount; //default
    }

    /**
     * Sets the amount to a new variable.
     * @param i The new amount desired for Ore.
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
