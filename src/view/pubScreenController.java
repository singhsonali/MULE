package View;

import Main.Main;
import Model.GameTimer;
import Model.Player;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by Ashley on 9/30/2015.
 */
public class pubScreenController {

    @FXML
    private Button btnGamble;

    @FXML
    private Label lblTimer;

    private Main main;
    private Scene prevScene;
    private GameTimer timer;
    private Player currentPlayer;

    private void initialize() {

        //When continue button is pressed
        btnGamble.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                timer.setDuration(0);
                currentPlayer.setTurn(false);
                //Closes this screen
                Stage stage = (Stage) prevScene.getWindow();
                stage.close();
            }
        });

    }

    public void setPrevScene(Scene scene){
        this.prevScene = scene;
    }
}
