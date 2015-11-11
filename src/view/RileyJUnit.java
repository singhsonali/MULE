package view;

import model.Player;
import model.Store;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.omg.CORBA.TIMEOUT;
import view.StoreController;


/**
 * Created by rileyauten on 11/10/15.
 */
public class RileyJUnit {
    Player firstPlayer = new Player("p1", "Human", "Red");
    Store store = new Store();
    StoreController storeController = new StoreController();

    public void setStore() {
        
    }

    @Test
    public void testSellFood1() {
        firstPlayer.setFood(5);
        firstPlayer.setMoney(0);
        storeController.setCurrentPlayer(firstPlayer);
        storeController.sellFood(5);
        assertEquals(firstPlayer.getFood(), 0);
        assertTrue(firstPlayer.getMoney() == 150);
    }

    @Test
    public void testSellFood2() {
        firstPlayer.setFood(5);
        firstPlayer.setMoney(0);
        storeController.setCurrentPlayer(firstPlayer);
        storeController.sellFood(1);
        assertEquals(firstPlayer.getFood(), 4);
        assertTrue(firstPlayer.getMoney() == 30);
        storeController.sellFood(2);
        assertEquals(firstPlayer.getFood(), 2);
        assertTrue(firstPlayer.getMoney() == 90);
        storeController.buyFood(1);
        assertEquals(firstPlayer.getFood(), 3);
        assertTrue(firstPlayer.getMoney() == 60);
        storeController.sellFood(3);
        assertEquals(firstPlayer.getFood(), 0);
        assertTrue(firstPlayer.getMoney() == 150);
        storeController.sellFood(0);
        assertEquals(firstPlayer.getFood(), 0);
        assertTrue(firstPlayer.getMoney() == 150);
    }

    @Test
    public void testSellFood3() {
        firstPlayer.setFood(5);
        firstPlayer.setMoney(0);
        storeController.setCurrentPlayer(firstPlayer);
        storeController.sellFood(6);
        assertEquals(firstPlayer.getMoney(), 0);
        assertTrue(firstPlayer.getFood() == 5);
    }
}
