package View;


import Main.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class gameScreenController{


    @FXML
    private Slider difficultySlider;

    @FXML
    private Button cntButton;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private ChoiceBox<String> cmbMapChoice;

    private int players;

    //Ref to main application
    private Main main;
    private Scene prevScene;
    //Constructor
    public gameScreenController(){

    }

    @FXML
    private void initialize(){

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Hello bud");
                players = 1;
            }
        });

        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("Number 2");
                players = 2;
                System.out.println(players);
            }
        });

        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //System.out.println("No");
                players = 3;
            }
        });

        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                players = 4;
            }
        });

        cmbMapChoice.getItems().addAll(
                "Default",
                "Normal",
                "Random"
        );
        cntButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.setPlayerCount(players);
                main.showPlayerTraitScreen();
                Stage stage = (Stage) prevScene.getWindow();
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
