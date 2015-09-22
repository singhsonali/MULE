package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
    //Resources Object
    //Turn will be given to the player
    private int turn;
    private int playerNum;
    //The number the player is when created, Class variable
    private static int playerNumber = 0;
    private boolean[][] owned = new boolean[5][9];

    public Player(){
        this.name = "temp";
        this.race = "temp";
        this.color = "temp";
        this.playerNum = ++playerNumber;
        for (int i = 0; i < owned.length; i++) {
            for (int j = 0; j < owned[0].length; j++) {
                owned[i][j] = false;
            }
        }
    }
    public Player(String name, String race, String color){
        this.name = name;
        this.race = race;
        this.color = color;
        this.playerNum =  ++playerNumber;
        for (int i = 0; i < owned.length; i++) {
            for (int j = 0; j < owned[0].length; j++) {
                owned[i][j] = false;
            }
        }
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
    public boolean[][] getOwned() {
       return owned;
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
    public void setOwned(int i, int j) {
        this.owned[i][j] = true;
    }
}
