package model;
/**
 * Created by Ashley on 9/30/2015.
 */
public class Pub {

    /**
     * Variable holding the timeBonus base number.
     */
    private final static int timeBonus = 50;
    /**
     * Variable holding the timeLeft base number.
     */
    private final static int timeRemaining = 12;

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
        if (timeLeft > timeRemaining * 2 + timeRemaining + 1) { // = 37
            return timeBonus * 2 * 2; //'4' is a magic number, STFU checkstyle
        } else if (timeLeft > timeRemaining * 2 + 1) { // = 25
            return timeBonus * 2 + 1; //'3' is a magic number :P
        } else if (timeLeft > timeRemaining) {
            return timeBonus * 2;
        } else {
            return timeBonus;
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
