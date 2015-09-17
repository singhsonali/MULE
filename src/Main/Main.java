package Main;

import View.gameScreenController;
import View.playerTraitController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Scene currentScene;

    //Vars needed
    //Player Conatiner
    //Difficulty choice
    //Map Choice
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MULE");

       //initRootLayout();
        showGameScreen();
    }
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../View/menuRoot.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showGameScreen() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/gameScreen1.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout
            //rootLayout.getChildren().setAll(personOverview);

            Scene scene = new Scene(personOverview);
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            //Gets Controller that preforms actions to the scene
            gameScreenController controller = loader.getController();
            controller.setPrevScene(currentScene);
            loader.setController(controller);
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPlayerTraitScreen(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/playerTraits.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout
            //rootLayout.getChildren().setAll(personOverview);

            Scene scene = new Scene(personOverview);
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

            playerTraitController controller = loader.getController();
            controller.setPrevScene(currentScene);
            loader.setController(controller);
            controller.setMainApp(this);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showMapScreen(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/MapScence.fxml"));
            AnchorPane mapScreen= (AnchorPane) loader.load();

            // Set person overview into the center of root layout
            //rootLayout.getChildren().setAll(personOverview);

            Scene scene = new Scene(mapScreen);
            currentScene = scene;
            primaryStage.setScene(scene);
            primaryStage.show();

//            playerTraitController controller = loader.getController();
//            controller.setPrevScene(currentScene);
//            loader.setController(controller);
//            controller.setMainApp(this);

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
