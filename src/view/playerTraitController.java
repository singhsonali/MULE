package View;

import Main.Main;
import model.Player;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

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

    @FXML
    private Label lblWarning;

    private Main main;

    private Scene prevScene;

    private int playerCount;

    private int playerNum;

    private ArrayList<String> colors;
    private boolean boolGreen = true;
    private boolean boolBlue = true;
    private boolean boolYellow = true;
    private boolean boolRed = true;

    @FXML
    private void initialize(){

        setPlayerNum(playerCount);

        lblPlayerNum.setText("Player 1: Choose Your Options");
        setColors();

        cmbColor.getItems().addAll(colors);
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
            /*Stage newStage = new Stage();
            VBox comp = new VBox();
            Label warning = new Label("Invalid Name");
            comp.getChildren().add(warning);
            Scene stageScene = new Scene(comp, 100, 100);
            newStage.setScene(stageScene);
            newStage.show(); */
            lblWarning.setText("Please enter a name.");
        }else {
            if(playerCount > 0){
                //Add Player's information if more than one
                Player tempPlayer = new Player(textName.getText(),cmbRace.getValue(),cmbColor.getValue());
                main.addPlayer(tempPlayer);
                if (cmbColor.getValue().equals("Green")) {
                    boolGreen = false;
                } else if (cmbColor.getValue().equals("Blue")) {
                    boolBlue = false;
                } else if (cmbColor.getValue().equals("Yellow")) {
                    boolYellow = false;
                } else {
                    boolRed = false;
                }
                //Resets PlayerTraitScreen
                setColors();
                cmbColor.valueProperty().setValue(colors.get(0));
                cmbRace.valueProperty().setValue("Human");
                textName.clear();
                lblWarning.setText("");
                setPlayerCount(playerCount - 1);
                lblPlayerNum.setText("Player " + playerNum + ": Choose Your Options");
                //Display another Screen
                /*main.showPlayerTraitScreen();
                Stage stage = (Stage)prevScene.getWindow();
                stage.close(); */
            }else{
                //If just one player, and end case for last player
                Player tempPlayer = new Player(textName.getText(),cmbRace.getValue(),cmbColor.getValue());
                main.addPlayer(tempPlayer);
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
        setPlayerNum(playerNum + 1);
    }

    public void setPlayerNum(int i) {
        this.playerNum = i;
    }

    public void setColors() {
        //Resets color combo box so no two players can have the same color
        colors = new ArrayList<>();
        if (this.boolGreen) {
            this.colors.add("Green");
        } if (this.boolBlue) {
            this.colors.add("Blue");
        } if (this.boolYellow) {
            this.colors.add("Yellow");
        } if (this.boolRed) {
            this.colors.add("Red");
        }
        cmbColor.getItems().setAll(colors);
    }
}
