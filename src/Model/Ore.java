package model;

/**
 * Created by rileyauten on 9/30/15.
 */
public class Ore extends AbstractResources {

    private int amount;

    public Ore(){
        this.amount = 0; //default
    }

    @Override
    void setAmount(int i) {
        this.amount = i;
    }

    @Override
    int getAmount() {
        return this.amount;
    }
}
