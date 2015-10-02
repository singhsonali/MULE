package View;

import Main.Main;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Created by Ashley on 9/30/2015.
 */
public class pubScreenController {

    @FXML
    private Button btnGamble;

    private Main main;
    private Scene prevScene;

    private void initialize() {
        //When continue button is pressed
        btnGamble.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

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
