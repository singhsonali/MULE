package model;

/**
 * Created by Ashley on 9/20/2015.
 */
public class Store {

    /**
     * Variable to hold initial mule amount.
     */
    private static final int MULE_AMOUNT = 25;
    /**
     * Variable to hold initial food amount.
     */
    private static final int FOOD_AMOUNT = 16;
    /**
     * Variable to hold initial ore amount.
     */
    private static final int ORE_AMOUNT = 0;
    /**
     * Variable to hold initial energy amount.
     */
    private static final int ENERGY_AMOUNT = 16;
    /**
     * Variable to hold mule amount.
     */
    private int muleAmnt;
    /**
     * Variable to hold food amount.
     */
    private int foodAmnt;
    /**
     * Variable to hold ore amount.
     */
    private int oreAmnt;
    /**
     * Variable to hold energy amount.
     */
    private int energyAmnt;

    /**
     * Constructor for Store.
     */
    public Store() {
        muleAmnt = MULE_AMOUNT;
        foodAmnt = FOOD_AMOUNT;
        oreAmnt = ORE_AMOUNT;
        energyAmnt = ENERGY_AMOUNT;
    }

    /**
     * Subtracts a mule from the store inventory.
     */
    public final void deleteMule() {
        muleAmnt--;
    }

    /**
     * Returns how many mules are in the store.
     *
     * @return the Store's mule amount
     */
    public final int getMuleAmount() {
        return muleAmnt;
    }

    /**
     * Sets the Store's food amount.
     *
     * @param food the store's new food amount
     */
    public final void setFood(final int food) {
        foodAmnt = food;
    }

    /**
     * Returns how much food is in the store.
     *
     * @return the Store's food amount
     */
    public final int getFood() {
        return foodAmnt;
    }

    /**
     * Sets the Store's pre amount.
     *
     * @param ore the store's new ore amount
     */
    public final void setOre(final int ore) {
        oreAmnt = ore;
    }

    /**
     * Returns how much ore is in the store.
     *
     * @return the Store's ore amount
     */
    public final int getOre() {
        return oreAmnt;
    }

    /**
     * Sets the Store's energy amount.
     *
     * @param energy the store's new food amount
     */
    public final void setEnergy(final int energy) {
        energyAmnt = energy;
    }

    /**
     * Returns how much energy is in the store.
     *
     * @return the Store's energy amount
     */
    public final int getEnergy() {
        return energyAmnt;
    }

}