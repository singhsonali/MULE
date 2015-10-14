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
    private GameTimer timer;
    private Player currentPlayer;
    private Main main;
    private Stage primaryStage; //Stage for the stores
    private Scene prevScene;
    private Scene currentScene;
    private ObservableList<Player> tempPlayers; //Array of sorted Players
    private int playersBeenToTown = 0; //When to stop town actions
    private Round currentRound; //Needed for gambling
    @FXML
    private Label lblPub;
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
                if(primaryStage !=null){
                    primaryStage.close();//Close any store player is in
                }
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
            Stage map = new Stage();
            map.setScene(prevScene);
            map.show();
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
        primaryStage = new Stage();
    }

    public void currentPlayerGambled(Player player){
        System.out.println(currentPlayer.getMoney());
        currentPlayer = player; //need to test
        System.out.println(currentPlayer.getMoney());
    }
    public void setCurrentRound(Round round){
        this.currentRound = round;
    }
    public int getTime(){
        return timer.getTime();
    }
    public void goToPub(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/pubScreen.fxml"));
            AnchorPane townMap = (AnchorPane) loader.load();

            Scene scene = new Scene(townMap);
            pubScreenController controller = loader.getController();

            controller.setPrevScene(currentScene);
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            //printPlayerData();
            controller.getStage(primaryStage);
            controller.setGambleBonus(currentRound.getGamblingBonus());
            controller.setCurrentScene(currentScene);
            System.out.println(currentPlayer.getName());
            controller.getCurrentPlayer(currentPlayer);
            controller.setController(this);
            loader.setController(controller);


        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
