package View;


import Main.Main;
import Model.Map;
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
public class townController {

    @FXML
    private Pane landOffice;

    @FXML
    private Pane store;

    @FXML
    private Pane pub;

    @FXML
    private Pane assay;


    //Ref to main application
    private Main main;
    private Scene prevScene;
    private Map tempMap;

    //Constructor
    public townController(){

    }

    @FXML
    public void openPub() {
        main.showPubScreen();
        Stage stage = (Stage) prevScene.getWindow();
        stage.close();
    }

    @FXML
    private void initialize(){

    }
}
