package Model;

import java.util.ArrayList;

/**
 * Created by Shannor on 9/16/2015.
 * Will hold all information about a player,
 * Up to four players
 *
 */
public class Player {
    private String name;
    private String race;
    private String color;
    //Turn will be given to the player
    private int turn;
    private int playerNum;
    //Each player gets two for receiving free land
    private int landGrants;
    //The number the player is when created, Class variable
    private static int playerNumber = 0;
    //Resources
    private Food food;
    private Energy energy;
    private Money money;
    private Ore ore;
    private Mule oreMule;
    private Mule energyMule;
    private Mule foodMule;
    private int totalMule;
    private String holdingMule;


    //How much time they have for the round
    private int roundTime;
    private Round round;

    private final int ENERGY_CONST = 25, FOOD_CONST = 30, LAND_GRAND_CONST = 500, ORE_CONST = 50;

    //Player holds an array of owned land
    private ArrayList<Land> ownedLand;

    public Player(){
        this.name = "temp";
        this.race = "temp";
        this.color = "temp";
        this.playerNum = ++playerNumber;
        this.landGrants = 2;

    }
    public Player(String name, String race, String color){
        this.name = name;
        this.race = race;
        this.color = color;
        this.playerNum =  ++playerNumber;
        this.landGrants = 2;
        this.food = new Food();
        this.ore = new Ore();
        this.energy = new Energy();
        this.money = new Money(this);
        this.foodMule = new Mule();
        this.energyMule = new Mule();
        this.oreMule = new Mule();
        this.ownedLand = new ArrayList<Land>();
        this.ore = new Ore();
        totalMule = calcTotalMules();
        this.round = new Round();
        this.holdingMule = holdingMule;
    }


    public String getName(){
        return this.name;
    }

    public String getRace(){
        return this.race;
    }
    public String getColor(){
        return this.color;
    }
    public int getTurn(){
        return this.turn;
    }
    public int getPlayerNum(){
        return this.playerNum;
    }
    public int getLandGrants(){return this.landGrants;}
    public int getFood(){
        return this.food.getAmount();
    }
    public int getMoney(){
        return this.money.getAmount();
    }
    public int getEnergy(){
        return this.energy.getAmount();
    }
    public int getOre() { return this.ore.getAmount(); }
    public int getOreMule() {return this.oreMule.getAmount(); }
    public int getEnergyMule() {return this.energyMule.getAmount(); }
    public int getFoodMule() {return this.foodMule.getAmount(); }
    public String getHoldingMule() {return this.holdingMule; }

    public int calcRoundTime() {
        if (getFood() == 0 || totalMule != 0 && energy.getAmount() == 0) { //No food or no energy for mules
            return 5;
        } else if (!this.round.checkRequirement(food) || energy.getAmount() < totalMule) { //Not enough food or energy for mules
            return 30;
        } else { //Meets food and energy requirement
            return 50;
        }
    }

    public int calcTotalMules() {
        return oreMule.getAmount() + energyMule.getAmount() + foodMule.getAmount();
    }


    public void addMoney(int i){
        int temp = this.money.getAmount();
        temp += i;
        this.money.setAmount(temp);
    }
    public void subtractMoney(int i){
        int temp = this.money.getAmount();
        temp -= i;
        this.money.setAmount(temp);
    }

    public void setName(String name){
        this.name = name;
    }
    public void setRace(String race){
        this.race = race;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setTurn(int i){
        this.turn = i;
    }
    public void setFood(int i){this.food.setAmount(i);}
    public void setMoney(int i){this.money.setAmount(i);}
    public void setEnergy(int i){this.energy.setAmount(i);}
    public void setOre(int i){this.ore.setAmount(i);}

    //There are three types of MULE a player can buy, all have different prices
    public void setOreMule(int i){this.oreMule.setAmount(i);}
    public void setEnergyMule(int i){this.energyMule.setAmount(i);}
    public void setFoodMule(int i){this.foodMule.setAmount(i);}
    public void setHoldingMule(String s){
        holdingMule = s;
        System.out.println("HoldingMule set.");
    }

    public boolean haveLandGrants(){
        return this.landGrants > 0;
    }
    public boolean useLandGrant(){
        if(haveLandGrants()) {
            this.landGrants--;
            return  true;
        }
        return false ; //Player is out of landGrants
    }

    public boolean hasMule() {
        if (holdingMule != null) {
            return true;
        } else {
            return false;
        }
    }

    public void addLand(Land land){
        ownedLand.add(land);
    }
    public void removeLand(Land land){
        if(ownedLand.contains(land)) {
            ownedLand.remove(land);
        }
    }

    public int calcScore() {
        return getMoney() + (getEnergy()*ENERGY_CONST) + (getFood()*FOOD_CONST) +
                (getLandGrants() * LAND_GRAND_CONST) + (getOre() * ORE_CONST);
    }

    public void muleProduction() {
        for (Land land : ownedLand) {
            land.updatePlayerResources();
        }
    }

    // onMouseClick event
    public void buyOreMule() {
        if (money.getAmount() < 175) {
            throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        }
        setMoney(money.getAmount() - 175);
        setOreMule(oreMule.getAmount() + 1);
    }

    // onMouseClick event
    public void buyEnergyMule() {
        if (money.getAmount() < 150) {
            throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        }
        setMoney(money.getAmount() - 150);
        setEnergyMule(energyMule.getAmount() + 1);
    }

    //onMouseClick event
    public void buyFoodMule() {
        if (money.getAmount() < 125) {
            throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        }
        setMoney(money.getAmount() - 125);
        setFoodMule(foodMule.getAmount() + 1);
    }

    // happens if a player wants to replace the MULE he currently has on a piece of land
    public void removeMule(String muleType) {
        if (muleType.equals("Ore")) {
            setOreMule(getOreMule() - 1);
        } else if (muleType.equals("Food")) {
            setFoodMule(getFoodMule() - 1);
        } else if (muleType.equals("Energy")) {
            setEnergyMule(getEnergyMule() - 1);
        }
    }
    public String getResources() {
        return "Player: " + getName() + "\n"
            + "Food: " + getFood() + "\n"
            + "Energy: " + getEnergy() + "\n"
            + "Ore: " + getOre() + "\n"
            + "==================================================";
    }
}
