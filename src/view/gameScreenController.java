package View;


import Main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

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

            }
        });

    }

    public void setMainApp(Main mainApp) {
        this.main = mainApp;

    }

}
