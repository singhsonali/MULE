package View;


import Main.Main;
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
    //Ref to last scene visited
    private Scene prevScene;

    //Constructor
    public townController(){

    }

    @FXML
    private void initialize(){

        //When continue button is pressed
        pub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Return number of players to main
                //Return map choice to Main
                main.showPubScreen();
                //Closes this screen
                Stage stage = (Stage) prevScene.getWindow();
                stage.close();
            }
        });
    }
}
