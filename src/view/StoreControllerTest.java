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

    @Test
    public void testBuyFoodException() {
        //Does not have enough money to buy food
        player.setMoney(29);
        StoreController.buyFood(1);
        assertEquals(29, player.getMoney());
        assertEquals(origFood, player.getFood());
    }

    @Test
    public void testBuyOverInventory() {
        //Store does not have enough inventory to sell amount
        player.setMoney(Integer.MAX_VALUE);
        StoreController.buyFood(17);
        assertEquals(origFood, player.getFood());
    }
}