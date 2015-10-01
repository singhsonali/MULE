package Main;

import Model.Map;
import Model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import View.gameScreenController;
import View.playerTraitController;
import View.mapController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Shannor on 9/23/2015.
 */
public class Main extends Application {

    private Stage primaryStage;
    private Scene currentScene;
    public int players= 0; //Temp variable to count the number of players
    public String mapChoice;
    public static int roundCount = 0; //Max is 12
    //Structure to hold players
    private ObservableList<Player> playerData = FXCollections.observableArrayList();
    private Map gameMap = new Map();


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

            Scene scene = new Scene(gameScreen);
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            //Gets Controller that preforms actions to the scene
            gameScreenController controller = loader.getController();
            controller.setPrevScene(currentScene);
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

            Scene scene = new Scene(personOverview);
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            playerTraitController controller = loader.getController();
            controller.setPrevScene(currentScene);
            controller.setPlayerCount(--players);
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

            Scene scene = new Scene(mapScreen);
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            mapController controller = loader.getController();
            controller.setPrevScene(currentScene);
            //Pass in player Array and Map Data
            controller.setPlayerData(playerData);
            controller.getMap(gameMap);
            controller.connectMapWithPanes();
            loader.setController(controller);
            controller.setMainApp(this);
            printPlayerData();
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
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            printPlayerData();

        }catch (IOException e){
            e.printStackTrace();
        }


    }

    public void showTownMap(){
        try {
            // Load Town Map.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/townMap.fxml"));
            AnchorPane townMap= (AnchorPane) loader.load();

            Scene scene = new Scene(townMap);
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

//            playerTraitController controller = loader.getController();
//            controller.setPrevScene(currentScene);
//            loader.setController(controller);
//            controller.setMainApp(this);

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
            System.out.println(player.getName() + ":" + player.getPlayerNum());
            System.out.println("Money =" + player.getMoney() + " " + "Energy =" + player.getEnergy());
        }
    }
    public void setMapChoice(String mapChoice){ this.mapChoice = mapChoice;}

    public void setPlayerCount(int i){
        players = i;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setPlayerData(ObservableList<Player> player){
        player = FXCollections.observableArrayList(playerData);
    }

    public Map getGameMap(){
        return this.gameMap;
    }
}

