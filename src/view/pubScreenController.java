package View;

import Main.Main;
import Model.GameTimer;
import Model.Player;
import Model.Pub;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by Ashley on 9/30/2015.
 */
public class pubScreenController {

    @FXML
    private Button btnReturn;
    @FXML
    private Button btnGamble;

    @FXML
    private Label lblPubTimer;

    private Scene prevScene; //Town
    private GameTimer currentTimer; //Current time
    private Scene currentScene; //Pub
    private Player currentPlayer; //Current player
    private int gambleBonus;
    private townMapController controller;
    private Pub pub;
    private Stage primaryStage;

    private void initialize() {
        //Needed
    }

    public void setCurrentTimer(GameTimer timer){
        this.currentTimer = timer;
    }
    public void setTimer(){
        currentTimer.setLabel(lblPubTimer);
        currentTimer.getTimeline().setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                leavePub();
                controller.updateCurrent();
            }
        });
    }

    public void playerGambles(){
        //Do some calculations to player then return player and close screen
        //End Turn
        int x = pub.calcGamble(gambleBonus, currentTimer.getTime());
        System.out.println("Gamble Money: " + x + " Player ="  + currentPlayer.getName());
        currentPlayer.addMoney(x);
        //Turn is over
        currentTimer.stopTimer();
        leavePub();
    }
    public void getCurrentPlayer(Player player){
        this.currentPlayer = player;
    }
    public void setPrevScene(Scene scene){
        this.prevScene = scene;
        pub = new Pub();
        //controller.updateCurrent();
    }
    public void setCurrentScene(Scene scene){
        this.currentScene = scene;
    }
    public void leavePub(){
        //Close the scene
        primaryStage.setScene(prevScene);
        primaryStage.show();

        Stage stage = new Stage();
        stage.setScene(currentScene);
        stage.close();

        controller.updateCurrent();
    }
    public void getStage(Stage stage){
        this.primaryStage  = stage;
    }
    public void setGambleBonus(int bonus){
        this.gambleBonus = bonus;
    }
    public void setController(townMapController controller){
        this.controller = controller;
    }
}