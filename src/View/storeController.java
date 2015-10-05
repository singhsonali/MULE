package View;
import Main.Main;
import Model.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by Ashley on 9/25/2015.
 */
public class storeController {

    private Pane currentPane;
    @FXML
    public TextField amnt;

    @FXML
    private Button btnReturn;
    @FXML
    private Button btnBuy;
    @FXML
    private Button btnSell;
    private Player currentPlayer;
    private Store store;
    @FXML
    private ChoiceBox item;


    private Stage primaryStage;
    private Scene prevScene; //Town
    private GameTimer currentTimer; //Current time
    private Scene currentScene; //Pub

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

        btnBuy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Return number of players to main
                String itemChoice = (String) item.getValue();
                if (itemChoice == "Food") {
                    buyFood(getNumChoice());
                } else if (itemChoice == "Energy") {
                    buyEnergy(getNumChoice());
                } else if (itemChoice == "Ore") {
                    buyOre(getNumChoice());
                }
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
            }
        });

        btnReturn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                leaveStore();
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
            store.setFood(store.getFood() - amnt);
        }
    }

    //onMouseClick event
    public void buyEnergy(int amnt) {
        if (currentPlayer.getEnergy() < (25 * amnt)) {
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
        if (currentPlayer.getOre() < (50 * amnt)) {
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
            store.setFood(store.getFood() + amnt);
        }
    }

    public void sellOre(int amnt) {
        if (currentPlayer.getOre() < amnt) {
            throw new IndexOutOfBoundsException("Cannot sell more ore than you have");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() + (amnt * 50));
            store.setOre(store.getOre() + amnt);
        }
    }

    public void sellEnergy(int amnt) {
        if (currentPlayer.getEnergy() < amnt) {
            throw new IndexOutOfBoundsException("Cannot sell more energy than you have");
        } else {
            currentPlayer.setMoney(currentPlayer.getMoney() + (amnt * 25));
            store.setEnergy(store.getEnergy() + amnt);
        }
    }

    @FXML
    public int getNumChoice(){
        return Integer.parseInt(amnt.getText());
    }


}
