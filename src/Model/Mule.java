package Model;

/**
 * Created by Ashley on 9/30/2015.
 */
public class Mule extends Resources {

    public Mule() {
    this.amount = 0;
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
