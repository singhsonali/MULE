package Model;

import java.util.ArrayList;

/**
 * Created by Shannor on 9/18/2015.
 * Map Class to hold multiple Class items Land
 * Land will have its own attributes Map just holds all of them
 * When Map is created it should also make all the Land as well
 *
 */
public class Map {
    //Store owned land current so cna updated MapScene.fxml colors
    private ArrayList<Land> ownedLand = new ArrayList<Land>();
    //2D array of all possible land
    private Land[][] map = new Land[][]{
            {new Land(), new Land(), new Land(1), new Land(), new Land(true), new Land(), new Land(3), new Land(), new Land()},
            {new Land(), new Land(1), new Land(), new Land(), new Land(true), new Land(), new Land(), new Land(), new Land(3)},
            {new Land(3), new Land(), new Land(), new Land(), new Land(new Town()), new Land(), new Land(), new Land(), new Land(1)},
            {new Land(), new Land(2), new Land(), new Land(), new Land(true), new Land(), new Land(2), new Land(), new Land()},
            {new Land(), new Land(), new Land(2), new Land(), new Land(true), new Land(), new Land(), new Land(), new Land(2)}
    };
    //Constructor
    public Map(){
        ArrayList<Land> ownedLand = new ArrayList<Land>();
        Land[][] map;
    }

    public int getRow(){return 9;}
    public int getColumn() {return 5;}

    public Land getLand(int i, int j) {
        return map[i][j];
    }

    public void setLand(int i, int j, Land land) {
        map[i][j] = land;
    }

    //Method that just returns owned Land
}
