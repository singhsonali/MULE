package view;

import main.Main;
import model.Player;
import model.Store;
import model.GameTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Created by Ashley Noll on 10/1/2015.
 */
public class StoreController {

    /**
     * the player whose turn it is.
     */
    private Player currentPlayer;
    /**
     * Instance of Main.
     */
    private Main main;
    /**
     * Instance of Store.
     */
    private Store store;
    /**
     * The stage that holds the first scene.
     */
    private Stage primaryStage;
    /**
     * The current timer.
     */
    private GameTimer currentTimer;
    /**
     * The current scene. Always Store.
     */
    private Scene currentScene; //Store
    /**
     * Instance of TownMapController.
     */
    private TownMapController controller;
    /**
     * Price of an Ore Mule in the store.
     */
    private final static int oreMulePrice = 175;

    /**
     * Price of an Energy Mule in the store.
     */
    private final static int energyMulePrice = 150;

    /**
     * Price of Food Mule in the store.
     */
    private final static int foodMulePrice = 125;

    /**
     * Price of food in the store.
     */
    private final static int foodPrice = 30;

    /**
     * Price of energy in the store.
     */
    private final static int energyPrice = 25;

    /**
     * Price of ore in the store.
     */
    private final static int orePrice = 50;

    /**
     * Empty string for clearing purposes.
     */
    private final static String empty = "";

    /**
     * A TextField in store.
     */
    @FXML
    private TextField txtAmount;
    /**
     * Return button in store. Return to town map.
     */
    @FXML
    private Button btnReturn;
    /**
     * Buy button in store. For resources.
     */
    @FXML
    private Button btnBuy;
    /**
     * Sell button in store. For resources.
     */
    @FXML
    private Button btnSell;
    /**
     * Choice box to choose resource.
     */
    @FXML
    private ChoiceBox cbItem;
    /**
     * Choice box to choose mule type.
     */
    @FXML
    private ChoiceBox muleBox;
    /**
     * Label mule type.
     */
    @FXML
    private Label lblMuleType;
    /**
     * Label quantity.
     */
    @FXML
    private Label lblQuantity;

    /**
     * Label the energy price.
     */
    @FXML
    private Label lblPriceEnergy;
    /**
     * Label the ore price.
     */
    @FXML
    private Label lblPriceOre;
    /**
     * Label the food price.
     */
    @FXML
    private Label lblPriceFood;
    /**
     * Label the energyMule price.
     */
    @FXML
    private Label lblPriceMEnergy;
    /**
     * Label the oreMule price.
     */
    @FXML
    private Label lblPriceMOre;
    /**
     * Label the foodMule price.
     */
    @FXML
    private Label lblPriceMFood;
    /**
     * Label the amount of energy in stock.
     */
    @FXML
    private Label lblStockEnergy;
    /**
     * Label the amount of ore in stock.
     */
    @FXML
    private Label lblStockOre;
    /**
     * Label the amount of food in stock.
     */
    @FXML
    private Label lblStockFood;
    /**
     * Label the amount of energyMule in stock.
     */
    @FXML
    private Label lblStockMEnergy;
    /**
     * Label the amount of oreMule in stock.
     */
    @FXML
    private Label lblStockMOre;
    /**
     * Label the amount of foodMule in stock.
     */
    @FXML
    private Label lblStockMFood;
    /**
     * Confirm message for purchase/sale.
     */
    @FXML
    private Label lblConfirmMsg;

    /**
     * Empty constructor for StoreController.
     */
    public StoreController() {

    }

