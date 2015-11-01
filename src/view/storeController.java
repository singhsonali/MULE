package view;
import Main.Main;
import model.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Created by Ashley on 9/25/2015.
 */
public class storeController {

    private Pane currentPane;
    private Player currentPlayer;
    private Main main;
    private Store store;
    private Stage primaryStage;
    private Scene prevScene; //Town
    private GameTimer currentTimer; //Current time
    private Scene currentScene; //Store
    private townMapController controller;

    @FXML
    public TextField txtAmount;
    @FXML
    private Button btnReturn;
    @FXML
    private Button btnBuy;
    @FXML
    private Button btnSell;
    @FXML
    private ChoiceBox cbItem;
    @FXML
    private ChoiceBox muleBox;
    @FXML
    private Label lblMuleType;
    @FXML
    private Label lblQuantity;

    @FXML
    private Label lblPriceEnergy;
    @FXML
    private Label lblPriceOre;
    @FXML
    private Label lblPriceFood;
    @FXML
    private Label lblPriceMEnergy;
    @FXML
    private Label lblPriceMOre;
    @FXML
    private Label lblPriceMFood;
    @FXML
    private Label lblStockEnergy;
    @FXML
    private Label lblStockOre;
    @FXML
    private Label lblStockFood;
    @FXML
    private Label lblStockMEnergy;
    @FXML
    private Label lblStockMOre;
    @FXML
    private Label lblStockMFood;
    @FXML
    private Label lblConfirmMsg;

    public storeController() {

    }

