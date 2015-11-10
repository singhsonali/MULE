package model;
import javafx.collections.ObservableList;
import java.util.Random;
/**
 * Round created by Ashley.
 * Keeps track of the Round a player is on.
 */
public class Round implements java.io.Serializable {
    /**
     * Holds the gambling bonus value.
     */
    private static final int GB1 = 25;
    /**
     * Holds the gambling bonus value.
     */
    private static final int GB2 = 50;
    /**
     * Holds gambling bonus value.
     */
    private static final int GB3 = 75;
    /**
     * Holds gambling bonus value.
     */
    private static final int GB4 = 100;
    /**
     * Variable that keeps track of food requirement.
     */
    private final int[]
            foodRequirement
                = {0, 2 + 1, 2 + 1, 2 + 1, 2 + 1, 2 + 2,
                    2 + 2, 2 + 2, 2 + 2, 2 + 2 + 1, 2 + 2 + 1,
                    2 + 2 + 1, 2 + 2 + 1};
    /**
     * Variable that keeps track of the gambling bonus.
     */
    private final int[] gamblingBonus
            = {GB1, GB1, GB1, GB2, GB2, GB2, GB2, GB3, GB3, GB3, GB3, GB4};
    /**
     * Variable to keep track of round.
     */
    private final int round;
    /**
     * Constructor for Round.
     * Init to the default amount.
     */
    public Round() {
        this.round = 1;
    }
    /**
     * Returns the integer representation of players round.
     * @return (int)round
     */
    public final int getRound() {
        return this.round;
    }
    /**
     * Checks to make sure the food requirement is met for the round.
     * @param food amount of food.
     * @return foodRequirement round.
     *
     */
    public final boolean checkRequirement(final Food food) {
        return food.getAmount() >= foodRequirement[this.round];
    }
    /**
     * Calculates the gambling bonus for the round.
     * @return gamblingBonus
     *
     */
    public final int getGamblingBonus() {
        return gamblingBonus[this.round - 1];
    }
    /**
     * method for the following.
     * @param players calculate random event
     * @param p chance that random event will occur.
     */
    public final void randEvent(final ObservableList<Player>
                                        players, final Player p) {
        int chance;
        Random randGen = new Random();
        chance = randGen.nextInt(GB4); //calc if random event will occur
        int m;
        //Sets the value of m according to round number
        if (round < 2 + 2) {
            m = GB1;
        } else if (round > 2 + 1 && round < 8) {
            m = GB2;
        } else if (round > 7 && round < 12) {
            m = GB3;
        } else {
            m = GB4;
        }
        int eventNum;
        if (chance > 27) {
            if (!p.equals(players.get(0))) {
                eventNum = (randGen.nextInt(7)) + 1; //all events
            } else {
                eventNum = (randGen.nextInt(4)) + 1; //includes only good events
            }
            if (eventNum == 1) {
                event1(p);
            } else if (eventNum == 2) {
                event2(p);
            } else if (eventNum == 3) {
                event3(p, m);
            } else if (eventNum == 4) {
                event4(p, m);
            } else if (eventNum == 5) {
                event5(p, m);
            } else if (eventNum == 6) {
                event6(p);
            } else {
                event7(p, m);
            }
        }
    }
    /**
     * Random event 1.
     * @param p player going through random event.
     */
    private void event1(final Player p) {
        System.out.println("You just received a package from the GT "
                + "Alumni containing 3 food and 2 energy units.");
        p.setFood(p.getFood() + (2 + 1));
        p.setEnergy(p.getEnergy() + 2);
    }
    /**
     * Random event 2.
     * @param p player going through random event.
     */
    private void event2(final Player p) {
        System.out.println("A wandering Tech student repaid your "
                +
                "hospitality by leaving two bars of ore.");
        p.setOre(p.getOre() + 2);
    }
    /**
     * Random event 3.
     * @param p player going through random event.
     * @param m integer.
     */
    private void event3(final Player p, final int m) {
        System.out.println("The museum bought your antique personal "
                +
                "computer for $" + (8 * m) + ".");
        p.setMoney(p.getMoney() + (8 * m));
    }
    /**
     * Random event 4.
     * @param p player going through random event.
     * @param m  integer.
     */
    private void event4(final Player p, final int m) {
        System.out.println("You found a dead moose rat and sold the "
                +
                "hide for $" + (2 * m) + ".");
        p.setMoney(p.getMoney() + (2 * m));
    }
    /**
     * Random event 5.
     * @param p player going through random event.
     * @param m integer.
     */
    private void event5(final Player p, final int m) {
        System.out.println("Flying cat-bugs ate the roof off your house. "
                +
                "Repairs cost $" + (4 * m) + ".");
        p.setMoney(p.getMoney() - (4 * m));
    }
    /**
     * Random event 6.
     * @param p player going through random event.
     */
    private void event6(final Player p) {
        System.out.println("Mischievous UGA students broke into your "
                +
                "storage shed and stole half your food.");
        p.setFood(p.getFood() / 2);
    }
    /**
     * Random event 7.
     * @param p player going through random event.
     * @param m integer.
     */
    private void event7(final Player p, final int m) {
        System.out.println("Your space gypsy in-laws made a mess of the town. "
                +
                "It cost you $" + (6 * m) + " to clean it up.");
        p.setMoney(p.getMoney() - (6 * m));
    }
}