    /**
     * Initializes the screen.
     */
    @FXML
    private void initialize() {
        store = new Store();
        setInterfaceInvis(false);
        updateStoreInventoryLabels();

        cbItem.getItems().addAll(
                "Food",
                "Energy",
                "Ore",
                "Mule"
        );

        muleBox.getItems().addAll(
                "Energy Mule",
                "Food Mule",
                "Ore Mule"
        );

        cbItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                String itemChoice = (String) cbItem.getValue();
                if (itemChoice.equals("Mule")) {
                    setInterfaceInvis(true);
                } else {
                    setInterfaceInvis(false);
                }
            }
        });

        btnBuy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                //Return number of players to main
                String itemChoice = (String) cbItem.getValue();
                if (itemChoice.equals("Food")) {
                    buyFood(getNumChoice());
                } else if (itemChoice.equals("Energy")) {
                    buyEnergy(getNumChoice());
                } else if (itemChoice.equals("Ore")) {
                    buyOre(getNumChoice());
                } else if (itemChoice.equals("Mule")) {
                    if (currentPlayer.hasLand()) {
                        String muleChoice = (String) muleBox.getValue();
                        if (muleChoice.equals("Energy Mule")) {
                            buyEnergyMule();
                        } else if (muleChoice.equals("Food Mule")) {
                            buyFoodMule();
                        } else if (muleChoice.equals("Ore Mule")) {
                            buyOreMule();
                        }
                    } else {
                        lblConfirmMsg.setText(
                                "You do not have any"
                                        + " land to place a MULE on.");
                    }
                }
            }
        });

        btnSell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                String itemChoice = (String) cbItem.getValue();
                if (itemChoice.equals("Food")) {
                    sellFood(getNumChoice());
                } else if (itemChoice.equals("Energy")) {
                    sellEnergy(getNumChoice());
                } else if (itemChoice.equals("Ore")) {
                    sellOre(getNumChoice());
                }
            }
        });

        btnReturn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                leaveStore();
            }
        });
    }

    /**
     * Sets the interface invisible if true.
     *
     * @param bool true or false?
     */
    public final void setInterfaceInvis(final boolean bool) {
        muleBox.visibleProperty().setValue(bool);
        lblMuleType.setVisible(bool);
        btnSell.visibleProperty().setValue(!bool);
        txtAmount.visibleProperty().setValue(!bool);
        lblQuantity.setVisible(!bool);
    }

    /**
     * Closes the scene and opens a new stage/scene.
     */
    public final void leaveStore() {
        //Close the scene
        main.setReturningFromStore(true);
        main.setReturnTimer(currentTimer);
        main.showTownScreen();

        /*primaryStage.setScene(prevScene);
        primaryStage.show(); */
        controller.updatePlayerInfoLabels();

        Stage stage = new Stage();
        stage.setScene(currentScene);
        stage.close();

        //controller.updateCurrent();
    }

    /**
     * On mouseClick event to buy oreMule.
     */
    public final void buyOreMule() {
        if (currentPlayer.getMoney() < oreMulePrice) {
            lblConfirmMsg.setText("Insufficient funds: You can never be in debt");
            //throw new IndexOutOfBoundsException(
            // "Insufficient funds: Cannot be in debt");
        } else if (store.getMuleAmount() <= 0) {
            lblConfirmMsg.setText(
                    "There are no longer any ore MULEs for purchase");
            //throw new IndexOutOfBoundsException(
            // "There are no longer any ore MULEs for purchase");
        } else if (currentPlayer.hasMule()) {
            lblConfirmMsg.setText("Cannot purchase MULE"
                    + " if you have MULE that haven't been placed");
            //throw new IllegalArgumentException(
            // "Cannot purchase MULE if u have MULE that haven't been placed");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - oreMulePrice);
            currentPlayer.setOreMule(currentPlayer.getOreMule() + 1);
            currentPlayer.setHoldingMule("oreMule");
            store.deleteMule();
            goToMap();
        }
    }

    /**
     * On mouseClick even to buy energyMule.
     */
    public final void buyEnergyMule() {
        if (currentPlayer.getMoney() < energyMulePrice) {
            lblConfirmMsg.setText("Insufficient funds: Can't be in debt");
            //throw new IndexOutOfBoundsException(
            // "Insufficient funds: Cannot be in debt");
        } else if (store.getMuleAmount() <= 0) {
            lblConfirmMsg.setText(
                    "There are no longer any energy MULEs for purchase");
            //throw new IndexOutOfBoundsException(
            // "There are no longer any energy MULEs for purchase");
        } else if (currentPlayer.hasMule()) {
            lblConfirmMsg.setText("Cannot purchase MULE"
                    + " if you have MULE that haven't been placed");
            //throw new IllegalArgumentException("Cannot purchase
            // MULE if you have MULE that haven't been placed");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - energyMulePrice);
            currentPlayer.setEnergyMule(currentPlayer.getEnergyMule() + 1);
            currentPlayer.setHoldingMule("energyMule");
            store.deleteMule();
            goToMap();
        }
    }

    /**
     * On mouseClick even to buy foodMule.
     */
    public final void buyFoodMule() {
        if (currentPlayer.getMoney() < foodMulePrice) {
            lblConfirmMsg.setText("Insufficient funds: you can cannot be in debt");
            //throw new IndexOutOfBoundsException(
            // "Insufficient funds: Cannot be in debt");
        } else if (store.getMuleAmount() <= 0) {
            lblConfirmMsg.setText(
                    "There are no longer any food MULEs for purchase");
            //throw new IndexOutOfBoundsException(
            // "There are no longer any food MULEs for purchase");
        } else if (currentPlayer.hasMule()) {
            lblConfirmMsg.setText("Cannot purchase MULE"
                    + " if you have MULE that haven't been placed");
            //throw new IllegalArgumentException("Cannot purchase MULE
            // if you have MULE that haven't been placed");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - foodMulePrice);
            currentPlayer.setFoodMule(currentPlayer.getFoodMule() + 1);
            currentPlayer.setHoldingMule("foodMule");
            store.deleteMule();
            goToMap();
        }
    }

    /**
     * On mouseClick even to buy food.
     *
     * @param amnt Amount of food purchased.
     */
    public final void buyFood(final int amnt) {
        if (currentPlayer.getMoney() < (foodPrice * amnt)) {
            //lblConfirmMsg.setText("Insufficient funds: Player cannot be in debt");
            throw new IndexOutOfBoundsException (
             "Insufficient funds: Cannot be in debt");
        } else if (store.getFood() < amnt) {
            /*lblConfirmMsg.setText(
                    "There is less than " + amnt + " food left in the store."
                            + " You can purchase up to "
                            + store.getFood() + " food."); */
            throw new IndexOutOfBoundsException (
                    "Insufficient inventory: Cannot sell more than available");
        } else {
            currentPlayer.setMoney(
                    currentPlayer.getMoney() - (foodPrice * amnt));
            currentPlayer.setFood(currentPlayer.getFood() + amnt);
            store.setFood(store.getFood() - amnt);
            //lblConfirmMsg.setText("You have purchased " + amnt + " food.");
            //updateStoreInventoryLabels();
            //cbItem.valueProperty().setValue(empty);
            //txtAmount.setText(empty);
        }
    }

    /**
     * On mouseClick even to buy energy.
     *
     * @param amnt Amount of energy purchased.
     */
    public final void buyEnergy(final int amnt) {
        if (currentPlayer.getMoney() < (energyPrice * amnt)) {
            lblConfirmMsg.setText(
                    "Insufficient funds: In debt? Illegal."
                            + " Nope. Can't do it. Bad kitty.");
            //throw new IndexOutOfBoundsException(
            // "Insufficient funds: Cannot be in debt");
        } else if (store.getEnergy() < amnt) {
            lblConfirmMsg.setText("There is less than " + amnt
                    + " energy left in the store. You can purchase up to "
                    + store.getEnergy() + " energy.");
        } else {
            currentPlayer.setMoney(
                    currentPlayer.getMoney() - (energyPrice * amnt));
            currentPlayer.setEnergy(currentPlayer.getEnergy() + amnt);
            store.setEnergy(store.getEnergy() - amnt);
            lblConfirmMsg.setText("You have purchased " + amnt + " energy.");
            updateStoreInventoryLabels();
            //cbItem.valueProperty().setValue(empty);
            txtAmount.setText(empty);
        }
    }

    /**
     * On mouseClick even to buy ore.
     *
     * @param amnt Amount of ore purchased.
     */
    public final void buyOre(final int amnt) {
        if (currentPlayer.getMoney() < (orePrice * amnt)) {
            lblConfirmMsg.setText("Insufficient funds: :( ");
            //throw new IndexOutOfBoundsException(
            // "Insufficient funds: Cannot be in debt");
        } else if (store.getEnergy() < amnt) {
            lblConfirmMsg.setText("There is less than " + amnt
                    + " ore left in the store. You can purchase up to "
                    + store.getOre() + " ore.");
        } else {
            currentPlayer.setMoney(
                    currentPlayer.getMoney() - (orePrice * amnt));
            currentPlayer.setOre(currentPlayer.getOre() + amnt);
            store.setOre(store.getOre() - amnt);
            lblConfirmMsg.setText("You have purchased " + amnt + " ore.");
            updateStoreInventoryLabels();
            //cbItem.valueProperty().setValue(empty);
            txtAmount.setText(empty);
        }
    }


    /**
     * On mouseClick even to sell food.
     *
     * @param amnt Amount of food sold.
     */
    public final void sellFood(final int amnt) {
        if (currentPlayer.getFood() < amnt) {
            lblConfirmMsg.setText("Cannot sell more food than you have");
        } else {
            currentPlayer.setMoney(
                    currentPlayer.getMoney() + (amnt * foodPrice));
            currentPlayer.setFood(currentPlayer.getFood() - amnt);
            store.setFood(store.getFood() + amnt);
            lblConfirmMsg.setText("You have sold " + amnt + " food.");
            updateStoreInventoryLabels();
            //cbItem.valueProperty().setValue(empty);
            txtAmount.setText(empty);
        }
    }

    /**
     * On mouseClick even to sell ore.
     *
     * @param amnt Amount of ore sold.
     */
    public final void sellOre(final int amnt) {
        if (currentPlayer.getOre() < amnt) {
            lblConfirmMsg.setText("Cannot sell more ore than you have");
        } else {
            currentPlayer.setMoney(
                    currentPlayer.getMoney() + (amnt * orePrice));
            currentPlayer.setOre(currentPlayer.getOre() - amnt);
            store.setOre(store.getOre() + amnt);
            lblConfirmMsg.setText("You have sold " + amnt + " ore.");
            updateStoreInventoryLabels();
            txtAmount.setText(empty);
        }
    }

    /**
     * On mouseClick even to sell energy.
     *
     * @param amnt Amount of energy sold.
     */
    public final void sellEnergy(final int amnt) {
        if (currentPlayer.getEnergy() < amnt) {
            lblConfirmMsg.setText("Cannot sell more energy than you have");
        } else {
            currentPlayer.setMoney(
                    currentPlayer.getMoney() + (amnt * energyPrice));
            currentPlayer.setEnergy(currentPlayer.getEnergy() - amnt);
            store.setEnergy(store.getEnergy() + amnt);
            lblConfirmMsg.setText("You have sold " + amnt + " energy.");
            updateStoreInventoryLabels();
            txtAmount.setText(empty);
        }
    }

    /**
     * Getter for the following.
     *
     * @return int: amount a player wishes to buy/sell.
     */
    @FXML
    public final int getNumChoice() {
        return Integer.parseInt(txtAmount.getText());
    }

    /**
     * Getter for the following.
     *
     * @param stage gets the primary stage.
     */
    public final void getStage(final Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Setter for the following.
     *
     * @param tMController sets the controller of TownMapController type.
     */
    public final void setController(final TownMapController tMController) {
        this.controller = tMController;
    }

    /**
     * Getter for the primary stage.
     *
     * @param stage Instance of Stage.
     */
    public final void getPrimaryStage(final Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Getter for the current player.
     *
     * @param player the current player.
     */
    public final void setCurrentPlayer(final Player player) {
        this.currentPlayer = player;
    }

    /**
     * Setter to set the current scene.
     *
     * @param scene An instance of Scene.
     */
    public final void setCurrentScene(final Scene scene) {
        this.currentScene = scene;
    }

    /**
     * A setter to set the game timer.
     *
     * @param timer An instance of GameTimer.
     */
    public final void setCurrentTimer(final GameTimer timer) {
        this.currentTimer = timer;
    }

    /**
     * Sets the time limit.
     */
    public final void setTimer() {
        //currentTimer.setLabel(lblPubTimer);
        currentTimer.getTimeline().setOnFinished(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent event) {
                        leaveStore();
                        controller.updateCurrent();
                    }
                });
    }

    /**
     * Action event to go to the map.
     */
    public final void goToMap() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(
                    "../view/MapScene.fxml"));
            AnchorPane mapScreen = loader.load();

            Scene scene = new Scene(mapScreen);
            MapController mController = loader.getController();

            mController.setPrevScene(currentScene); // Scene is Town
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();
            //Passes current player to map
            mController.setCurrentPlayer(currentPlayer);
            mController.setMulePhaseLabels();
            mController.setStoreController(this);
            //Current Stage everything is displayed on
            mController.getStage(primaryStage);
            mController.setCurrentScene(currentScene);
            mController.setCurrentTimer(currentTimer);
            //mController.setTimer();
            //mController.setController(this);
            loader.setController(mController);
            mController.getMap(main.getGameMap());
            mController.connectMapWithPanes();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the main app.
     *
     * @param mainApp An instance of Main.
     */
    public final void setMainApp(final Main mainApp) {
        this.main = mainApp;
    }

    /**
     * Sets the store.
     */
    public final void setStore() {
        store = new Store();
    }

    /**
     * Updates the store inventory.
     * How much mule (of each type) and how much resources (of each type).
     */
    public final void updateStoreInventoryLabels() {
        lblPriceEnergy.setText("25");
        lblPriceFood.setText("30");
        lblPriceOre.setText("50");
        lblPriceMEnergy.setText("130");
        lblPriceMFood.setText("125");
        lblPriceMOre.setText("150");
        lblStockEnergy.setText(empty + store.getEnergy());
        lblStockFood.setText(empty + store.getFood());
        lblStockOre.setText(empty + store.getOre());
        lblStockMEnergy.setText(empty + store.getMuleAmount());
        lblStockMFood.setText(empty + store.getMuleAmount());
        lblStockMOre.setText(empty + store.getMuleAmount());
    }
}
