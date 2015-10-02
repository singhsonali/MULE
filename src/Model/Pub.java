package Model;
import java.util.Random;
/**
 * Created by Sonali.
 */
public class Pub {
    
    private static Pub instance = null;
    private Pub(){} 
    
    public static Pub getInstance(){
        if(instance == null){
            instance = new Pub();
        }
        
        return instance;
    }

    /**
     * cGamble method to calculate the amount a player can gamble in terms of time
     * 
     * @param round
     * @param timeLeft
     * @return time lost from player's turn
     */
    public static int cGamble(Round round, long timeLeft){
        Random rand = new Random();
        double secondsLeft = (double) timeLeft/1000;
        
        if (secondsLeft < 0){
            secondsLeft = 0;
        }
        return (int)(round.getGamblingBonus(round) + rand.nextInt((int)(2 * (2.14 * secondsLeft)+1))); // convert seconds to BTU then double

    }

}

