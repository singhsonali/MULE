package Main;

import Model.Player;
import View.gameScreenController;
import View.playerTraitController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private Scene currentScene;
    public int players= 0; //Temp variable to count the number of players
    public String mapChoice;
    //Structure to hold players
    private ObservableList<Player> playerData = FXCollections.observableArrayList();

    //Vars needed
    //Player Conatiner
    //Difficulty choice
    //Map Choice
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
    public void showMapScreen(){
        try {
            // Load Map Screen.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/MapScence.fxml"));
            AnchorPane mapScreen= (AnchorPane) loader.load();

            Scene scene = new Scene(mapScreen);
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
        playerData.add(player);
    }

    public void printPlayerData(){
        for(Player player: playerData){
            System.out.println(player.getName() + ":" + player.getPlayerNum());
        }
    }
    public void setMapChoice(String mapChoice){ this.mapChoice = mapChoice;}

    public void setPlayerCount(int i){
        players = i;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
