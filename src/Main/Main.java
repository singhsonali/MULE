package main;

import model.GameTimer;
import model.Map;
import model.Player;
import model.Round;
import view.TownMapController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import view.gameScreenController;
import view.PlayerTraitController;
import view.mapController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;

/**
 * Created by Shannor on 9/23/2015.
 */
public class Main extends Application {

    /**
     * The primary stage that holds the scene.
     */
    private Stage primaryStage;
    /**
     * The current scene: what the player sees.
     */
    private Scene currentScene;
    /**
     * Temp variable to count the number of players.
     */
    private int players = 0;
    /**
     * Chosen map.
     */
    private String mapChoice;
    /**
     * Structure to hold players.
     */
    private ObservableList<Player> playerData =
            FXCollections.observableArrayList();
    /**
     * Gets map.
     */
    private Map gameMap = new Map();
    /**
     * Class for keeping track of rounds.
     */
    private Round round = new Round();
    /**
     * Points to the currentPlayer (who's turn it is).
     */
    private Player currentPlayer;
    /**
     * mulePhase is initially false.
     */
    private boolean mulePhase = false;
    /**
     * Players are not initially returning from store.
     */
    private boolean returningFromStore = false;
    /**
     * Sets up the game time to keep track of turn length.
     */
    private GameTimer returnTimer;
    /**
     * The land phase is not initially skipped.
     */
    private boolean landPhaseSkipped = false;

    /**
     * Initializes the primary stage.
     * @param primaryStages the first stage of the game.
     */
    @Override
    public final void start(final Stage primaryStages) {
        this.primaryStage = primaryStages;
        this.primaryStage.setTitle("MULE");
        showGameScreen();
    }

    /**
     * Shows the game screen.
     */
    public final void showGameScreen() {
        try {
            // Load Game Screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(
                    "../view/gameScreen.fxml"));
            AnchorPane gameScreen = (AnchorPane) loader.load();
            //Creates new scene
            Scene scene = new Scene(gameScreen);
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();
            gameScreenController controller = loader.getController();
            loader.setController(controller);
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the player trait screen.
     * Players choose their race/color/name here.
     */
    public final void showPlayerTraitScreen() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(
                    "../view/playerTraits.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            PlayerTraitController controller = loader.getController();
            Scene scene = new Scene(personOverview);
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();
            controller.setPlayerCount(--players);
            loader.setController(controller);
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the map screen where players can buy/select land to own.
     */
    //After each player picks traits start the game loop
    public final void showMapScreen() {
        try {
            // Load Map Screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(
                    "../view/MapScene.fxml"));
            AnchorPane mapScreen = (AnchorPane) loader.load();
            mapController controller = loader.getController();
            controller.setPrevScene(currentScene);
            Scene scene = new Scene(mapScreen);
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            controller.setLandPhaseSkipped(landPhaseSkipped);

            //Pass in player Array and Map Data
            controller.setPlayerData(playerData);
            controller.getMap(gameMap);
            controller.connectMapWithPanes();
            controller.setRound(round);
            controller.setMulePhase(mulePhase);

            loader.setController(controller);
            controller.setMainApp(this);

            //printPlayerData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the town screen.
     * Town screen has the Pub, Land Office, Assay, Store.
     */
    public final void showTownScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/townMap.fxml"));
            AnchorPane townMap = loader.load();

            Scene scene = new Scene(townMap);
            TownMapController controller = loader.getController();

            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            //printPlayerData();
            controller.setCurrentScene(currentScene); //Town Scene
            //Used for handling end of time
            //controller.setMyScene(currentScene);
            controller.setPlayerData(playerData); //pass in all players
            controller.setCurrentRound(round); //Set round
            controller.setPrimaryStage(primaryStage);
            if (!returningFromStore) {
                controller.setTimer(); //Start timer
            } else {
                controller.setCurrentTimer(returnTimer);
            }
            loader.setController(controller);
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a player.
     * @param player An instance of Player.
     */
    public final void addPlayer(final Player player) {
//        Player tempPlayer = new Player(name,Race,Color);
        playerData.add(player);
    }

    /**
     * Prints the resources that a player owns.
     */
    public final void printPlayerData() {
        for (Player player: playerData) {
            System.out.println(player.getResources());
        }
    }

    /**
     * Sets the map choice.
     * @param mapPick The string indicator for the map type.
     */
    public final void setMapChoice(final String mapPick) {
        this.mapChoice = mapPick;
    }

    /**
     * Sets the number of players in the game.
     * @param i Number of players playing the game.
     */
    public final void setPlayerCount(final int i) {
        players = i;
    }

    /**
     * Launches the game.
     * @param args Argument in main.
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * Updates the player list.
     * @param playerData2 A list of players and their data.
     */
    public final void updatePlayerData(
            final ObservableList<Player> playerData2) {
        this.playerData.removeAll();
        this.playerData = playerData2;
    }

    /**
     * setter for the timer.
     * @param timer An instance of GameTimer.
     */
    public final void setReturnTimer(final GameTimer timer) {
        this.returnTimer = timer;
    }

    /**
     * setter method for the following.
     * @param bool Is a player returning from the store?
     */
    public final void setReturningFromStore(final boolean bool) {
        this.returningFromStore = bool;
    }

    /**
     * setter method for the following.
     * @param bool Is a player skipping their land selection phase?
     */
    public final void setLandPhaseSkipped(final boolean bool) {
        this.landPhaseSkipped = bool;
    }

    /**
     * see below.
     * @param rounds An instance of Round.
     */
    public final void updateRound(final Round rounds) {
        this.round = rounds;
    }

    /**
     * Sets the player whose turn it is.
     * @param player An instance of player.
     */
    public final void setCurrentPlayer(final Player player) {
        this.currentPlayer = player;
    }

    /**
     * Gets the game map.
     * @return Returns an instance of Map.
     */
    public final Map getGameMap() {
        return this.gameMap;
    }

    /**
     * see below.
     * @param bool Is it the Mule Phase?
     */
    public final void setMulePhase(final boolean bool) {
        this.mulePhase = true;
    }
    /**
     * Saves the game.
     */
    public final void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveFile.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            ArrayList<Player> temp = new ArrayList<>();
            for(Player p : playerData){
                temp.add(p);
            }
            out.writeObject(temp); //Save all player information
            out.writeObject(round); //Save the round information
            out.writeObject(gameMap); //Save the current Map
            out.close(); //close
            fileOut.close();
            System.out.println("Saved data is saved in saveFile.txt");
            System.out.println("Saved Player 1:" + temp.get(0).getName());
            System.out.println("Saved Round:" + round.getRound());
            System.out.println("Saved Player 2:" + playerData.get(1).getName());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the game
     */
    public void loadGame(){
        try{
            ArrayList<Player> temp = new ArrayList<>();
            FileInputStream fileIn = new FileInputStream("saveFile.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            temp = (ArrayList<Player>) in.readObject();
            this.round = (Round)in.readObject();
            this.gameMap =(Map)in.readObject();
            in.close();
            fileIn.close();
            //Clear current playerData and add new player back
            this.playerData.clear();
            for(Player p : temp){
                this.playerData.add(p);
            }
            System.out.println("Loaded Player 1:" + playerData.get(0).getName());
            System.out.println("Saved Round:" + round.getRound());
            System.out.println("Loaded Player 2:" + playerData.get(1).getName());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }
}
