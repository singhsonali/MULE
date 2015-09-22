package Model;

/**
 * Created by Shannor on 9/20/2015.
 * Abstract Class that all Resources pull from
 * Has the basic functions that each resource overwrites
 */
public abstract class Resources {

    protected int amount;

    abstract void setAmount(int i);
    abstract int getAmount();

}