    @FXML
    private void initialize(){
        currentPane = null;
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
            public void handle(ActionEvent event) {
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
            public void handle(ActionEvent event) {
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
                        System.out.println(currentPlayer + " : " + currentPlayer.getEnergyMule());
                    } else {
                        lblConfirmMsg.setText("You do not have any land to place a MULE on.");
                    }
                }
            }
        });

        btnSell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
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
            public void handle(ActionEvent event) {
                leaveStore();
            }
        });
    }

    public void setInterfaceInvis(boolean bool){
        muleBox.visibleProperty().setValue(bool);
        lblMuleType.setVisible(bool);
        btnSell.visibleProperty().setValue(!bool);
        txtAmount.visibleProperty().setValue(!bool);
        lblQuantity.setVisible(!bool);
    }

    public void leaveStore(){
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

    // onMouseClick event
    public void buyOreMule() {
        if (currentPlayer.getMoney() < 175) {
            lblConfirmMsg.setText("Insufficient funds: Cannot be in debt");
            //throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        } else if (store.getMuleAmount() <= 0) {
            lblConfirmMsg.setText("There are no longer any ore MULEs for purchase");
            //throw new IndexOutOfBoundsException("There are no longer any ore MULEs for purchase");
        } else if (currentPlayer.hasMule()) {
            lblConfirmMsg.setText("Cannot purchase MULE if you have MULE that haven't been placed");
            //throw new IllegalArgumentException("Cannot purchase MULE if you have MULE that haven't been placed");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - 175);
            currentPlayer.setOreMule(currentPlayer.getOreMule() + 1);
            currentPlayer.setHoldingMule("oreMule");
            store.deleteMule();
            goToMap();
        }
    }

    // onMouseClick event
    public void buyEnergyMule() {
        if (currentPlayer.getMoney() < 150) {
            lblConfirmMsg.setText("Insufficient funds: Cannot be in debt");
            //throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        } else if (store.getMuleAmount() <= 0) {
            lblConfirmMsg.setText("There are no longer any energy MULEs for purchase");
            //throw new IndexOutOfBoundsException("There are no longer any energy MULEs for purchase");
        } else if (currentPlayer.hasMule()) {
            lblConfirmMsg.setText("Cannot purchase MULE if you have MULE that haven't been placed");
            //throw new IllegalArgumentException("Cannot purchase MULE if you have MULE that haven't been placed");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - 150);
            currentPlayer.setEnergyMule(currentPlayer.getEnergyMule() + 1);
            currentPlayer.setHoldingMule("energyMule");
            store.deleteMule();
            goToMap();
        }
    }

    //onMouseClick event
    public void buyFoodMule() {
        if (currentPlayer.getMoney() < 125) {
            lblConfirmMsg.setText("Insufficient funds: Cannot be in debt");
            //throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        } else if (store.getMuleAmount() <= 0) {
            lblConfirmMsg.setText("There are no longer any food MULEs for purchase");
            //throw new IndexOutOfBoundsException("There are no longer any food MULEs for purchase");
        } else if (currentPlayer.hasMule()) {
            lblConfirmMsg.setText("Cannot purchase MULE if you have MULE that haven't been placed");
            //throw new IllegalArgumentException("Cannot purchase MULE if you have MULE that haven't been placed");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - 125);
            currentPlayer.setFoodMule(currentPlayer.getFoodMule() + 1);
            currentPlayer.setHoldingMule("foodMule");
            store.deleteMule();
            goToMap();
        }
    }

    //onMouseClick event
    public void buyFood(int amnt) {
        if (currentPlayer.getMoney() < (30 * amnt)) {
            lblConfirmMsg.setText("Insufficient funds: Cannot be in debt");
            //throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        } else if (store.getEnergy() < amnt) {
            lblConfirmMsg.setText("There is less than " + amnt + " food left in the store. You can purchase up to " + store.getFood() + " food.");
            //throw new IndexOutOfBoundsException("There is less than " + amnt + " food left in the store. You can purchase up to " + store.getFood() + " food.");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - (30 * amnt));
            currentPlayer.setFood(currentPlayer.getFood() + amnt);
            store.setFood(store.getFood() - amnt);
            lblConfirmMsg.setText("You have purchased " + amnt + " food.");
            updateStoreInventoryLabels();
            //cbItem.valueProperty().setValue("");
            txtAmount.setText("");
        }
    }

    //onMouseClick event
    public void buyEnergy(int amnt) {
        if (currentPlayer.getMoney() < (25 * amnt)) {
            lblConfirmMsg.setText("Insufficient funds: Cannot be in debt");
            //throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        } else if (store.getEnergy() < amnt) {
            lblConfirmMsg.setText("There is less than " + amnt + " energy left in the store. You can purchase up to " + store.getEnergy() + " energy.");
            //throw new IndexOutOfBoundsException("There is less than " + amnt + " energy left in the store. You can purchase up to " + store.getEnergy() + " energy.");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - (25 * amnt));
            currentPlayer.setEnergy(currentPlayer.getEnergy() + amnt);
            store.setEnergy(store.getEnergy() - amnt);
            lblConfirmMsg.setText("You have purchased " + amnt + " energy.");
            updateStoreInventoryLabels();
            //cbItem.valueProperty().setValue("");
            txtAmount.setText("");
        }
    }

    //onMouseClick event
    public void buyOre(int amnt) {
        if (currentPlayer.getMoney() < (50 * amnt)) {
            lblConfirmMsg.setText("Insufficient funds: Cannot be in debt");
            //throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        } else if (store.getEnergy() < amnt) {
            lblConfirmMsg.setText("There is less than " + amnt + " ore left in the store. You can purchase up to " + store.getOre() + " ore.");
            //throw new IndexOutOfBoundsException("There is less than " + amnt + " ore left in the store. You can purchase up to " + store.getOre() + " ore.");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - (50 * amnt));
            currentPlayer.setOre(currentPlayer.getOre() + amnt);
            store.setOre(store.getOre() - amnt);
            lblConfirmMsg.setText("You have purchased " + amnt + " ore.");
            updateStoreInventoryLabels();
            //cbItem.valueProperty().setValue("");
            txtAmount.setText("");
        }
    }

    //onMouseClick event

    public void sellFood(int amnt) {
        if (currentPlayer.getFood() < amnt) {
            lblConfirmMsg.setText("Cannot sell more food than you have");
            //throw new IndexOutOfBoundsException("Cannot sell more food than you have");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() + (amnt * 30));
            currentPlayer.setFood(currentPlayer.getFood() - amnt);
            store.setFood(store.getFood() + amnt);
            lblConfirmMsg.setText("You have sold " + amnt + " food.");
            updateStoreInventoryLabels();
            //cbItem.valueProperty().setValue("");
            txtAmount.setText("");
        }
    }

    public void sellOre(int amnt) {
        if (currentPlayer.getOre() < amnt) {
            lblConfirmMsg.setText("Cannot sell more ore than you have");
            //throw new IndexOutOfBoundsException("Cannot sell more ore than you have");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() + (amnt * 50));
            currentPlayer.setOre(currentPlayer.getOre() - amnt);
            store.setOre(store.getOre() + amnt);
            lblConfirmMsg.setText("You have sold " + amnt + " ore.");
            updateStoreInventoryLabels();
            //cbItem.valueProperty().setValue("");
            txtAmount.setText("");
        }
    }

    public void sellEnergy(int amnt) {
        if (currentPlayer.getEnergy() < amnt) {
            lblConfirmMsg.setText("Cannot sell more energy than you have");
            //throw new IndexOutOfBoundsException("Cannot sell more energy than you have");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() + (amnt * 25));
            currentPlayer.setEnergy(currentPlayer.getEnergy() - amnt);
            store.setEnergy(store.getEnergy() + amnt);
            lblConfirmMsg.setText("You have sold " + amnt + " energy.");
            updateStoreInventoryLabels();
            //cbItem.valueProperty().setValue("");
            txtAmount.setText("");
        }
    }

    @FXML
    public int getNumChoice(){
        return Integer.parseInt(txtAmount.getText());
    }

    public void getStage(Stage stage){
        this.primaryStage  = stage;
    }
    public void setController(townMapController controller){
        this.controller = controller;
    }
    public void getPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }
    public void getCurrentPlayer(Player player){
        this.currentPlayer = player;
    }
    public void setPrevScene(Scene scene){
        this.prevScene = scene;
        //controller.updateCurrent();
    }
    public void setCurrentScene(Scene scene){
        this.currentScene = scene;
    }
    public void setCurrentTimer(GameTimer timer){
        this.currentTimer = timer;
    }
    public void setTimer(){
        //currentTimer.setLabel(lblPubTimer);
        currentTimer.getTimeline().setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                leaveStore();
                controller.updateCurrent();
            }
        });
    }

    public void goToMap() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/MapScene.fxml"));
            AnchorPane mapScreen = (AnchorPane) loader.load();

            Scene scene = new Scene(mapScreen);
            mapController controller = loader.getController();

            controller.setPrevScene(currentScene); // Scene is Town
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            controller.setCurrentPlayer(currentPlayer); //Passes current player to map
            controller.setMulePhaseLabels();
            controller.setStoreController(this);
            controller.getStage(primaryStage); //Current Stage everything is displayed on
            controller.setCurrentScene(currentScene);
            controller.setCurrentTimer(currentTimer);
            //controller.setTimer();
            //controller.setController(this);
            loader.setController(controller);
            controller.getMap(main.getGameMap());
            controller.connectMapWithPanes();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    public void updateStoreInventoryLabels() {
        lblPriceEnergy.setText("25");
        lblPriceFood.setText("30");
        lblPriceOre.setText("50");
        lblPriceMEnergy.setText("130");
        lblPriceMFood.setText("125");
        lblPriceMOre.setText("150");

        lblStockEnergy.setText("" + store.getEnergy());
        lblStockFood.setText("" + store.getFood());
        lblStockOre.setText("" + store.getOre());
        lblStockMEnergy.setText("" + store.getMuleAmount());
        lblStockMFood.setText("" + store.getMuleAmount());
        lblStockMOre.setText("" + store.getMuleAmount());
    }
}