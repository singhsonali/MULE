package Model;

/**
 * Created by Ashley on 9/20/2015.
 */
public class Land {
    private int mountain;
    private boolean river;
    private boolean open;
    private boolean town = false;

    public Land() {
        this.mountain = 0;
        this.river = false;
        this.open = true;
    }
    public Land(int mountain) {
        this.mountain = mountain;
        this.river = mountain != 0;
        this.open = true;
    }
    public Land(boolean river) {
        this.river = river;
        if (river) {
            this.mountain = 0;
            this.open = true;
        }
    }
    public Land(Town town) {
        this.mountain = 0;
        this.river = false;
        this.open = false;
        this.town = true;
    }

    public boolean hasRiver(Land land) {
        return this.river;
    }
    public boolean hasMountain(Land land) {
        return this.mountain != 0;
    }
    public boolean isOpen (Land land) { return this.open;}
    public boolean isPlain (Land land) {
        return this.mountain == 0 && !this.river;
    }
    public int getMountain (Land land) {
        return this.mountain;
    }
    public boolean isTown (Land land) {
        return town;
    }

    public void setMountain (int mountain) {
        this.mountain = mountain;
    }
    public void setRiver (boolean river) {
        this.river = river;
    }
    public void setOpen (boolean open) {
        this.open = open;
    }
    public void setTown (boolean town) {
        this.town = town;
    }
}
