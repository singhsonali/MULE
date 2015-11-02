package view;

import Main.Main;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.Comparator;

import Model.Player;

/**
 * Created by Melanie Smith on 9/20/2015.
 */
public class mapController {

    /**
     * Scene that asks player traits.
     */
    private Scene prevScene; // Player traits

    /**
     * The current Scene.
     */
    private Scene currentScene; //Map

    /**
     * Creates instance of main.
     */
    private Main main;

    /**
     * List of current players.
     */
    private ObservableList<Player> tempPlayers;

    /**
     * The current Player.
     */
    private Player currentPlayer;

    /**
     * Timer for the game.
     */
    private Timer timer;

    /**
     * Current Map.
     */
    private Map tempMap;

    /**
     * The number of players created.
     */
    private int numPlayers = 0;
    //-1 for error checking

    /**
     * The column on the land Map.
     */
    private int column = -1;

    /**
     * The row on the land Map.
     */
    private int row = -1;

    /**
     * Whether the Land Selection phase has been finished.
     */
    private boolean landSelectionFinished = false;

    /**
     * The current Pane on the map.
     */
    private Pane currentPane;

    /**
     * counts the number of skips.
     */
    private int skips = 0; //Counts the number of skips

    /**
     * The current round.
     */
    private Round round;

    /**
     * controller for the map.
     */
    private mapController controller;

    /**
     * Stage for game UI.
     */
    private Stage primaryStage;

    /**
     * Timer for the current round.
     */
    private GameTimer currentTimer;

    /**
     * Mule Phase.
     */
    private boolean mulePhase;

    /**
     * Controller for the game store.
     */
    private storeController storeController;

    /**
     * Whether the land phase was skipped.
     */
    private boolean landPhaseSkipped = false;

    /**
     * Location for the town.
     */
    @FXML
    private Pane townPane;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane00;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane01;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane03;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane05;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane07;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane08;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane10;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane12;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane13;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane15;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane16;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane17;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane21;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane22;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane23;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane25;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane26;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane27;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane30;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane32;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane33;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane35;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane37;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane38;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane40;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane41;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane43;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane45;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane46;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane47;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane04;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane14;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane34;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane44;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane02;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane11;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane20;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane31;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane42;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane06;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane18;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane28;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane36;

    /**
     * Map location pane.
     */
    @FXML
    private Pane pane48;

    /**
     * Array for all the map location panes.
     */
    private Pane[] panes = {pane00, pane01, pane02, pane03,
            pane04, pane05, pane06, pane07, pane08,
            pane10, pane11, pane12, pane13, pane14, pane15,
            pane16, pane17, pane18, pane20, pane21, pane22,
            pane23, /*townpane*/ pane25, pane26, pane27, pane28,
            pane30, pane31, pane32, pane33, pane34, pane35,
            pane36, pane37, pane38, pane40, pane41, pane42,
            pane43, pane44, pane45, pane46, pane47, pane48};

    /**
     * Label for the player name.
     */
    @FXML
    private Label lblPlayerName;

    /**
     * Button to continue.
     */
    @FXML
    private Button btnContinue;

    /**
     * Button to skip.
     */
    @FXML
    private Button btnSkip;

    /**
     * Label for the instructions.
     */
    @FXML
    private Label lblInstructions;

    /**
     * Constructor for mapController.
     */
    public mapController() {

    }

    /**
     * Timeline for game.
     */
    private Timeline timeline;


    /**
     * initializes panes.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Shows the town screen.
     */
    @FXML
    public final void openTown() {
        //Provide conditional when this method can work.
        //ie if(land purchases are done)
        if (landSelectionFinished) {
            //round.nextRound();
            round.randEvent(tempPlayers, tempPlayers.get(0));
            main.setReturningFromStore(false);
            main.showTownScreen();
            //main.updateRound(round);
            currentPlayer = tempPlayers.get(0);
            main.setCurrentPlayer(currentPlayer);
        }
    }

