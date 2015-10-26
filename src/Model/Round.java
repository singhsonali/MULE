package Model;
import View.mapController;
import javafx.collections.ObservableList;

import java.util.Observable;
import java.util.Random;

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
        //calling randEvent here should make it so it never happens during the first round... right?
        //iterate through players?
        //if(p.getLandGrants()==0){randEvent(p)}

    }
    //Checks to make sure the food requirement is met for the round.
    //If not met, returns false.
    public boolean checkRequirement(Food food) {
        return food.getAmount() >= foodRequirement[this.round];
    }

    public int getGamblingBonus() {
        return gamblingBonus[this.round-1];
    }
    public boolean gameOver(){
        return this.round > 12 ;
    }

    public void randEvent(ObservableList<Player> players, Player p){
        int chance;
        Random randGen = new Random();
        chance = randGen.nextInt(100); //calc if random event will occur
        int m;
        //Sets the value of m according to round number
        if(round<4){
            m=25;
        }else if(round>3 && round<8){
            m=50;
        }else if (round>7 && round<12){
            m=75;
        }else{
            m=100;
        }

        //Gets a random event number
        int eventNum;
        if(chance > 27) {
            if (!p.equals(players.get(0))) { //If p is not the lowest scoring player
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
    private void event1(Player p){
        System.out.println("You just received a package from the GT Alumni containing 3 food and 2 energy units.");
        p.setFood(p.getFood()+3);
        p.setEnergy(p.getEnergy()+2);
    }

    private void event2(Player p){
        System.out.println("A wandering Tech student repaid your hospitality by leaving two bars of ore.");
        p.setOre(p.getOre()+2);
    }
    private void event3(Player p, int m){
        System.out.println("The museum bought your antique personal computer for $"+(8*m)+".");
        p.setMoney(p.getMoney()+(8*m));
    }
    private void event4(Player p, int m){
        System.out.println("You found a dead moose rat and sold the hide for $"+(2*m)+".");
        p.setMoney(p.getMoney()+(2*m));
    }
    private void event5(Player p, int m){
        System.out.println("Flying cat-bugs ate the roof off your house. Repairs cost $"+(4*m)+".");
        p.setMoney(p.getMoney()-(4*m));
    }
    private void event6(Player p){
        System.out.println("Mischievous UGA students broke into your storage shed and stole half your food.");
        p.setFood(p.getFood()/2);
    }
    private void event7(Player p, int m){
        System.out.println("Your space gypsy inlaws made a mess of the town. It cost you $" + (6 * m) + " to clean it up.");
        p.setMoney(p.getMoney()-(6*m));
    }
}


