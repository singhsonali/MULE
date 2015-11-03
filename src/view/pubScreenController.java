package view;

import model.GameTimer;
import model.Player;
import model.Pub;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by Ashley on 9/30/2015.
 */
public class PubScreenController {

    /**
     * Return button.
     */
    @FXML
    private Button btnReturn;
    /**
     * Gamble button.
     */
    @FXML
    private Button btnGamble;

    /**
     * Label for timer.
     */
    @FXML
    private Label lblPubTimer;

    /**
     * The previous scene.
     */
    private Scene prevScene; //Town
    /**
     * The current timer.
     */
    private GameTimer currentTimer;
    /**
     * The current scene.
     */
    private Scene currentScene; //Pub
    /**
     * The current player.
     */
    private Player currentPlayer;
    /**
     * The gambling bonus for the round.
     */
    private int gambleBonus;
    /**
     * The TownMapController used.
     */
    private TownMapController controller;
    /**
     * The pub.
     */
    private Pub pub;
    /**
     * The primary stage.
     */
    private Stage primaryStage;

    /**
     * Initializes the controller.
     */
    private void initialize() {
        //Needed
    }

    /**
     * Sets the current timer.
     * @param timer what the current timer is set to
     */
    public final void setCurrentTimer(final GameTimer timer) {
        this.currentTimer = timer;
    }

    /**
     * Sets the label for the current timer and leaves pub when
     * the timer is finished.
     */
    public final void setTimer() {
        //currentTimer.setLabel(lblPubTimer);
        currentTimer.getTimeline().setOnFinished(event -> {
            leavePub();
            controller.updateCurrent();
        });
    }

    /**
     * Finds how much the player gets from gambling, prints
     * out amount earned, adds money to player, stops timer,
     * and leaves pub.
     */
    public final void playerGambles() {
        //Do some calculations to player then return player and close screen
        //End Turn
        int x = pub.calcGamble(gambleBonus, currentTimer.getTime());
        System.out.println("Gamble Money: " + x + " Player ="
                + currentPlayer.getName());
        currentPlayer.addMoney(x);
        //Turn is over
        currentTimer.stopTimer();
        leavePub();
    }

    /**
     * Sets the current player.
     * @param player the player currentPlayer is set to
     */
    public final void setCurrentPlayer(final Player player) {
        this.currentPlayer = player;
    }

    /**
     * Returns the current player.
     * @return the current player
     */
    public final Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the previous scene and creates a new pub.
     * @param scene the scene that prevScene is set to
     */
    public final void setPrevScene(final Scene scene) {
        this.prevScene = scene;
        pub = new Pub();
        //controller.updateCurrent();
    }

    /**
     * Sets the current scene.
     * @param scene the scene currentScene is set to
     */
    public final void setCurrentScene(final Scene scene) {
        this.currentScene = scene;
    }

    /**
     * Closes pub scene and shows previous scene.
     */
    public final void leavePub() {
        //Close the scene
        primaryStage.setScene(prevScene);
        primaryStage.show();

        Stage stage = new Stage();
        stage.setScene(currentScene);
        stage.close();

        controller.updateCurrent();
    }

    /**
     * Sets the gambling bonus.
     * @param bonus the amount gambling bonus is to be set to
     */
    public final void setGambleBonus(final int bonus) {
        this.gambleBonus = bonus;
    }

    /**
     * Sets the controller for the TownMapController.
     * @param townMapcontroller the TownMapController to be set to
     */
    public final void setController(final TownMapController townMapcontroller) {
        this.controller = townMapcontroller;
    }

    /**
     * Sets the primary stage.
     * @param stage the stage primary stage is to be set to
     */
    public final void setStage(final Stage stage) {
        this.primaryStage = stage;
    }
}
