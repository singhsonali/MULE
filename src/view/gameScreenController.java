package View;


import Main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class gameScreenController{

        @FXML
        private Slider playerSlider;

        @FXML
        private Slider difficultySlider;

        @FXML
        private Button cntButton;

        @FXML
        private ComboBox<String> cmbMapChoice;

        //Ref to main application
        private Main main;
        private Scene prevScene;
        //Constructor
        public gameScreenController(){

        }

    @FXML
    private void initialize(){
        cmbMapChoice.getItems().addAll(
                "Default",
                "Normal",
                "Random"
        );
        cntButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.showPlayerTraitScreen();
                Stage stage = (Stage) prevScene.getWindow();
                stage.close();
            }
        });
    }
    @FXML
    private void switchScreen(){
        //Call an function from main to open new Scene,
        //Close itself

    }
    public void setMainApp(Main mainApp) {
            this.main = mainApp;
    }

    public void setPrevScene(Scene scene){
        this.prevScene = scene;
    }


}
