package model;

/**
 * Created by Shannor on 9/18/2015.
 * Class Town, which holds all the different type of store's in the game
 */
public class Town {
    /**
     * private instance of Store Class.
     */
    private Store store;
    /**
     * private instance of Land Office Class.
     */
    private LandOffice landOffice;
    /**
     * private instance of Pub Class.
     */
    private Pub pub;

    /**
     * Constructor for the Town.
     */
    public Town() {
        this.store = new Store();
        this.landOffice = new LandOffice();
        this.pub = new Pub();
    }
}
