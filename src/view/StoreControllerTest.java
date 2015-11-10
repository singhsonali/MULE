package view;

import junit.framework.TestCase;
import model.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Melanie Smith on 11/9/2015.
 */
public class StoreControllerTest extends TestCase {

    //create player var
    private Player player;
    private StoreController storeController;
    //original energy value
    private int originalEnergy;


    @Before
    public void setUp() {

        //fix arguments
        player = new Player();
        storeController = new StoreController();
        originalEnergy = player.getEnergy();
        StoreController.setStore();
    }

    @Test
    public void testBuyEnergy() {
        //player has enough money to buy the energy
        player.setEnergy(25);
        storeController.buyEnergy(1);
        assertEquals(player.getEnergy(), 0);
        assertEquals(player.getEnergy(), originalEnergy + 1);
    }

    @Test
    public void testBuyEnergyException() {
        //player does not have enough money to buy the energy
        player.setMoney(24);
        storeController.buyEnergy(1);
        assertEquals(24, player.getMoney());
        assertEquals(originalEnergy, player.getEnergy());
    }

    @Test
    public void testPurchaseExceedsInventory() {
        /*store does not have enough inventory to complete the
         player's purchase*/
        player.setMoney(Integer.MAX_VALUE);
        storeController.buyEnergy(20);
        assertEquals (originalEnergy, player.getEnergy());
    }
}