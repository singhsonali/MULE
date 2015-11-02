package model;

/**
 * Created by Shannor on 9/20/2015.
 * Class to hold Energy types
 * Will be held by Players and Stores
 */
public class Energy {
    private int amount;

    public Energy(){
        this.amount = 4; //default
    }

    void setAmount(int i) {
        this.amount = i;
    }

    int getAmount() {
        return this.amount;
    }
}
