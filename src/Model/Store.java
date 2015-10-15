package Model;

/**
 * Created by Ashley on 9/20/2015.
 */
public class Store {

    private int MULE_AMOUNT = 25;
    private int FOOD_AMOUNT = 16;
    private int ORE_AMOUNT = 0;
    private int ENERGY_AMOUNT = 16;

    public Store() {

    }

    public void deleteMule() {
        MULE_AMOUNT--;
    }

    public int getMuleAmount() {
        return MULE_AMOUNT;
    }

    public void setFood(int food) {
        FOOD_AMOUNT = food;
    }

    public int getFood() {
        return FOOD_AMOUNT;
    }

    public void setOre(int ore) {
        ORE_AMOUNT = ore;
    }

    public int getOre() {
        return ORE_AMOUNT;
    }

    public void setEnergy(int energy) {
        ENERGY_AMOUNT = energy;
    }

    public int getEnergy() {
        return ENERGY_AMOUNT;
    }

}
