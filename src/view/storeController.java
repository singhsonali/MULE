package View;
import Main.Main;
import Model.*;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.awt.*;

/**
 * Created by Ashley on 9/25/2015.
 */
public class storeController {

    private Pane currentPane;

    @FXML
    private Button btnReturn;
    @FXML
    private Button btnBuy;
    @FXML
    private Button btnSell;
    @FXML
    private TextField amnt;

    private Scene prevScene; //Town
    private Scene currentScene; //Store
    private Player currentPlayer;
    private int gambleBonus ;
    private townMapController controller;
    private Stage primaryStage;
    private Store store;

    public storeController() {

    }

    @FXML
    private void initialize(){
        currentPane = null;
    }

    public void getStage(Stage stage){
        this.primaryStage  = stage;
    }
    public void getCurrentPlayer(Player player){
        this.currentPlayer = player;
    }
    public void setPrevScene(Scene scene){
        this.prevScene = scene;
        store = new Store();
    }
    public void setCurrentScene(Scene scene){
        this.currentScene = scene;
    }
    public void leaveStore(){
        //Close the scene
        primaryStage.setScene(prevScene);
        primaryStage.show();

        Stage stage = new Stage();
        stage.setScene(currentScene);
        stage.close();

        controller.updateCurrent();
    }
    public void setController(townMapController controller){
        this.controller = controller;
    }
}
