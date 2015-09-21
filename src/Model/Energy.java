package Model;

/**
 * Created by Shannor on 9/20/2015.
 */
public class Energy extends Resources {

    public Energy(){
        this.amount = 4; //default
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
