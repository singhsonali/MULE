package Model;

/**
 * Created by Shannor on 9/20/2015.
 * Food class inherates form Resources
 */
public class Food extends Resources {

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
