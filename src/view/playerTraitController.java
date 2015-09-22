package View;

import Main.Main;
import Model.Player;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.midi.SysexMessage;

/**
 * Created by Shannor on 9/16/2015.
 * Controller for the PlayerTraits.fxml
 * Adds information for each player
 * Will lead to Map Screen afterwards
 */
public class playerTraitController extends gameScreenController {

    @FXML
    private Label lblPlayerNum;

    @FXML
    private ChoiceBox<String> cmbColor;

    @FXML
    private ChoiceBox<String> cmbRace;

    @FXML
    private TextField textName;

    @FXML
    private Button cntButton;

    private Main main;

    private Scene prevScene;

    private int playerCount;

    @FXML
    private void initialize(){

        cmbColor.getItems().addAll(
                "Green",
                "Blue",
                "Black",
                "Purple."
        );
        cmbRace.getItems().addAll(
                "Human",
                "Flapper",
                "Other"
        );

        cmbColor.getSelectionModel().select(0);
        cmbRace.getSelectionModel().select(0);
    }

    @FXML
    public void confirmChoices(){
        if(textName.getText().isEmpty()){
            //Error Handling
            Stage newStage = new Stage();
            VBox comp = new VBox();
            Label warning = new Label("Invalid Name");
            comp.getChildren().add(warning);
            Scene stageScene = new Scene(comp, 100, 100);
            newStage.setScene(stageScene);
            newStage.show();
        }else {
            if(playerCount > 0){
                //Add Player's information if more than one
                main.addPlayer(textName.getText(),cmbRace.getValue(),cmbColor.getValue());
                //Display another Screen
                main.showPlayerTraitScreen();
                Stage stage = (Stage)prevScene.getWindow();
                stage.close();
            }else{
                //If just one player, and end case for last player
                main.addPlayer(textName.getText(),cmbRace.getValue(),cmbColor.getValue());
                //Display the map Screen to start game
                main.showMapScreen();
                Stage stage = (Stage)prevScene.getWindow();
                stage.close();
            }

        }
    }

    @FXML
    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    public void setPrevScene(Scene scene){
        this.prevScene = scene;
    }

    public void setPlayerCount(int i){
        this.playerCount = i;
    }
}
