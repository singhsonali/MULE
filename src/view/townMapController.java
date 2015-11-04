package view;

import main.Main;
import model.GameTimer;
import model.Player;
import model.Round;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Ashley on 10/1/2015.
 */
public class TownMapController {

    /**
     * Label for timer.
     */
    @FXML
    private Label lblTimer;
    /**
     * Label for name.
     */
    @FXML
    private Label lblName;
    /**
     * Label for player's money.
     */
    @FXML
    private Label lblPlayerMoney;
    /**
     * Label for player's energy.
     */
    @FXML
    private Label lblPlayerEnergy;
    /**
     * Label for player's ore.
     */
    @FXML
    private Label lblPlayerOre;
    /**
     * Label for player's food.
     */
    @FXML
    private Label lblPlayerFood;

    /**
     * Display each player's amount of time per turn.
     */
    private GameTimer timer;
    /**
     * The current player.
     */
    private Player currentPlayer;
    /**
     * Reference to main methods.
     */
    private Main main;
    /**
     * Stage for the stores.
     */
    private Stage primaryStage;
    /**
     * The current scene. Will change based on the store you enter.
     */
    private Scene currentScene;
    /**
     * List of sorted players.
     */
    private ObservableList<Player> tempPlayers;
    /**
     * Variable used to tell when to stop town actions.
     */
    private int playersBeenToTown = 0;
    /**
     * Current round, needed for gambling.
     */
    private Round currentRound = null;
    /**
     * Label for Pub.
     */

    @FXML
    public void initialize() {

    }

    /**
     * Sets the timer, sets the timer label, updates current player
     * when timer ends.
     */
    public final void setTimer() {
        timer = new GameTimer(currentPlayer.calcRoundTime());
        //timer.setDuration(10); //Here for testing
        timer.setLabel(lblTimer);
        timer.startTimer();
        timer.getTimeline().setOnFinished(event -> updateCurrent());
    }

    /**
     * If not all players have been to town, updates current player,
     * triggers random event generator, and updates player labels.
     * Otherwise, starts mule production, updates main, and returns to map.
     */
    public final void updateCurrent() {
        if (playersBeenToTown < tempPlayers.size() - 1) {
            playersBeenToTown++;
            currentPlayer = tempPlayers.get(playersBeenToTown);
            currentRound.randEvent(tempPlayers, currentPlayer);
            updatePlayerInfoLabels();
            setTimer();
            //goToMap();
        } else {
            for (Player player : tempPlayers) {
                player.muleProduction();
            }
            main.updatePlayerData(tempPlayers);
            main.printPlayerData();
            timer.stopTimer();
            /*primaryStage.setScene(prevScene);
            primaryStage.show();*/
            main.showMapScreen();
            //Done here go back to Map to pick land
        }
    }

    /**
     * Returns to the map screen.
     */
    public final void goToMap() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/MapScene.fxml"));
            AnchorPane mapScreen = loader.load();

            Scene scene = new Scene(mapScreen);
            MapController controller = loader.getController();

            controller.setPrevScene(currentScene); // Scene is Town
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            //Passes current player to map
            controller.setCurrentPlayer(currentPlayer);
            //Current Stage everything is displayed on
            controller.getStage(primaryStage);
            controller.setCurrentScene(currentScene);
            controller.updatePlayerLabel();
            controller.setLandSelectionFinished(true);
            controller.setMainApp(main);
            //controller.setTimer();
            //controller.setController(this);
            loader.setController(controller);
            controller.getMap(main.getGameMap());
            controller.connectMapWithPanes();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the labels containing player information.
     */
    public final void updatePlayerInfoLabels() {
        lblName.setText(currentPlayer.getName());
        lblPlayerMoney.setText("Money: " + currentPlayer.getMoney());
        lblPlayerFood.setText("Food: " + currentPlayer.getFood());
        lblPlayerOre.setText("Ore: " + currentPlayer.getOre());
        lblPlayerEnergy.setText("Energy: " + currentPlayer.getEnergy());
    }

    /**
     * Sets the main variable.
     * @param mainApp what the main variable is set to
     */
    public final void setMainApp(final Main mainApp) {
        this.main = mainApp;
    }

    /**
     * Sets the current scene.
     * @param scene what the current scene is set to
     */
    public final void setCurrentScene(final Scene scene) {
        this.currentScene = scene;
    }

    /**
     * Sets player data and triggers updating player information labels.
     * @param player list of players
     */
    public final void setPlayerData(final ObservableList<Player> player) {
        this.tempPlayers = player;
        currentPlayer = tempPlayers.get(0);
        updatePlayerInfoLabels();
    }

    /**
     * Sets the primary stage.
     * @param stage what the primary stage is set to
     */
    public final void setPrimaryStage(final Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Sets the current round.
     * @param round what the current round is set to
     */
    public final void setCurrentRound(final Round round) {
        this.currentRound = round;
    }

    /**
     * Sets the current timer
     * @param gameTimer what the current timer is set to
     */
    public final void setCurrentTimer(final GameTimer gameTimer) {
        setTimer();
        this.timer.setDuration(gameTimer.getTime());
        timer.startTimer();
    }

    /**
     * Sets the scene to pub.
     */
    public final void goToPub() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class
                    .getResource("../view/pubScreen.fxml"));
            AnchorPane townMap = loader.load();

            Scene scene = new Scene(townMap);
            PubScreenController controller = loader.getController();

            controller.setPrevScene(currentScene); // Scene is Town
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            //Current Stage everything is displayed on
            controller.setStage(primaryStage);
            controller.setGambleBonus(currentRound.getGamblingBonus());
            controller.setCurrentScene(currentScene); //Scene is Pub
            controller.setCurrentTimer(timer); // Passes timer to Pub
            controller.setTimer();
            //Passes current player to pub
            controller.setCurrentPlayer(currentPlayer);
            controller.setController(this);
            loader.setController(controller);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the store screen.
     */
    public final void goToStore() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class
                    .getResource("../view/storeScreen.fxml"));
            AnchorPane townMap = loader.load();

            Scene scene = new Scene(townMap);
            StoreController controller = loader.getController();

            //controller.setPrevScene(currentScene); // Scene is Town
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            controller.setMainApp(main);
            //Current Stage everything is displayed on
            controller.getStage(primaryStage);
            controller.setCurrentScene(currentScene); //Scene is Store
            controller.setCurrentTimer(timer); // Passes timer to Store
            controller.setTimer();
            //Passes current player to Store
            controller.setCurrentPlayer(currentPlayer);
            controller.setController(this);
            loader.setController(controller);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
