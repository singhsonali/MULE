package view;

import junit.framework.TestCase;
import model.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Ashley on 11/6/2015.
 */
public class StoreControllerTest extends TestCase {

    private Player player;
    private StoreController StoreController = new StoreController();
    //private static int foodPrice = 30;
    private int origFood;

    @Before
    public void setUp() {
        player = new Player();
        StoreController.setCurrentPlayer(player);
        origFood = player.getFood();
        StoreController.setStore();
    }

    @Test
    public void testBuyFood() {
        //Has money to buy food.
        player.setMoney(30);
        StoreController.buyFood(1);
        assertEquals(player.getMoney(), 0);
        assertEquals(player.getFood(), origFood + 1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void buyFoodException() {
        player.setMoney(29);
        StoreController.buyFood(1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void buyOverInventory() {
        player.setMoney(Integer.MAX_VALUE);
        StoreController.buyFood(17);
    }
}