    /**
     * Skips player's turn.
     */
    @FXML
    public final void skipTurn() {
        skips++;
        if (skips == tempPlayers.size()) {
            System.out.println("All players skipped. "
                    + "Land selection phase ended.");
            landSelectionFinished = true;
            main.setLandPhaseSkipped(true);
            setInterfaceInvis(false);
        } else if (!landSelectionFinished) {
            numPlayers++;
            if (numPlayers < tempPlayers.size()) {
                updatePlayer();
            } else {
                //Done with buying land
                //Start Turns
                landSelectionFinished = true;
                setInterfaceInvis(false);
                System.out.println("Finished buying Land.");
                skips = 0;
            }
        }
    }

    /**
     * Details the land selection phase for the players.
     */
    @FXML
    public final void playersSelectLand() {
        if (!landSelectionFinished && !landPhaseSkipped) {
            if (numPlayers < tempPlayers.size()) {
                //Go through land procedure
                //If land is not owned
                if (tempMap.getLand(row, column).isOpen()) {
                    Land chosenLand = tempMap.getLand(row, column);
                    if (currentPlayer.haveLandGrants()) {
                        currentPlayer.useLandGrant();
                        chosenLand.setOpen(false);
                        currentPlayer.addLand(chosenLand);
                        chosenLand.setPlayerOwner(currentPlayer);
                        currentPane = null;
                        updateColor(chosenLand.getMyPane());
                        //If there is another player
                        numPlayers++;
                        if (numPlayers < tempPlayers.size()) {
                            updatePlayer();
                        } else {
                            landSelectionFinished = true;
                            setInterfaceInvis(false);
                            skips = 0;
                        }
                    } else if (currentPlayer.getMoney()
                            >= chosenLand.getCost()) {
                        //Has enough money but not any landGrants
                        chosenLand.setOpen(false);
                        currentPlayer.addLand(chosenLand);
                        chosenLand.setPlayerOwner(currentPlayer);
                        currentPane = null;
                        currentPlayer.subtractMoney(chosenLand.getCost());
                        updateColor(chosenLand.getMyPane());
                        //If there is another player
                        numPlayers++;
                        if (numPlayers < tempPlayers.size()) {
                            updatePlayer();
                        } else {
                            landSelectionFinished = true;
                            setInterfaceInvis(false);
                            skips = 0;
                        }
                    } else {
                        //Not enough money or any Land Grants
                        System.out.println("Player can't afford this land.");
                    }
                } else {
                    System.out.println("Land is already owned. "
                            + "Pick another piece of land or skip.");
                }
            } else {
                //All Players have finished buying land or skipping
                //Start turns
                skips = 0;
                System.out.println("Start Town Game");
            }
        }
    }

    /**
     * updates the current player.
     */
    public final void updatePlayer() {
        currentPlayer = tempPlayers.get(numPlayers);
        updatePlayerLabel();
    }

    /**
     * Updates the player label to the current player.
     */
    public final void updatePlayerLabel() {
        this.lblPlayerName.setText(currentPlayer.getName());
    }

    /**
     * Changes the current map pane.
     *
     * @param pane The pane to be the new current pane.
     */
    public final void updateCurrentPane(final Pane pane) {
        //Error Checking
        if (!landSelectionFinished) {
            if (currentPane == pane) {
                //Already current pane
                return;
            }
            if (currentPane == null) {
                currentPane = pane;
                updateColor(pane);
            } else {
                revertColor(currentPane);
                currentPane = pane;
                updateColor(pane);
            }
        }
    }

    /**
     * Changes whether the interface is visible.
     *
     * @param bool Represents whether the interface should be visible
     */
    public final void setInterfaceInvis(final boolean bool) {
        btnContinue.visibleProperty().setValue(bool);
        btnSkip.visibleProperty().setValue(bool);
        lblInstructions.setText("Go to the Town");
        lblPlayerName.setText(tempPlayers.get(0).getName());
    }

    /**
     * Whether the set Land Phase is to be skipped.
     *
     * @param bool represents whether the set land phase should be skipped.
     */
    public final void setLandPhaseSkipped(final boolean bool) {
        this.landPhaseSkipped = bool;
    }

    /**
     * Sets the controller for the store.
     *
     * @param storeController controller for the store
     */
    public final void setStoreController(
            final storeController storeController) {
        this.storeController = storeController;
    }

