package model;

/**
 * Created by Shannor on 9/20/2015.
 * Class to hold Money item
 * Will be held by Player and the stores
 */
public class Money extends AbstractResources {

    private int amount;

    public Money(){
        this.amount = 0;
    }

    public Money(Player player){
        if(player.getRace().equals("Flapper")) {
            this.amount = 1600;
        }else if(player.getRace().equals("Human")){
            this.amount = 600;
        }else{
            this.amount = 1000;
        }
    }

    @Override
    void setAmount(int i) {
        this.amount = i;
    }

    @Override
    int getAmount() {
        return this.amount;
    }
}
