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
    private Mule oreMule = new Mule();
    private Mule foodMule = new Mule();
    private Mule energyMule = new Mule();
    private boolean hasMule = false;

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

    public boolean hasMule() {
        return getTotalMule() != 0;
    }
    public void setHasMule(boolean bool) {
        hasMule = bool;
     }

    public boolean isOpen() {
        return this.open;
    }

    public boolean isPlain() {
        return this.mountain == 0 && !this.river;
    }

    public int getMountain() {
        return this.mountain;
    }

    public boolean isTown() {
        return town;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Pane getMyPane() {
        return this.myPane;
    }

    public int getCost() {
        return this.cost;
    }

    public int getOreMule() {
        return this.oreMule.getAmount();
    }

    public int getEnergyMule() {
        return this.energyMule.getAmount();
    }

    public int getFoodMule() {
        return this.foodMule.getAmount();
    }

    public int getTotalMule() {
        return getOreMule() + getFoodMule() + getEnergyMule();
    }

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


    public void setMountain(int mountain) {
        this.mountain = mountain;
    }

    public void setRiver(boolean river) {
        this.river = river;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setTown(boolean town) {
        this.town = town;
    }

    public void setPlayerOwner(Player player) {
        this.player = player;
    }

    public void setMyPane(Pane pane) {
        this.myPane = pane;
    }

    public void setOreMule(int i) {
        this.oreMule.setAmount(i);
    }

    public void setEnergyMule(int i) {
        this.energyMule.setAmount(i);
    }

    public void setFoodMule(int i) {
        this.foodMule.setAmount(i);
    }

    public void clearMule() {
        this.foodMule.setAmount(0);
        this.oreMule.setAmount(0);
        this.energyMule.setAmount(0);
    }

    public void updatePlayerResources() {
        if (player.getEnergy() >= player.calcTotalMules()) {
            if (isPlain() && hasMule()) {
                if (getMuleType().equals("foodMule")) {
                    player.setFood(player.getFood() + 2);
                } else if (getMuleType().equals("oreMule")) {
                    player.setOre(player.getOre() + 1);
                } else {
                    player.setEnergy(player.getEnergy() + 3);
                }
            } else if (hasRiver() && hasMule()) {
                if (getMuleType().equals("foodMule")) {
                    player.setFood(player.getFood() + 4);
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
                    player.setOre(player.getOre() + 3);
                } else {
                    player.setEnergy(player.getEnergy() + 1);
                }
            } else if (getMountain() == 3 && hasMule()) {
                if (getMuleType().equals("foodMule")) {
                    player.setFood(player.getFood() + 1);
                } else if (getMuleType().equals("oreMule")) {
                    player.setOre(player.getOre() + 4);
                } else {
                    player.setEnergy(player.getEnergy() + 1);
                }
            }
        } else {
            System.out.println("Not enough energy for MULEs to produce.");
        }
    }
}
