package Model;
import java.util.Random;
/**
 * Created by Ashley on 9/20/2015.
 */
public class Pub {

    private Round round;
    private Player currentPlayer;

    public Pub(Player currentPlayer, Round round){
        this.currentPlayer = currentPlayer;
        this.round = round;
    }

    /**
     * calcGamble method to calculate the amount a player can gamble in terms of time
     *
     * @param timeLeft
     * @return time lost from player's turn
     */
    public int calcGamble(long timeLeft){
        Random rand = new Random();
        double secondsLeft = (double) timeLeft/1000;
        
        if (secondsLeft < 0){
            secondsLeft = 0;
        }
        
        //System.out.println("Seconds left = " + secondsLeft);
        return (this.round.getGamblingBonus(this.round) + rand.nextInt((int)(2 * (2.14 * secondsLeft)+1))); // convert seconds to BTU then double

    }

}