    /**
     * Sets the main application for the game.
     *
     * @param mainApp main application for the game
     */
    public final void setMainApp(final Main mainApp) {
        this.main = mainApp;
    }

    /**
     * Sets the previous scene.
     *
     * @param scene The scene to be set as the previous scene.
     */
    public final void setPrevScene(final Scene scene) {
        this.prevScene = scene;
    }

    /**
     * Gets the current Map.
     *
     * @param map the current map.
     */
    public final void getMap(final Map map) {
        this.tempMap = map;
    }

    /**
     * Sorts the list of players.
     */
    public final void sortPlayers() {
        FXCollections.sort(this.tempPlayers, new PlayerComparator());
    }

    /**
     * Sets the data for the player.
     *
     * @param player The player whose data is being set.
     */
    public final void setPlayerData(final ObservableList<Player> player) {
        this.tempPlayers = player;
        sortPlayers();
        currentPlayer = tempPlayers.get(0);
        this.lblPlayerName.setText(currentPlayer.getName());
    }

    /**
     * Sets the map controller for the game.
     *
     * @param controller the map controller for the game.
     */
    public final void setController(final mapController controller) {
        this.controller = controller;
    }

    /**
     * Sets the current player.
     *
     * @param player the player to be set as the current player.
     */
    public final void setCurrentPlayer(final Player player) {
        this.currentPlayer = player;
    }

    /**
     * Completion of the land selaection phase.
     *
     * @param bool represents whether the land selection phase is completed
     */
    public final void setLandSelectionFinished(final boolean bool) {
        this.landSelectionFinished = bool;
        if (bool) {
            btnContinue.visibleProperty().setValue(false);
            btnSkip.visibleProperty().setValue(false);
            lblInstructions.setText("Go to the Town");
        }
    }

    /**
     * Compares the players by their score.
     */
    public static class PlayerComparator implements Comparator<Player> {

