package Model;
import java.util.Random;
/**
 * Created by Ashley on 9/20/2015.
 */
public class Pub {
    
    private static Pub instance = null;
    private Pub(){} // Singleton -> private constructor
    
    public static Pub getInstance(){
        if(instance == null){
            instance = new Pub();
        }
        
        return instance;
    }

    /**
     * calcGamble method to calculate the amount a player can gamble in terms of time
     * 
     * @param round
     * @param timeLeft
     * @return time lost from player's turn
     */
    public static int calcGamble(Round round, long timeLeft){
        Random rand = new Random();
        double secondsLeft = (double) timeLeft/1000;
        
        if (secondsLeft < 0){
            secondsLeft = 0;
        }
        
        //System.out.println("Seconds left = " + secondsLeft);
        return (int)(round.getGamblingBonus() + rand.nextInt((int)(2 * (2.14 * secondsLeft)+1))); // convert seconds to BTU then double
    }

}

}
