package View;
import Main.Main;
import Model.Map;
import Model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Observable;

/**
 * Created by Melanie Smith on 9/20/2015.
 */
public class mapController {


    private Scene prevScene;

    private Main main;

    private ObservableList<Player> tempPlayers;
    private Player currentPlayer;
    private Map tempMap;
    private boolean next = false;
    private int column =0;
    private int row = 0;

    @FXML
    private Pane townPane;

    @FXML
    private Label lblPlayerName;

    @FXML
    private Button btnContinue;

    @FXML
    private Button btnSkip;

    public mapController(){

    }

    @FXML
    private void initialize(){


    }

    @FXML
    public void openTown(){
        //Provide conditional when this method can work.
        //ie if(land purchases are done)
        main.showTownScreen();
        Stage stage = (Stage)prevScene.getWindow();
        stage.close();
    }


    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    public void setPrevScene(Scene scene){
        this.prevScene = scene;
    }

    public void setPlayerData(ObservableList<Player> player){
        this.tempPlayers = FXCollections.observableArrayList(player);
        currentPlayer = tempPlayers.get(0);
        this.lblPlayerName.setText(currentPlayer.getName());
    }

    public void printPlayers(){
//        for(Player p : tempPlayers){
//            System.out.println(p.getName());
//        }
        System.out.println("Row:" + row + "Column"+ column );
    }
    public void getMap(Map map){
        this.tempMap = map;
    }

    @FXML
    public void setLandChoice00(){
        this.column = 0;
        this.row = 0;
    }
    @FXML
    public void setLandChoice01() {
        this.column = 1;
        this.row = 0;
    }


}
