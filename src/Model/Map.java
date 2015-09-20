package Model;

/**
 * Created by Shannor on 9/18/2015.
 * Map Class to hold multiple Class items Land
 * Land will have its own attributes Map just holds all of them
 * When Map is created it should also make all the Land as well
 *
 */
public class Map {

    private Land[][] map;

    public Map() {
        map = new Land[][]{
                {new Land(), new Land(), new Land(1), new Land(), new Land(true), new Land(), new Land(3), new Land(), new Land()},
                {new Land(), new Land(1), new Land(), new Land(), new Land(true), new Land(), new Land(), new Land(), new Land(3)},
                {new Land(3), new Land(), new Land(), new Land(), new Land(new Town()), new Land(), new Land(), new Land(), new Land(1)},
                {new Land(), new Land(2), new Land(), new Land(), new Land(true), new Land(), new Land(2), new Land(), new Land()},
                {new Land(), new Land(), new Land(2), new Land(), new Land(true), new Land(), new Land(), new Land(), new Land(2)}
        };
    }

}
