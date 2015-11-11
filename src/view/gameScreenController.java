package view;
import main.Main;
import model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
/**
 * GameScreenController created by Shannor.
 * Controller class for the starting gameScreen
 * Takes the information for num of players, difficulty, and mapChoice
 */
public class GameScreenController {
    /**
     * Continue button.
     */
    @FXML
    private Button cntButton;
    /**
     * Slider for choosing number of players.
     */
    @FXML
    private Slider numPlayerSlider;
    /**
     * Choice box for map.
     */
    @FXML
    private ChoiceBox<String> cmbMapChoice;
    /**
     * Testing button.
     */
    @FXML
    private Button btnForTesting;

    /**
     * Instance of Main.
     */
    private Main main;

    /**
     * Empty constructor for GameScreenController.
     */
    public GameScreenController() {
    }
    /**
     * Initializes the controller.
     */
    @FXML
    private void initialize() {
        numPlayerSlider.setValue(2);
        cmbMapChoice.getItems().addAll(
                "Default",
                "Normal",
                "Random"
        );
        cmbMapChoice.getSelectionModel().select(0);
        cntButton.setOnAction(event -> {
            //Return number of players to main
            main.setPlayerCount((int) numPlayerSlider.getValue());
            //Return map choice to main
            main.setMapChoice(getMapChoice());
            main.showPlayerTraitScreen();
        });

        btnForTesting.setOnAction(event -> {
            main.setPlayerCount(2);
            main.addPlayer(new Player("Player1", "Human", "Blue"));
            main.addPlayer(new Player("Player2", "Human", "Yellow"));
            main.showMapScreen();
        });
    }
    /**
     * Goes to main map.
     * @return mapChoice.
     */
    @FXML
    private String getMapChoice() {
        return cmbMapChoice.getValue();
    }
    /**
     * Goes to main map.
     * @param mainApp goes to mainApp.
     */
    public final void setMainApp(final Main mainApp) {
        this.main = mainApp;
    }
}
