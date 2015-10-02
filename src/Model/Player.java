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
        this.energy = new Energy();
        this.money = new Money(this);
        this.ownedLand = new ArrayList<Land>();
        this.ore = new Ore();
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

    public void addLand(Land land){
        ownedLand.add(land);
    }
    public void removeLand(Land land){
        if(ownedLand.contains(land)) {
            ownedLand.remove(land);
        }
    }

    public int getScore() {
//        int moneyScore = getMoney();
//        int energyScore = getEnergy() * 25;
//        int foodScore = getFood() * 30;
//        int landScore = getLandGrants() * 500;
//        int oreScore = getOre() * 50;
//
//        int score = moneyScore + energyScore + foodScore + landScore + oreScore;
//        return score;
        //less variables needed and don't want to hardCode in method, declare in a final variable above
        //Can change back if you want either or
        return getMoney() + (getEnergy()*ENERGY_CONST) + (getFood()*FOOD_CONST) +
                (getLandGrants() * LAND_GRAND_CONST) + (getOre() * ORE_CONST);
    }
}