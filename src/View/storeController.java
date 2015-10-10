package View;
import Main.Main;
import Model.*;

import Main.Main;
import View.mapController;
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

import java.awt.*;
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
    private Scene currentScene; //Pub
    private townMapController controller;
    private GameTimer timer;
    private Mule energyMule;
    private Mule foodMule;
    private Mule oreMule;



    @FXML
    public TextField stuff;
    @FXML
    private Button btnReturn;
    @FXML
    private Button btnBuy;
    @FXML
    private Button btnSell;
    @FXML
    private Button buyMule;
    @FXML
    private ChoiceBox item;
    @FXML
    private ChoiceBox muleBox;




    public storeController() {

    }

    @FXML
    private void initialize(){
        currentPane = null;

        item.getItems().addAll(
                "Food",
                "Energy",
                "Ore"
        );

        muleBox.getItems().addAll(
                "Energy Mule",
                "Food Mule",
                "Ore Mule"
        );

        btnBuy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Return number of players to main
                String itemChoice = (String) item.getValue();
                if (itemChoice.equals("Food")) {
                    buyFood(getNumChoice());
                } else if (itemChoice.equals("Energy")) {
                    buyEnergy(getNumChoice());
                } else if (itemChoice.equals("Ore")) {
                    buyOre(getNumChoice());
                }
                leaveStore();
            }
        });

        btnSell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String itemChoice = (String) item.getValue();
                if (itemChoice == "Food") {
                    sellFood(getNumChoice());
                } else if (itemChoice == "Energy") {
                    sellEnergy(getNumChoice());
                } else if (itemChoice == "Ore") {
                    sellOre(getNumChoice());
                }
                leaveStore();
            }
        });

        btnReturn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                leaveStore();
            }
        });

        buyMule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String muleChoice = (String) muleBox.getValue();
                if (muleChoice.equals("Energy Mule")) {
                    buyEnergyMule();
                } else if (muleChoice.equals("Food Mule")) {
                    buyFoodMule();
                } else if (muleChoice.equals("Ore Mule")) {
                    buyOreMule();
                }
                goToMap();
            }
        });
    }

    public void leaveStore(){
        //Close the scene
        primaryStage.setScene(prevScene);
        primaryStage.show();

        Stage stage = new Stage();
        stage.setScene(currentScene);
        stage.close();


        //controller.updateCurrent();
    }

    //If there is a separate buy/sell screen that we are going to implement, these methods will have to be under a different controller. I'm just putting them here for now
    // onMouseClick event
    public void buyOreMule() {
        if (currentPlayer.getMoney() < 175) {
            throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        } else if (store.getMuleAmount() <= 0) {
            throw new IndexOutOfBoundsException("There are no longer any ore MULEs for purchase");
        } else if (currentPlayer.hasMule()) {
                throw new IllegalArgumentException("Cannot purchase MULE if you have MULE that haven't been placed");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - 175);
            currentPlayer.setOreMule(currentPlayer.getOreMule() + 1);
            currentPlayer.setMuleString("oreMule");
            currentPlayer.setHoldingMule(oreMule);
            store.deleteMule();
        }
    }

    // onMouseClick event
    public void buyEnergyMule() {
        if (currentPlayer.getMoney() < 150) {
            throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        } else if (store.getMuleAmount() <= 0) {
            throw new IndexOutOfBoundsException("There are no longer any energy MULEs for purchase");
        } else if (currentPlayer.hasMule()) {
            throw new IllegalArgumentException("Cannot purchase MULE if you have MULE that haven't been placed");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - 150);
            currentPlayer.setEnergyMule(currentPlayer.getEnergyMule() + 1);
            currentPlayer.setMuleString("energyMule");
            System.out.println(currentPlayer.getMuleType());
            currentPlayer.setHoldingMule(energyMule);
            System.out.println(currentPlayer.getHoldingMule());
            store.deleteMule();
        }
    }

    //onMouseClick event
    public void buyFoodMule() {
        if (currentPlayer.getMoney() < 125) {
            throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        } else if (store.getMuleAmount() <= 0) {
            throw new IndexOutOfBoundsException("There are no longer any food MULEs for purchase");
        } else if (currentPlayer.hasMule()) {
                throw new IllegalArgumentException("Cannot purchase MULE if you have MULE that haven't been placed");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - 125);
            currentPlayer.setFoodMule(currentPlayer.getFoodMule() + 1);
            currentPlayer.setMuleString("foodMule");
            currentPlayer.setHoldingMule(foodMule);
            store.deleteMule();
        }
    }

    //onMouseClick event
    public void buyFood(int amnt) {
        if (currentPlayer.getMoney() < (30 * amnt)) {
            throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        } else if (store.getEnergy() < amnt) {
            throw new IndexOutOfBoundsException("There is less than " + amnt + " food left in the store. You can purchase up to " + store.getFood() + " food.");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - (30 * amnt));
            currentPlayer.setFood(currentPlayer.getFood() + amnt);
            //currentPlayer.setHoldingMule(currentPlayer.getHoldingMule() + 1);
            store.setFood(store.getFood() - amnt);
        }
    }

    //onMouseClick event
    public void buyEnergy(int amnt) {
        if (currentPlayer.getMoney() < (25 * amnt)) {
            throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        } else if (store.getEnergy() < amnt) {
            throw new IndexOutOfBoundsException("There is less than " + amnt + " energy left in the store. You can purchase up to " + store.getEnergy() + " energy.");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - (25 * amnt));
            currentPlayer.setEnergy(currentPlayer.getEnergy() + amnt);
            store.setEnergy(store.getEnergy() - amnt);
        }
    }

    //onMouseClick event
    public void buyOre(int amnt) {
        if (currentPlayer.getMoney() < (50 * amnt)) {
            throw new IndexOutOfBoundsException("Insufficient funds: Cannot be in debt");
        } else if (store.getEnergy() < amnt) {
            throw new IndexOutOfBoundsException("There is less than " + amnt + " ore left in the store. You can purchase up to " + store.getOre() + " ore.");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() - (50 * amnt));
            currentPlayer.setOre(currentPlayer.getOre() + amnt);
            store.setOre(store.getOre() - amnt);
        }
    }

    //onMouseClick event

    public void sellFood(int amnt) {
        if (currentPlayer.getFood() < amnt) {
            throw new IndexOutOfBoundsException("Cannot sell more food than you have");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() + (amnt * 30));
            currentPlayer.setFood(currentPlayer.getFood() - amnt);
            store.setFood(store.getFood() + amnt);
        }
    }

    public void sellOre(int amnt) {
        if (currentPlayer.getOre() < amnt) {
            throw new IndexOutOfBoundsException("Cannot sell more ore than you have");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() + (amnt * 50));
            currentPlayer.setOre(currentPlayer.getOre() - amnt);
            store.setOre(store.getOre() + amnt);
        }
    }

    public void sellEnergy(int amnt) {
        if (currentPlayer.getEnergy() < amnt) {
            throw new IndexOutOfBoundsException("Cannot sell more energy than you have");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() + (amnt * 25));
            currentPlayer.setEnergy(currentPlayer.getEnergy() - amnt);
            store.setEnergy(store.getEnergy() + amnt);
        }
    }

    @FXML
    public int getNumChoice(){
        return Integer.parseInt(stuff.getText());
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
        store = new Store();
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
            System.out.println(currentPlayer.getHoldingMule() + "wassup");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/MapScene.fxml"));
            AnchorPane townMap = (AnchorPane) loader.load();

            Scene scene = new Scene(townMap);
            mapController controller = loader.getController();

            controller.setPrevScene(currentScene); // Scene is Town
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            controller.getStage(primaryStage); //Current Stage everything is displayed on
            //controller.setGambleBonus(currentRound.getGamblingBonus());//Gambling Bonus
            controller.setCurrentScene(currentScene); //Scene is Pub
            //controller.setCurrentTimer(timer);// Passes timer to Pub
            //controller.setTimer();
            controller.getCurrentPlayer(currentPlayer); //Passes current player to pub
            //controller.setController(this);
            loader.setController(controller);
            System.out.println(currentPlayer.getHoldingMule() + "hello");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }
}
