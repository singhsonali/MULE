package Main;

import com.sun.javafx.PlatformUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Shannor on 9/23/2015.
 */
public class Main extends Application {

    private Stage primaryStage; //Stage the holds scnes
    private Scene currentScene; //Current displayed scene
    public int players= 0; //Temp variable to count the number of players
    public String mapChoice; //Picked map
    //Structure to hold players
    private ObservableList<Player> playerData = FXCollections.observableArrayList();
    private Map gameMap = new Map(); //Gets map
    private Round round = new Round(); //Class for keeping track of rounds
    private Player currentPlayer;
    private boolean mulePhase = false;

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
            controller.setTimer(); //Start timer
            loader.setController(controller);
            controller.setMainApp(this);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    class production implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent arg0){
                productionFrame produce = new productionFrame();
            }
            
        }
        class productionFrame extends JFrame {
            
            public productionFrame(){
                setTitle("Production");
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                setSize(400, 300);
                setVisible(true);
                setLocationRelativeTo(null);
                setResizable(false);
                JPanel list = new JPanel();
                list.setLayout(new GridLayout(4,1));
                JLabel dialog, foodProduction, energyProduction, oreProduction;
                int food, energy, ore;
                food = 0; energy = 0; ore = 0;
                dialog = new JLabel("<html>How many resources you are producing is shown below.<br>It takes one energy per mule to produce resources.<br>If you have 0 energy you are unable to produce anything.</html>");
                foodProduction = new JLabel("Currently producing: " + food + " food");
                energyProduction = new JLabel("Currently producing: " + energy + " energy");
                oreProduction = new JLabel("Currently producing: " + ore + " ore");
                add(list);
                list.add(dialog);
                list.add(foodProduction);
                list.add(energyProduction);
                list.add(oreProduction);
                
                if(player.getOwnedTiles().size() >= 1){
                    if(player.getEnergy() == 0){
                        foodProduction.setText("Currently producing: 0 food");
                        energyProduction.setText("Currently producing: 0 energy");
                        oreProduction.setText("Currently producing: 0 ore");
                    }
                    else {
                        for(int i = 0; i < player.getOwnedTiles().size(); i++){
                            Tile land = player.getOwnedTiles().get(i);
                            if(land.hasMule() && land.getMule().toString() == "Food Mule" && player.getEnergy() > 0){
                                food += land.getFoodRate();
                                energy -= 1;
                            }
                            else if(land.hasMule() && land.getMule().toString() == "Energy Mule" && player.getEnergy() > 0)
                                energy += land.getEnergyRate()-1;
                            else if(land.hasMule() && land.getMule().toString() == "Ore Mule" && player.getEnergy() > 0){
                                ore += land.getOreRate();
                                energy -= 1;
                            }
                            foodProduction.setText("Currently producing: " + food + " food");
                            energyProduction.setText("Currently producing: " + energy + " energy");
                            oreProduction.setText("Currently producing: " + ore + " ore");
                        }
                    }
                    
                }
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

    public void updatePlayerData(ObservableList<Player> playerData){
        this.playerData.removeAll();
        this.playerData = playerData;
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