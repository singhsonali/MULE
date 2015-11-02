package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shannor on 9/16/2015.
 * Will hold all information about a player,
 * Up to four players
 *
 */
public class Player implements java.io.Serializable {
    /**
     * Player's name.
     */
    private String name;
    /**
     * Player's race.
     */
    private String race;
    /**
     * Player's color.
     */
    private String color;
    /**
     * Player's land grants.
     * Each player gets two for receiving free land.
     */
    private int landGrants;
    /**
     * Player's food.
     */
    private Food food;
    /**
     * Player's energy.
     */
    private Energy energy;
    /**
     * Player's money.
     */
    private Money money;
    /**
     * Player's ore.
     */
    private Ore ore;
    /**
     * Player's ore Mules.
     */
    private Mule oreMule;
    /**
     * Player's energy Mules.
     */
    private Mule energyMule;
    /**
     * Player's food Mules.
     */
    private Mule foodMule;
    /**
     * Player's total Mules.
     */
    private int totalMule;
    /**
     * Player's holding mule.
     */
    private String holdingMule;
    /**
     * The current round.
     */
    private Round round;

    /**
     * Constants for energy, food, landGrants, and ore.
     */
    private final static int energyConstant = 25, foodConstant = 30,
            landGrantConstant = 500, oreConstant = 50;

    /**
     * Player holds an array of owned land.
     */
    private List<Land> ownedLand;

    /**
     * Variable used in roundTime calculation.
     */
    private final static int roundTimeNum = 5;

    /**
     * Creates a new player with name, race, and color.
     * @param playerName the player's name
     * @param playerRace the player's race
     * @param playerColor the player's color
     */
    public Player(final String playerName, final String playerRace,
                  final String playerColor) {
        this.name = playerName;
        this.race = playerRace;
        this.color = playerColor;
        this.landGrants = 2;
        this.food = new Food();
        this.ore = new Ore();
        this.energy = new Energy();
        this.money = new Money(this);
        this.foodMule = new Mule();
        this.energyMule = new Mule();
        this.oreMule = new Mule();
        this.ownedLand = new ArrayList<>();
        this.ore = new Ore();
        totalMule = calcTotalMules();
        this.round = new Round();
    }

    /**
     * Returns player name.
     * @return player name
     */
    public final String getName() {
        return this.name;
    }
    /**
     * Returns player race.
     * @return player race
     */
    final String getRace() {
        return this.race;
    }
    /**
     * Returns player color.
     * @return player color
     */
    public final String getColor() {
        return this.color;
    }
    /**
     * Returns player's land grants.
     * @return player land grants
     */
    final int getLandGrants() {
        return this.landGrants;
    }
    /**
     * Returns player food.
     * @return player food
     */
    public final int getFood() {
        return this.food.getAmount();
    }
    /**
     * Returns player money.
     * @return player money
     */
    public final int getMoney() {
        return this.money.getAmount();
    }
    /**
     * Returns player energy.
     * @return player energy
     */
    public final int getEnergy() {
        return this.energy.getAmount();
    }
    /**
     * Returns player ore.
     * @return player ore
     */
    public final int getOre() {
        return this.ore.getAmount();
    }
    /**
     * Returns player ore mule.
     * @return player ore mule
     */
    public final int getOreMule() {
        return this.oreMule.getAmount();
    }
    /**
     * Returns player energy mule.
     * @return player energy mule
     */
    public final int getEnergyMule() {
        return this.energyMule.getAmount();
    }
    /**
     * Returns player food mule.
     * @return player food mule
     */
    public final int getFoodMule() {
        return this.foodMule.getAmount();
    }

    /**
     * Returns the player holding mule.
     * @return player holding mule
     */
    public final String getHoldingMule() {
        return this.holdingMule;
    }

    /**
     * Calculates the round time of a player based on resources.
     * @return the round time of player
     */
    public final int calcRoundTime() {
        if (getFood() == 0 || totalMule != 0
                && energy.getAmount() == 0) { //No food or no energy for mules
            return roundTimeNum;
        } else if (!this.round.checkRequirement(food) || energy.getAmount()
                < totalMule) { //Not enough food or energy for mules
            return roundTimeNum * 2 * 2 * 2; // = 30 (Magic Numbers suck)
        } else { //Meets food and energy requirement
            return roundTimeNum * roundTimeNum + roundTimeNum; // = 50
        }
    }

