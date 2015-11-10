package model;

/**
 * Created by Ashley on 9/30/2015.
 */
public class Pub {

    /**
     * Variable holding the timeBonus base number.
     */
    private static final int TIME_BONUS = 50;
    /**
     * Variable holding the timeLeft base number.
     */
    private static final int TIME_REMAINING = 12;

    /**
     * Constructor for Pub.
     */
    public Pub() {

    }

    /**
     * Returns the time bonus depending on the time left.
     * @param timeLeft time left in the turn
     * @return the time bonus for the player
     */
    final int getTimeBonus(final int timeLeft) {
        if (timeLeft > TIME_REMAINING * 2 + TIME_REMAINING + 1) { // = 37
            return TIME_BONUS * 4;
        } else if (timeLeft > TIME_REMAINING * 2 + 1) { // = 25
            return TIME_BONUS * 3;
        } else if (timeLeft > TIME_REMAINING) {
            return TIME_BONUS * 2;
        } else {
            return TIME_BONUS;
        }
    }

    /**
     * Calculates and returns the amount won gambling.
     * @param bonus round bonus
     * @param timeLeft time left in the turn
     * @return amount won gambling
     */
    public final int calcGamble(final int bonus, final int timeLeft) {
        return bonus + (int) (Math.random() * getTimeBonus(timeLeft));
    }

}
