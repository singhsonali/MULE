package model;

/**
 * Created by Shannor on 9/20/2015.
 * Food class inherits from AbstractResources
 * Food is given at the start and updated as the game goes on
 */
public class Food extends AbstractResources {
    private int amount;

    public Food(){
        this.amount = 8;
    }
    //Update method

    @Override
    void setAmount(int i) {
        this.amount = i;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }
}