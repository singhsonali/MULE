package model;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 * Created by Ashley on 9/20/2015.
 */
public class Land {

    /**
     * The number of mountains a piece of land has. 0-3.
     */
    private int mountain;

    /**
     * Does the land have a river?
     */
    private boolean river;

    /**
     * Is the land open?
     */
    private boolean open;

    /**
     * Panes are automatically set to not be the town pane.
     */
    private boolean town = false;

    /**
     * Creates a new oreMule.
     */
    private Mule oreMule = new Mule();

    /**
     * Creates a new foodMule.
     */
    private Mule foodMule = new Mule();

    /**
     * Creates a new energyMule.
     */
    private Mule energyMule = new Mule();

    /**
     * Land pieces, when instantiated, do not
     * start off having Mule.
     */
    private boolean hasMule = false;

    /**
     * Constant cost for all pieces of land.
     * Added a player as an owner of the Land that is set when purchased.
     * Also needed to set the color of the land.
     */
    private final int cost = 300;

    /**
     * A player in the game.
     */
    private Player player;

    /**
     * A pane in the land map.
     */
    @FXML
    private Pane myPane;

    /**
     * constructor for land.
     */
    public Land() {
        this.mountain = 0;
        this.river = false;
        this.open = true;
        this.player = null;
    }

    /**
     * constructor for mountain panes.
     * @param mountains mountains.
     */
    public Land(int mountains) {
        this.mountain = mountains;
        this.river = mountain != 0;
        this.open = true;
        this.player = null;
    }

    /**
     * constructor for river panes.
     * @param rivers rivers.
     */
    public Land(boolean rivers) {
        this.river = rivers;
        if (river) {
            this.mountain = 0;
            this.open = true;
        }
        this.player = null;
    }

    /**
     * constructor for the town pane.
     * @param town the town.
     */
    public Land(String town) {
        if (town.equals("Town")) {
            this.mountain = 0;
            this.river = false;
            this.open = false;
            this.town = true;
        }

    }

    /**
     * method for the following.
     * @return boolean: does the land have a river?
     */
    public boolean hasRiver() {
        return this.river;
    }

    /**
     * method for the following.
     * @return boolean: does the land have mountains.
     */
    public boolean hasMountain() {
        return this.mountain != 0;
    }

    /**
     * method for the following.
     * @return boolean: does the land have mule?
     */
    public boolean hasMule() {
        return getTotalMule() != 0;
    }

    /**
     * method for the following.
     * @param bool set if a land pane has mule or not.
     */
    public void setHasMule(boolean bool) {
        hasMule = bool;
    }

    /**
     * method for the following.
     * @return boolean: is the pane open?
     */
    public boolean isOpen() {
        return this.open;
    }

    /**
     * method for the following.
     * @return boolean: is the pane a plain pane?
     */
    public boolean isPlain() {
        return this.mountain == 0 && !this.river;
    }

    /**
     * getter method for the following.
     * @return int: numbers of mountains.
     */
    public int getMountain() {
        return this.mountain;
    }

    /**
     * method for the following.
     * @return boolean: is the pane the town pane?
     */
    public boolean isTown() {
        return town;
    }

    /**
     * method for the following.
     * @return Player: the selected player.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * method for the following.
     * @return Pane: the pane selected.
     */
    public Pane getMyPane() {
        return this.myPane;
    }

    /**
     * getter method for the following.
     * @return int: the total cost.
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * getter method for the following.
     * @return int: the total ore mule.
     */
    public int getOreMule() {
        return this.oreMule.getAmount();
    }

    /**
     * getter method for the following.
     * @return int: the total energy mule.
     */
    public int getEnergyMule() {
        return this.energyMule.getAmount();
    }

    /**
     * getter method for the following.
     * @return int: the total food mule.
     */
    public int getFoodMule() {
        return this.foodMule.getAmount();
    }

    /**
     * getter method for the following.
     * @return int: the total mule.
     */
    public int getTotalMule() {
        return getOreMule() + getFoodMule() + getEnergyMule();
    }

