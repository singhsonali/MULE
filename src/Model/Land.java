package Model;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 * Created by Ashley on 9/20/2015.
 */
public class Land {
    private int mountain;
    private boolean river;
    private boolean open;
    private boolean town = false;

    private final int cost = 300; //Constant cost for all pieces of land
    //Added a player as an owner of the Land that is set when purchased.
    //Also needed to set the color of the land
    private Player player;
    @FXML
    private Pane myPane;

    public Land() {
        this.mountain = 0;
        this.river = false;
        this.open = true;
        this.player = null;
    }
    public Land(int mountain) {
        this.mountain = mountain;
        this.river = mountain != 0;
        this.open = true;
        this.player = null;
    }
    public Land(boolean river) {
        this.river = river;
        if (river) {
            this.mountain = 0;
            this.open = true;
        }
        this.player = null;
    }
    public Land(Town town) {
        this.mountain = 0;
        this.river = false;
        this.open = false;
        this.town = true;
    }

    public boolean hasRiver() {
        return this.river;
    }
    public boolean hasMountain() {
        return this.mountain != 0;
    }
    public boolean isOpen () { return this.open;}
    public boolean isPlain () {
        return this.mountain == 0 && !this.river;
    }
    public int getMountain () {
        return this.mountain;
    }
    public boolean isTown () {
        return town;
    }
    public Player getPlayer(){return this.player;}
    public Pane getMyPane(){
        return this.myPane;
    }
    public int getCost(){
        return this.cost;
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
    public void setPlayerOwner(Player player){
        this.player = player;
    }
    public void setMyPane(Pane pane){
        this.myPane = pane;
    }
}