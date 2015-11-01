package view;

import Main.Main;
import model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
/**
 * Created by Shannor on 9/16/2015.
 * Controller for the PlayerTraits.fxml
 * Adds information for each player
 * Will lead to Map Screen afterwards
 */
public class PlayerTraitController {

    /**
     * Label in the game to display the name of the Player.
     */
    @FXML
    private transient Label lblPlayerNum;

    /**
     * Choice box that holds the color options.
     */
    @FXML
    private transient ChoiceBox<String> cmbColor;

    /**
     * Choice box to hold the Race Choices.
     */
    @FXML
    private transient ChoiceBox<String> cmbRace;

    /**
     * TextField to take in the input for Player name.
     */
    @FXML
    private transient TextField textName;

    /**
     * Button to create player and move to next player.
     * Or to continue to the game.
     */
    @FXML
    private transient Button cntButton;

    /**
     * Warning label displayed when person inputs incorrect name.
     * Or when no name is imputed.
     */
    @FXML
    private transient Label lblWarning;

    /**
     * Reference to Main to call functions in main and pass back information.
     */
    private transient Main main;

    /**
     * The amount of players chosen, passed in from main.
     */
    private transient int playerCount;

    /**
     * Keeps track of which Player Number currently on for displaying.
     */
    private transient int playerNum = 1;

    /**
     * Init function used by JavaFX.
     */
    @FXML
    private void initialize() {


        lblPlayerNum.setText("Player 1: Choose Your Options");

        cmbRace.getItems().addAll(
                "Human",
                "Flapper",
                "Other"
        );

        cmbColor.getItems().addAll(
                "Blue",
                "Yellow",
                "Green",
                "Orange"
        );

        cmbColor.getSelectionModel().select(0);
        cmbRace.getSelectionModel().select(0);
    }

    /**
     * Action taken on button click Continue.
     * Creates a player or displays an error.
     */
    @FXML
    public final void confirmChoices() {
        if (textName.getText().isEmpty()) {
            lblWarning.setText("Please enter a name.");
        } else {
            if (playerCount > 0) {
                //Gets the currently picked color
                final String pickedColor = cmbColor.getValue();
                //Create new player
                final Player tempPlayer = new Player(textName.getText(),
                        cmbRace.getValue(), cmbColor.getValue());
                main.addPlayer(tempPlayer); // Add new player
                //Remove picked Color from list
                cmbColor.getItems().remove(pickedColor);
                //Sets combo boxes back to zero index
                cmbColor.getSelectionModel().select(0);
                cmbRace.getSelectionModel().select(0);
                textName.clear();
                lblWarning.setText("");
                playerCount--; //Decrement player count after creation
                playerNum++; //Increment player Label number
                lblPlayerNum.setText("Player " + playerNum
                        + ": Choose Your Options");
            } else {
                //If just one player, and end case for last player
                final Player tempPlayer = new Player(textName.getText(),
                        cmbRace.getValue(), cmbColor.getValue());
                main.addPlayer(tempPlayer);
                //Display the map Screen to start game
                main.showMapScreen();

            }
        }
    }

    /**
     * Passes the reference of main to this controller.
     * @param mainApp main reference
     */
    @FXML
    public final void setMainApp(final Main mainApp) {
        this.main = mainApp;
    }

    /**
     * Sets the value of the PlayerCount variable.
     * @param count number of players
     */
    public final void setPlayerCount(final int count) {
        this.playerCount = count;
    }
}
