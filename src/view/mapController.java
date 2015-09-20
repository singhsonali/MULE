package view;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Melanie Smith on 9/20/2015.
 */
public class mapController {

    @FXML
    private Pane townPane;

    private Scene prevScene;

    private Main main;

    public mapController(){}

    @FXML
    private void intialize(){
        System.out.println("MouseEvent");
        townPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                main.showTownScreen();
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
