package model;
import javafx.collections.ObservableList;

import java.util.Random;

/**
 * Created by Ashley on 9/30/2015.
 * Keeps track of the Round a player is on.
 */
public class Round implements java.io.Serializable {
    /**
     * Variable that keeps track of food requirement.
     */
    private int[] foodRequirement = {0, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5};
    /**
     * Variable that keeps track of the gambling bonus.
     */
    private int[] gamblingBonus
            = {25, 25, 25, 50, 50, 50, 50, 75, 75, 75, 75, 100};
    /**
     * Variable to keep track of round.
     */
    private int round;
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
    public int getRound() {
        return this.round;
    }
    /**
     * Checks to make sure the food requirement is met for the round.
     * @param food amount of food.
     * @return foodRequirement round.
     *
     */
    public boolean checkRequirement(Food food) {
        return food.getAmount() >= foodRequirement[this.round];
    }
    /**
     * Calculates the gambling bonus for the round.
     * @return gamblingBonus
     *
     */
    public int getGamblingBonus() {
        return gamblingBonus[this.round - 1];
    }
    /**
     * method for the following.
     * @param players calculate random event
     * @param p chance that random event will occur.
     */
    public void randEvent(ObservableList<Player> players, Player p) {
        int chance;
        Random randGen = new Random();
        chance = randGen.nextInt(100); //calc if random event will occur
        int m;
        //Sets the value of m according to round number
        if (round < 4) {
            m = 25;
        } else if (round > 3 && round < 8) {
            m = 50;
        } else if (round > 7 && round < 12) {
            m = 75;
        } else {
            m = 100;
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
     * random event 1
     * @param p player going through random event.
     */
    private void event1(Player p) {
        System.out.println("You just received a package from the GT "
                + "Alumni containing 3 food and 2 energy units.");
        p.setFood(p.getFood() + 3);
        p.setEnergy(p.getEnergy() + 2);
    }
    /**
     * random event 2
     * @param p player going through random event.
     */
    private void event2(Player p) {
        System.out.println("A wandering Tech student repaid your "
                +
                "hospitality by leaving two bars of ore.");
        p.setOre(p.getOre() + 2);
    }
    /**
     * random event 3
     * @param p player going through random event.
     * @param m integer.
     */
    private void event3(Player p, int m) {
        System.out.println("The museum bought your antique personal "
                +
                "computer for $" + (8 * m) + ".");
        p.setMoney(p.getMoney() + (8 * m));
    }
    /**
     * random event 4
     * @param p player going through random event.
     * @param m  integer.
     */
    private void event4(Player p, int m) {
        System.out.println("You found a dead moose rat and sold the "
                +
                "hide for $" + (2 * m) + ".");
        p.setMoney(p.getMoney() + (2 * m));
    }
    /**
     * random event 5
     * @param p player going through random event.
     * @param m integer.
     */
    private void event5(Player p, int m) {
        System.out.println("Flying cat-bugs ate the roof off your house. "
                +
                "Repairs cost $" + (4 * m) + ".");
        p.setMoney(p.getMoney() - (4 * m));
    }
    /**
     * random event 6
     * @param p player going through random event.
     */
    private void event6(Player p) {
        System.out.println("Mischievous UGA students broke into your "
                +
                "storage shed and stole half your food.");
        p.setFood(p.getFood() / 2);
    }
    /**
     * random event 7
     * @param p player going through random event.
     * @param m integer.
     */
    private void event7(Player p, int m) {
        System.out.println("Your space gypsy in-laws made a mess of the town. "
                +
                "It cost you $" + (6 * m) + " to clean it up.");
        p.setMoney(p.getMoney() - (6 * m));
    }
}


