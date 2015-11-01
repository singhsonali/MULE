package model;
/**
 * Created by Ashley on 9/30/2015.
 */
public class Pub {

    public Pub(){

    }

    public int getTimeBonus(int timeLeft) {
        if (timeLeft > 37) {
            return 200;
        } else if (timeLeft > 25) {
            return 150;
        } else if (timeLeft > 12) {
            return 100;
        } else {
            return 50;
        }
    }

    public int calcGamble(int bonus, int timeLeft){
        return bonus + (int)(Math.random()*getTimeBonus(timeLeft));
    }

}