    /**
     * getter method for the following.
     * @return String, the type of mule on the land.
     */
    public String getMuleType() {
        if (getOreMule() == 1) {
            return "oreMule";
        } else if (getEnergyMule() == 1) {
            return "energyMule";
        } else if (getFoodMule() == 1) {
            return "foodMule";
        }
        return null;
    }

    /**
     * Sets a pane as having mountains or not.
     * @param mountains How many mountains does a certain pane have.
     */
    public void setMountain(int mountains) {
        this.mountain = mountains;
    }

    /**
     * Sets a pane as having a river or not.
     * @param rivers Does a certain pain have a river?
     */
    public void setRiver(boolean rivers) {
        this.river = rivers;
    }

    /**
     * Sets as open or not.
     * @param isOpen Is a certain pane open or not.
     */
    public void setOpen(boolean isOpen) {
        this.open = isOpen;
    }
    /**
     * Sets the town.
     * @param townCenter Is a certain pane town pane or not.
     */
    public void setTown(boolean townCenter) {
        this.town = townCenter;
    }

    /**
     * Dictates which player owns a piece of land.
     * @param players player owning the land.
     */
    public void setPlayerOwner(Player players) {
        this.player = players;
    }

    /**
     * Sets the pane.
     * @param pane pane you want to be set.
     */
    public void setMyPane(Pane pane) {
        this.myPane = pane;
    }

    /**
     * Setter to allocate ore mule.
     * @param i amount of ore mule you want to be allocated. Land
     * may have either 0 or 1 mule.
     */
    public void setOreMule(int i) {
        this.oreMule.setAmount(i);
    }

    /**
     * Setter to allocate energy mule.
     * @param i amount of energy mule you want to be allocated. Land
     * may have either 0 or 1 mule.
     */
    public void setEnergyMule(int i) {
        this.energyMule.setAmount(i);
    }

    /**
     * Setter to allocate food mule.
     * @param i amount of food mule you want to be allocated. Land
     * may have either 0 or 1 mule.
     */
    public void setFoodMule(int i) {
        this.foodMule.setAmount(i);
    }

    /**
     * Sets all Mule amounts to 0.
     */
    public void clearMule() {
        this.foodMule.setAmount(0);
        this.oreMule.setAmount(0);
        this.energyMule.setAmount(0);
    }

    /**
     * Gives a player resources they obtain from owning land
     * and Mule.
     */
    public void updatePlayerResources() {
        if (player.getEnergy() >= player.calcTotalMules()) {
            if (isPlain() && hasMule()) {
                if (getMuleType().equals("foodMule")) {
                    player.setFood(player.getFood() + 2);
                } else if (getMuleType().equals("oreMule")) {
                    player.setOre(player.getOre() + 1);
                } else {
                    player.setEnergy(player.getEnergy() + 1 + 2);
                }
            } else if (hasRiver() && hasMule()) {
                if (getMuleType().equals("foodMule")) {
                    player.setFood(player.getFood() + 2 + 2);
                } else if (getMuleType().equals("energyMule")) {
                    player.setEnergy(player.getEnergy() + 2);
                }
            } else if (getMountain() == 1 && hasMule()) {
                if (getMuleType().equals("foodMule")) {
                    player.setFood(player.getFood() + 1);
                } else if (getMuleType().equals("oreMule")) {
                    player.setOre(player.getOre() + 2);
                } else {
                    player.setEnergy(player.getEnergy() + 1);
                }
            } else if (getMountain() == 2 && hasMule()) {
                if (getMuleType().equals("foodMule")) {
                    player.setFood(player.getFood() + 1);
                } else if (getMuleType().equals("oreMule")) {
                    player.setOre(player.getOre() + 1 + 2);
                } else {
                    player.setEnergy(player.getEnergy() + 1);
                }
            } else if (getMountain() == (1 + 2) && hasMule()) {
                if (getMuleType().equals("foodMule")) {
                    player.setFood(player.getFood() + 1);
                } else if (getMuleType().equals("oreMule")) {
                    player.setOre(player.getOre() + 2 + 2);
                } else {
                    player.setEnergy(player.getEnergy() + 1);
                }
            }
        } else {
            System.out.println("Not enough energy for MULEs to produce.");
        }
    }
}
