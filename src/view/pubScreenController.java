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
    private Label lblTimer;

    private Stage stage;
    private Scene prevScene; //Town
    private GameTimer timer; //Current time
    private Scene currentScene; //Pub
    private Player currentPlayer; //Current player
    private int gambleBonus ;
    private townMapController controller;
    private Pub pub;

    private void initialize() {
        //Needed
    }

    public void playerGambles(){
        //Do some calculations to player then return player and close screen
        //End Turn
        int time = controller.getTime();
        int x = pub.calcGamble(gambleBonus, time);
        System.out.println(time);
        //currentPlayer.setMoney(x);
        //controller.currentPlayerGambled(currentPlayer);
        leavePub();
    }
    public void getCurrentPlayer(Player player){
        this.currentPlayer = player;
    }
    public void setPrevScene(Scene scene){
        this.prevScene = scene;
        pub = new Pub();
    }
    public void setCurrentScene(Scene scene){
        this.currentScene = scene;
    }
    public void leavePub(){
        //Close the scene
        stage.setScene(currentScene);
        stage.close();
    }
    public void getStage(Stage stage){
        this.stage = stage;
    }
    public void setGambleBonus(int bonus){
        this.gambleBonus = bonus;
    }
    public void setController(townMapController controller){
        this.controller = controller;
    }

}