package model;

/**
 * Created by Ashley on 9/30/2015.
 */
public class Mule implements java.io.Serializable {
    private  int amount;
    public Mule() {
         this.amount = 0;
    }
    void setAmount(int i) {
        this.amount = i;
    }

    int getAmount() {
        return this.amount;
    }
}