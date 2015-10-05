package View;

import Main.Main;
import Model.GameTimer;
import Model.Player;
import Model.Round;
import com.sun.imageio.spi.RAFImageOutputStreamSpi;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.text.LabelView;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Created by Ashley on 10/1/2015.
 */
public class townMapController {

    @FXML
    private Label lblTimer;
    @FXML
    private Label lblName;

    private Scene myScene; //Used for checking if player is on town map or a store
    private GameTimer timer; //Display each players amount of time per turn
    private Player currentPlayer; //Current player's turn
    private Main main; // Reference to main methods
    private Stage primaryStage; //Stage for the stores
    private Scene prevScene; //To return back to the map
    private Scene currentScene; //Will change based on the store you enter
    private ObservableList<Player> tempPlayers; //Array of sorted Players
    private int playersBeenToTown = 0; //When to stop town actions
    private Round currentRound = null; //Needed for gambling
    @FXML
    private Label lblPub;
    @FXML
    private Label lblStore;
    @FXML
    public void initialize() {

    }

    public void setTimer(){
        timer = new GameTimer(currentPlayer.calcRoundTime());
        timer.setDuration(10);//Here for testing
        timer.setLabel(lblTimer);
        timer.startTimer();
        timer.getTimeline().setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateCurrent();
            }
        });
    }

    public void updateCurrent(){
        if(playersBeenToTown < tempPlayers.size()-1){
            playersBeenToTown++;
            currentPlayer = tempPlayers.get(playersBeenToTown);
            lblName.setText(currentPlayer.getName());
            setTimer();
        }else{
            main.printPlayerData();
            timer.stopTimer();
            primaryStage.setScene(prevScene);
            primaryStage.show();
            //Done here go back to Map to pick land
        }

    }

    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    public void setPrevScene(Scene scene){
        this.prevScene = scene;
    }
    public void setCurrentScene(Scene scene){
        this.currentScene = scene;
    }
    public void setPlayerData(ObservableList<Player> player){
        this.tempPlayers = player;
        currentPlayer = tempPlayers.get(0);
        lblName.setText(currentPlayer.getName());
    }

    public void getPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }
    public void setCurrentRound(Round round){
        this.currentRound = round;
    }
    public void setMyScene(Scene scene){
        this.myScene =scene;
    }
    public void goToPub(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/pubScreen.fxml"));
            AnchorPane townMap = (AnchorPane) loader.load();

            Scene scene = new Scene(townMap);
            pubScreenController controller = loader.getController();

            controller.setPrevScene(currentScene); // Scene is Town
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            controller.getStage(primaryStage); //Current Stage everything is displayed on
            controller.setGambleBonus(currentRound.getGamblingBonus());//Gambling Bonus
            controller.setCurrentScene(currentScene); //Scene is Pub
            controller.setCurrentTimer(timer);// Passes timer to Pub
            controller.setTimer();
            controller.getCurrentPlayer(currentPlayer); //Passes current player to pub
            controller.setController(this);
            loader.setController(controller);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void goToStore(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/storeScreen.fxml"));
            AnchorPane townMap = (AnchorPane) loader.load();

            Scene scene = new Scene(townMap);
            pubScreenController controller = loader.getController();

            controller.setPrevScene(currentScene); // Scene is Town
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            controller.getStage(primaryStage); //Current Stage everything is displayed on
            controller.setGambleBonus(currentRound.getGamblingBonus());//Gambling Bonus
            controller.setCurrentScene(currentScene); //Scene is Pub
            controller.setCurrentTimer(timer);// Passes timer to Pub
            controller.setTimer();
            controller.getCurrentPlayer(currentPlayer); //Passes current player to pub
            controller.setController(this);
            loader.setController(controller);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
