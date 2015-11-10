package model;

/**
 * Created by Shannor on 9/18/2015.
 * Map Class to hold multiple Class items Land
 * Land will have its own attributes Map just holds all of them
 * When Map is created it should also make all the Land as well
 *
 */
public class Map implements java.io.Serializable {
    /**
     * 2D array of all possible land.
     */
    private final Land[][] map = new Land[][] {
    //Over 80 chars, but needed for visualization
            {new Land(), new Land(), new Land(1), new Land(), new Land(true), new Land(), new Land(3), new Land(), new Land()},
            {new Land(), new Land(1), new Land(), new Land(), new Land(true), new Land(), new Land(), new Land(), new Land(3)},
            {new Land(3), new Land(), new Land(), new Land(), new Land("Town"), new Land(), new Land(), new Land(), new Land(1)},
            {new Land(), new Land(2), new Land(), new Land(), new Land(true), new Land(), new Land(2), new Land(), new Land()},
            {new Land(), new Land(), new Land(2), new Land(), new Land(true), new Land(), new Land(), new Land(), new Land(2)}
    };

    /**
     * Constructor for map.
     */
    public Map() {
    }

    /**
     * Returns the land at i, j.
     * @param i the row number
     * @param j the column number
     * @return desired land
     */
    public final Land getLand(final int i, final int j) {
        return map[i][j];
    }

    //Method that just returns owned Land
}
