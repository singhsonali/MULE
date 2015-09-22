package Model;

/**
 * Created by Shannor on 9/18/2015.
 */
public class Map {
    //Default Map Layout
    private Land[][] map = new Land[][]{
            {new Land(), new Land(), new Land(1), new Land(), new Land(true), new Land(), new Land(3), new Land(), new Land()},
            {new Land(), new Land(1), new Land(), new Land(), new Land(true), new Land(), new Land(), new Land(), new Land(3)},
            {new Land(3), new Land(), new Land(), new Land(), new Land(new Town()), new Land(), new Land(), new Land(), new Land(1)},
            {new Land(), new Land(2), new Land(), new Land(), new Land(true), new Land(), new Land(2), new Land(), new Land()},
            {new Land(), new Land(), new Land(2), new Land(), new Land(true), new Land(), new Land(), new Land(), new Land(2)}
    };

    public Land getLand(int i, int j) {
        return map[i][j];
    }

    public void setLand(int i, int j, Land land) {
        map[i][j] = land;
    }
}
