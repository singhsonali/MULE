package model;

/**
 * Created by Shannor on 9/20/2015.
 * Abstract Class that all AbstractResources pull from
 * Has the basic functions that each resource overwrites
 */
public abstract class AbstractResources {
    /**
     * Abstract method that will be implemented by inherit Classes.
     * @param newAmount amount to set
     */
    abstract void setAmount(int newAmount);

    /**
     * Return the integer stored in amount.
     * @return amount
     */
    abstract int getAmount();

}
