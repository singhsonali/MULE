package Main;

import model.GameTimer;
import model.Map;
import model.Player;
import model.Round;
import view.mapController;
import view.townMapController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import view.gameScreenController;
import view.PlayerTraitController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Shannor on 9/23/2015.
 */
public class Main extends Application {

    private Stage primaryStage; //Stage the holds scene
    private Scene currentScene; //Current displayed scene
    public int players= 0; //Temp variable to count the number of players
    public String mapChoice; //Picked map
    //Structure to hold players
    private ObservableList<Player> playerData = FXCollections.observableArrayList();
    private Map gameMap = new Map(); //Gets map
    private Round round = new Round(); //Class for keeping track of rounds
    private Player currentPlayer;
    private boolean mulePhase = false;
    private boolean returningFromStore = false;
    private GameTimer returnTimer;
    private boolean landPhaseSkipped = false;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MULE");
        showGameScreen();
    }

    public void showGameScreen() {
        try {
            // Load Game Screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/gameScreen.fxml"));
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

    public void showPlayerTraitScreen(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/playerTraits.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();


            PlayerTraitController controller = loader.getController();


            Scene scene = new Scene(personOverview);
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            controller.setPlayerCount(--players); //Decrements player by one when passed in to be zero indexed
            loader.setController(controller);
            controller.setMainApp(this);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //After each player picks traits start the game loop
    public void showMapScreen(){
        try {
            // Load Map Screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/MapScene.fxml"));
            AnchorPane mapScreen= (AnchorPane) loader.load();


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
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showTownScreen(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/townMap.fxml"));
            AnchorPane townMap = (AnchorPane) loader.load();

            Scene scene = new Scene(townMap);
            townMapController controller = loader.getController();
            controller.setPrevScene(currentScene); //MapScene

            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            //printPlayerData();
            controller.setCurrentScene(currentScene); //Town Scene
            controller.setMyScene(currentScene);//Used for handling end of time for player turns
            controller.setPlayerData(playerData); //pass in all players
            controller.setCurrentRound(round); //Set round
            controller.getPrimaryStage(primaryStage);
            if (!returningFromStore) {
                controller.setTimer(); //Start timer
            } else {
                controller.setReturnTimer(returnTimer);
            }
            loader.setController(controller);
            controller.setMainApp(this);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addPlayer(Player player){
//        Player tempPlayer = new Player(name,Race,Color);
        playerData.add(player);
    }

    public void printPlayerData(){
        for(Player player: playerData){
            System.out.println(player.getResources());
            //System.out.println(player.getName() + ":" + player.getPlayerNum());
            //System.out.println("Money =" + player.getMoney() + " " + "Energy =" + player.getEnergy());
        }
    }
    public void setMapChoice(String mapChoice){ this.mapChoice = mapChoice;}

    public void setPlayerCount(int i){
        players = i;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void updatePlayerData(ObservableList<Player> playerData){
        this.playerData.removeAll();
        this.playerData = playerData;
    }

    public void setReturnTimer(GameTimer timer) {
        this.returnTimer = timer;
    }
    public void setReturningFromStore(boolean bool) {
        this.returningFromStore = bool;
    }
    public void setLandPhaseSkipped(boolean bool) {
        this.landPhaseSkipped = bool;
    }
    public void updateRound(Round round){
        this.round = round;
    }
    public void setCurrentPlayer(Player player){
        this.currentPlayer = player;
    }
    public Map getGameMap(){
        return this.gameMap;
    }
    public void setMulePhase(boolean bool) {
        this.mulePhase = true;
    }
}