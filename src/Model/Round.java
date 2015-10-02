package Model;

/**
 * Created by Ashley on 9/30/2015.
 */
public class Round {

    private int[] foodRequirement = {0, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5}; //Starts at index 1
    private int[] gamblingBonus = {25, 25, 25, 50, 50, 50, 50, 75, 75, 75, 75, 100};
    private int round;

    public Round() {
        this.round = 1;
    }

    public int getRound() {
        return this.round;
    }
    public void nextRound() {
        this.round++;
    }
    //Checks to make sure the food requirement is met for the round.
    //If not met, returns false.
    public boolean checkRequirement(Food food) {
        return food.getAmount() >= foodRequirement[this.round];
    }

    public int getGamblingBonus(Round round) {
        return gamblingBonus[roundNumber];
    }
}
