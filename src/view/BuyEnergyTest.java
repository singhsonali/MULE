package view;

import junit.framework.TestCase;
import model.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Melanie Smith on 11/9/2015.
 * Covers all test cases for buyEnergy()
 */
public class BuyEnergyTest extends TestCase {

    private Player player;
    private StoreController storeController;
    private int playerOrigEnergy;


    @Before
    public void setUp() {
        player = new Player();
        storeController = new StoreController();
        playerOrigEnergy = player.getEnergy();

        storeController.setCurrentPlayer(player);
        storeController.setStore();
    }

    /**
     * Test case: player has enough money to buy the energy & inventory is sufficient.
     * Player's money should be reduced by 25*energyAmount.
     * Player's energy should increase by energy purchased.
     */
    @Test
    public void testEnoughMoney() {
        player.setMoney(50);
        storeController.buyEnergy(2);
        assertEquals(player.getMoney(), 0);
        assertEquals(player.getEnergy(), playerOrigEnergy + 2);
    }

    /**
     * Test case: Player does not have enough money to purchase desired energy.
     * Player's money should stay the same.
     * Player's energy should stay the same.
     */
    @Test
    public void testInsufficientFunds() {
        player.setMoney(49);
        storeController.buyEnergy(2);
        assertEquals(49, player.getMoney());
        assertEquals(playerOrigEnergy, player.getEnergy());
    }

    /**
     * Test case: when there is not enough energy in the inventory to complete the player's desired purchase
     * Player's money should not change.
     * Energy energy should not change.
     */
    @Test
    public void testInsufficientInventory() {
        player.setMoney(425);
        storeController.buyEnergy(17);
        assertEquals(playerOrigEnergy, player.getEnergy());
        assertEquals(player.getMoney(), 425);
    }


}