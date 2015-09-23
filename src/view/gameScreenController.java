package View;

/**
 * gameScreenController Created by Shannor
 * Controller class for the starting gameScreen
 * Takes the information for num of players, difficulty, and mapChoice
 *
 * @return Number of players, Map Chosen
 */
import Main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class gameScreenController{

    @FXML
    private Slider difficultySlider;

    @FXML
    private Button cntButton;

    @FXML
    private Slider numPlayerSlider;

    @FXML
    private ChoiceBox<String> cmbMapChoice;

    //Ref to main application
    private Main main;
    //Ref to last scene visited
    private Scene prevScene;

    //Constructor
    public gameScreenController(){

    }

    @FXML
    private void initialize(){

        //Set the default number of players to one
        numPlayerSlider.setValue(1);
        //Adds choices
        cmbMapChoice.getItems().addAll(
                "Default",
                "Normal",
                "Random"
        );
        //Sets first item as default of combo box
        cmbMapChoice.getSelectionModel().select(0);

        //When continue button is pressed
        cntButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Return number of players to main
                main.setPlayerCount((int)numPlayerSlider.getValue());
                //Return map choice to Main
                main.setMapChoice(getMapChoice());
                main.showPlayerTraitScreen();
                //Closes this screen
                Stage stage = (Stage) prevScene.getWindow();
                stage.close();
            }
        });
    }

    @FXML
    public String getMapChoice(){
        return cmbMapChoice.getValue();
    }
    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    public void setPrevScene(Scene scene){
        this.prevScene = scene;
    }


}
