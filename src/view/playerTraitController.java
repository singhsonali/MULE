package view;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Shannor on 9/16/2015.
 * Controller for the PlayerTraits.fxml
 * Will lead to Map Screen afterwards
 */
public class playerTraitController {

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

    @FXML
    private void initialize(){
        cmbColor.getItems().addAll(
                "Green",
                "Blue",
                "Black",
                "etc."
        );
        cmbRace.getItems().addAll(
                "Human",
                "Fappy",
                "etc."
        );
        cntButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showMapScreen();
                Stage stage = (Stage)prevScene.getWindow();
                stage.close();
            }
        });
    }

    public void setMainApp(Main mainApp) {
        this.main = mainApp;

    }

    public void setPrevScene(Scene scene){
        this.prevScene = scene;
    }

}