        /**
         * compares the scores of two players.
         *
         * @param a first player.
         * @param b second player.
         * @return 1 if first player's score is higher.
         */
        public final int compare(final Player a, final Player b) {
            int aScore = a.calcScore();
            int bScore = b.calcScore();
            if (aScore > bScore) {
                return 1;
            } else if (aScore < bScore) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Sets the phase labels for the Mules.
     */
    public final void setMulePhaseLabels() {
        lblPlayerName.setText(currentPlayer.getName());
        lblInstructions.setText("Select Where to Place Mule");
        btnContinue.setVisible(false);
        btnSkip.setVisible(false);
    }

    /**
     * Places an Ore Mule.
     */
    public final void placeOreMule() {
        if (tempMap.getLand(row, column).getPlayer().equals(currentPlayer)
                && !tempMap.getLand(row, column).hasMule()) {
            tempMap.getLand(row, column).setOreMule(1);
            currentPlayer.setHoldingMule(null);
            storeController.leaveStore();
        } else if (tempMap.getLand(row, column)
                .getPlayer().equals(currentPlayer)
                && tempMap.getLand(row, column).hasMule()) {
            String muleType = tempMap.getLand(row, column).getMuleType();
            tempMap.getLand(row, column).clearMule();
            currentPlayer.removeMule(muleType);
            currentPlayer.setOreMule(currentPlayer.getOreMule() + 1);
            currentPlayer.setHoldingMule(muleType);
            tempMap.getLand(row, column).setOreMule(1);
            currentPlayer.setHoldingMule(null);
        } else {
            currentPlayer.setOreMule(currentPlayer.getOreMule() - 1);
        }
    }

    /**
     * Places an energy mule.
     */
    public final void placeEnergyMule() {
        if (tempMap.getLand(row, column).getPlayer().equals(currentPlayer)
                && !tempMap.getLand(row, column).hasMule()) {
            tempMap.getLand(row, column).setEnergyMule(1);
            currentPlayer.setHoldingMule(null);
            storeController.leaveStore();
        } else if (tempMap.getLand(row, column)
                .getPlayer().equals(currentPlayer)
                && tempMap.getLand(row, column).hasMule()) {
            tempMap.getLand(row, column).clearMule();
            String muleType = tempMap.getLand(row, column).getMuleType();
            currentPlayer.removeMule(muleType);
            currentPlayer.setEnergyMule(currentPlayer.getEnergyMule() + 1);
            currentPlayer.setHoldingMule(muleType);
            tempMap.getLand(row, column).setOreMule(1);
            currentPlayer.setHoldingMule(null);
        } else {
            currentPlayer.setEnergyMule(currentPlayer.getEnergyMule() - 1);
        }
    }

    /**
     * Places a food mule.
     */
    public final void placeFoodMule() {
        if (tempMap.getLand(row, column).getPlayer().equals(currentPlayer)
                && !tempMap.getLand(row, column).hasMule()) {
            tempMap.getLand(row, column).setFoodMule(1);
            currentPlayer.setHoldingMule(null);
            storeController.leaveStore();
        } else if (tempMap.getLand(row, column)
                .getPlayer().equals(currentPlayer)
                && tempMap.getLand(row, column).hasMule()) {
            tempMap.getLand(row, column).clearMule();
            String muleType = tempMap.getLand(row, column).getMuleType();
            currentPlayer.removeMule(muleType);
            currentPlayer.setFoodMule(currentPlayer.getFoodMule() + 1);
            currentPlayer.setHoldingMule(muleType);
            tempMap.getLand(row, column).setFoodMule(1);
            currentPlayer.setHoldingMule(null);
        } else {
            currentPlayer.setFoodMule(currentPlayer.getFoodMule() - 1);
        }
    }

    /**
     * Sets the currents round.
     *
     * @param round the current round.
     */
    public final void setRound(final Round round) {
        this.round = round;
    }

    /**
     * connects Map Land's object to gridPane pane's.
     */
    public final void connectMapWithPanes() {
        tempMap.getLand(0, 0).setMyPane(pane00);
        tempMap.getLand(0, 1).setMyPane(pane01);
        tempMap.getLand(0, 2).setMyPane(pane02);
        tempMap.getLand(0, 3).setMyPane(pane03);
        tempMap.getLand(0, 4).setMyPane(pane04);
        tempMap.getLand(0, 5).setMyPane(pane05);
        tempMap.getLand(0, 6).setMyPane(pane06);
        tempMap.getLand(0, 7).setMyPane(pane07);
        tempMap.getLand(0, 8).setMyPane(pane08);

        tempMap.getLand(1, 0).setMyPane(pane10);
        tempMap.getLand(1, 1).setMyPane(pane11);
        tempMap.getLand(1, 2).setMyPane(pane12);
        tempMap.getLand(1, 3).setMyPane(pane13);
        tempMap.getLand(1, 4).setMyPane(pane14);
        tempMap.getLand(1, 5).setMyPane(pane15);
        tempMap.getLand(1, 6).setMyPane(pane16);
        tempMap.getLand(1, 7).setMyPane(pane17);
        tempMap.getLand(1, 8).setMyPane(pane18);

        tempMap.getLand(2, 0).setMyPane(pane20);
        tempMap.getLand(2, 1).setMyPane(pane21);
        tempMap.getLand(2, 2).setMyPane(pane22);
        tempMap.getLand(2, 3).setMyPane(pane23);
        tempMap.getLand(2, 5).setMyPane(pane25);
        tempMap.getLand(2, 6).setMyPane(pane26);
        tempMap.getLand(2, 7).setMyPane(pane27);
        tempMap.getLand(2, 8).setMyPane(pane28);

        tempMap.getLand(3, 0).setMyPane(pane30);
        tempMap.getLand(3, 1).setMyPane(pane31);
        tempMap.getLand(3, 2).setMyPane(pane32);
        tempMap.getLand(3, 3).setMyPane(pane33);
        tempMap.getLand(3, 4).setMyPane(pane34);
        tempMap.getLand(3, 5).setMyPane(pane35);
        tempMap.getLand(3, 6).setMyPane(pane36);
        tempMap.getLand(3, 7).setMyPane(pane37);
        tempMap.getLand(3, 8).setMyPane(pane38);

        tempMap.getLand(4, 0).setMyPane(pane40);
        tempMap.getLand(4, 1).setMyPane(pane41);
        tempMap.getLand(4, 2).setMyPane(pane42);
        tempMap.getLand(4, 3).setMyPane(pane43);
        tempMap.getLand(4, 4).setMyPane(pane44);
        tempMap.getLand(4, 5).setMyPane(pane45);
        tempMap.getLand(4, 6).setMyPane(pane46);
        tempMap.getLand(4, 7).setMyPane(pane47);
        tempMap.getLand(4, 8).setMyPane(pane48);
    }

    /**
     * Updates the color of the map pane.
     *
     * @param pane the map pane to be altered.
     */
    public final void updateColor(final Pane pane) {
        //Sets the Land border to player color
        //Temp[0] hold the original background
        String[] temp = pane.getStyle().split(";");
        pane.setStyle(temp[0] + ";" + "-fx-border-color: "
                + currentPlayer.getColor());
    }

    /**
     * Sets the pane's color back to its original color.
     *
     * @param pane the pane to be altered.
     */
    public final void revertColor(final Pane pane) {
        //Set back to original color
        String[] temp = pane.getStyle().split(";");
        pane.setStyle(temp[0] + ";" + "-fx-border-color: Black");
    }

    /**
     * Places a mule.
     */
    public final void placeMule() {
        if (currentPlayer.getHoldingMule().equals("Energy")) {
            placeEnergyMule();
        } else if (currentPlayer.getHoldingMule().equals("Food")) {
            placeFoodMule();
        } else {
            placeOreMule();
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice00() {
        this.column = 0;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
        System.out.println("Mules: "
                + tempMap.getLand(row, column).getEnergyMule());
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice01() {
        this.column = 1;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice02() {
        this.column = 2;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice03() {
        this.column = 3;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice04() {
        this.column = 4;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice05() {
        this.column = 5;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice06() {
        this.column = 6;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice07() {
        this.column = 7;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice08() {
        this.column = 8;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice10() {
        this.column = 0;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice11() {
        this.column = 1;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice12() {
        this.column = 2;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice13() {
        this.column = 3;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice14() {
        this.column = 4;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice15() {
        this.column = 5;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice16() {
        this.column = 6;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice17() {
        this.column = 7;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice18() {
        this.column = 8;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice20() {
        this.column = 0;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice21() {
        this.column = 1;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice22() {
        this.column = 2;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice23() {
        this.column = 3;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice25() {
        this.column = 5;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice26() {
        this.column = 6;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice27() {
        this.column = 7;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice28() {
        this.column = 8;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice30() {
        this.column = 0;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice31() {
        this.column = 1;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice32() {
        this.column = 2;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice33() {
        this.column = 3;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice34() {
        this.column = 4;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice35() {
        this.column = 5;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice36() {
        this.column = 6;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice37() {
        this.column = 7;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice38() {
        this.column = 8;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice40() {
        this.column = 0;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice41() {
        this.column = 1;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice42() {
        this.column = 2;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice43() {
        this.column = 3;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice44() {
        this.column = 4;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice45() {
        this.column = 5;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice46() {
        this.column = 6;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice47() {
        this.column = 7;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the specific land choice.
     */
    @FXML
    public final void setLandChoice48() {
        this.column = 8;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if (tempMap.getLand(row, column).isOpen()) {
            updateCurrentPane(tempMap.getLand(row, column).getMyPane());
        }
    }

    /**
     * Sets the primary stage.
     *
     * @param stage the stage to be set.
     */
    public final void getStage(final Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Gets the current player.
     *
     * @return the current player.
     */
    public final Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Sets the current scene.
     *
     * @param scene the current scene.
     */
    public final void setCurrentScene(final Scene scene) {
        this.currentScene = scene;
    }

    /**
     * Stes the current timer.
     *
     * @param timer the current timer.
     */
    public final void setCurrentTimer(final GameTimer timer) {
        this.currentTimer = timer;
    }

    /**
     * Sets the mule phase.
     *
     * @param mulePhase the mule phase.
     */
    public final void setMulePhase(final boolean mulePhase) {
        this.mulePhase = mulePhase;
    }
}
