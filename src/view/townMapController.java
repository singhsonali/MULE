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
 * Created by Ashley on 10/1/2015.
 */
public class townMapController {

    @FXML
    private Label lblTimer;

    private GameTimer timer;

    public void initialize() {

        /*timer = new GameTimer(timer.getTime());
        timer.setLabel(lblTimer);
        timer.startTimer(); */

    }
}