    /**
     * Calculates the total mules of a player.
     * @return the total mules of a player
     */
    final int calcTotalMules() {
        return oreMule.getAmount() + energyMule.getAmount()
                + foodMule.getAmount();
    }

    /**
     * Adds an amount of money to the player's money.
     * @param i the amount of money added
     */
    public final void addMoney(final int i) {
        int temp = this.money.getAmount();
        temp += i;
        this.money.setAmount(temp);
    }

    /**
     * Subtracts an amount of money to the player's money.
     * @param i the amount of money subtracted
     */
    public final void subtractMoney(final int i) {
        int temp = this.money.getAmount();
        temp -= i;
        this.money.setAmount(temp);
    }

    /**
     * Sets the player's food.
     * @param i the player's new food amount
     */
    public final void setFood(final int i) {
        this.food.setAmount(i);
    }

    /**
     * Sets the player's money.
     * @param i the player's new money amount
     */
    public final void setMoney(final int i) {
        this.money.setAmount(i);
    }
    /**
     * Sets the player's energy.
     * @param i the player's new energy amount
     */
    public final void setEnergy(final int i) {
        this.energy.setAmount(i);
    }
    /**
     * Sets the player's ore.
     * @param i the player's new ore amount
     */
    public final void setOre(final int i) {
        this.ore.setAmount(i);
    }

    //There are three types of MULE a player can buy, all have different prices

    /**
     * Sets the player's ore mule amount.
     * @param i player's new ore mule amount
     */
    public final void setOreMule(final int i) {
        this.oreMule.setAmount(i);
    }
    /**
     * Sets the player's energy mule amount.
     * @param i player's new energy mule amount
     */
    public final void setEnergyMule(final int i) {
        this.energyMule.setAmount(i);
    }
    /**
     * Sets the player's food mule amount.
     * @param i player's new food mule amount
     */
    public final void setFoodMule(final int i) {
        this.foodMule.setAmount(i);
    }

    /**
     * Sets the player's holding mule string.
     * @param s the holding mule string
     */
    public final void setHoldingMule(final String s) {
        holdingMule = s;
    }

    /**
     * Whether an owned land is empty or not.
     * @return whether land is empty
     */
    public final boolean hasLand() {
        return !ownedLand.isEmpty();
    }
    /**
     * Whether a player has land grants or not.
     * @return whether player has land grants
     */
    public final boolean haveLandGrants() {
        return this.landGrants > 0;
    }
    /**
     * Whether a player has land grants or not.
     * If true, subtract one from land grants.
     * @return whether player has land grants
     */
    public final boolean useLandGrant() {
        if (haveLandGrants()) {
            this.landGrants--;
            return  true;
        }
        return false; //Player is out of landGrants
    }

    /**
     * Whether a player has a holding mule or not.
     * @return whether player has holding mule
     */
    public final boolean hasMule() {
        return holdingMule != null;
    }

    /**
     * Adds land to owned land list.
     * @param land land to be added
     */
    public final void addLand(final Land land) {
        ownedLand.add(land);
    }

    /**
     * Calculates the player's score.
     * @return player's score
     */
    public final int calcScore() {
        return getMoney() + (getEnergy() * energyConstant)
                + (getFood() * foodConstant)
                + (getLandGrants() * landGrantConstant)
                + (getOre() * oreConstant);
    }

    /**
     * For every owned land of the player with a mule on it,
     * resources are produced.
     */
    public final void muleProduction() {
        for (Land land : ownedLand) {
            land.updatePlayerResources();
        }
    }

    /**
     * Happens if a player wants to replace the mule
     * they currently have on a piece of land.
     * @param muleType the type of mule to be removed
     */
    public final void removeMule(final String muleType) {
        if (muleType.equals("Ore")) {
            setOreMule(getOreMule() - 1);
        } else if (muleType.equals("Food")) {
            setFoodMule(getFoodMule() - 1);
        } else if (muleType.equals("Energy")) {
            setEnergyMule(getEnergyMule() - 1);
        }
    }

    /**
     * Returns a string of player resource data for printing.
     * @return string of player resource data
     */
    public final String getResources() {
        String nextLine = "\n";
        return "Player: " + getName() + nextLine
                + "Money: " + getMoney() + nextLine
                + "Food: " + getFood() + nextLine
                + "Energy: " + getEnergy() + nextLine
                + "Ore: " + getOre() + nextLine
                + "==================================================";
    }
}
