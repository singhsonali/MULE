package View;


import Main.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 * Created by rileyauten on 10/1/15.
 */
public class townMapController {

    @FXML
    private Pane landOffice;

    @FXML
    private Pane store;

    @FXML
    private Pane pub;

    @FXML
    private Pane assay;

    @FXML
    private Label lblTimer;


    //Ref to main application
    private Main main;
    //Ref to last scene visited
    private Scene prevScene;

    private Pane currentPane;

    //Constructor
    public townMapController(){

    }

    @FXML
    private void initialize(){
        currentPane = null;
    }

    @FXML
    public void openPub() {
        main.showPubScreen();;
        Stage stage = (Stage) prevScene.getWindow();
        stage.close();
    }

    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    public void setPrevScene(Scene scene){
        this.prevScene = scene;
    }
}