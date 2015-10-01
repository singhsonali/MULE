package Model;

/**
 * Created by Ashley on 9/30/2015.
 */
public class Round {

    private int[] foodRequirement = {0, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5}; //Starts at index 1
    private int round = 1;

    public Round() {

    }

    public int getRound() {
        return round;
    }
    public void nextRound() {
        round++;
    }
    //Checks to make sure the food requirement is met for the round.
    //If not met, returns false.
    public boolean checkRequirement(Food food) {
        return food.getAmount() >= foodRequirement[round];
    }

}
