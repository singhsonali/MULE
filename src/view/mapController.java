package view;

import main.Main;
import model.*;
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

import model.Player;

/**
 * Created by Melanie Smith on 9/20/2015.
 */
public class mapController {


    private Scene prevScene; // Player traits
    private Scene currentScene; //Map

    private Main main;

    private ObservableList<Player> tempPlayers;
    private Player currentPlayer;
    private Timer timer;
    private Map tempMap;
    private int numPlayers = 0;
    //-1 for error checking
    private int column = -1;
    private int row = -1;
    private boolean landSelectionFinished = false;
    private Pane currentPane;
    private int skips = 0; //Counts the number of skips
    private Round round;
    private mapController controller;
    private Stage primaryStage;
    private GameTimer currentTimer;
    private boolean mulePhase;
    private storeController storeController;
    private boolean landPhaseSkipped = false;

    @FXML
    private Pane townPane;

    @FXML
    private Pane pane00;

    @FXML
    private Pane pane01;

    @FXML
    private Pane pane03;

    @FXML
    private Pane pane05;

    @FXML
    private Pane pane07;

    @FXML
    private Pane pane08;

    @FXML
    private Pane pane10;

    @FXML
    private Pane pane12;

    @FXML
    private Pane pane13;

    @FXML
    private Pane pane15;

    @FXML
    private Pane pane16;

    @FXML
    private Pane pane17;

    @FXML
    private Pane pane21;

    @FXML
    private Pane pane22;

    @FXML
    private Pane pane23;

    @FXML
    private Pane pane25;

    @FXML
    private Pane pane26;

    @FXML
    private Pane pane27;

    @FXML
    private Pane pane30;

    @FXML
    private Pane pane32;

    @FXML
    private Pane pane33;

    @FXML
    private Pane pane35;

    @FXML
    private Pane pane37;

    @FXML
    private Pane pane38;

    @FXML
    private Pane pane40;

    @FXML
    private Pane pane41;

    @FXML
    private Pane pane43;

    @FXML
    private Pane pane45;

    @FXML
    private Pane pane46;

    @FXML
    private Pane pane47;

    @FXML
    private Pane pane04;

    @FXML
    private Pane pane14;

    @FXML
    private Pane pane34;

    @FXML
    private Pane pane44;

    @FXML
    private Pane pane02;

    @FXML
    private Pane pane11;

    @FXML
    private Pane pane20;

    @FXML
    private Pane pane31;

    @FXML
    private Pane pane42;

    @FXML
    private Pane pane06;

    @FXML
    private Pane pane18;

    @FXML
    private Pane pane28;

    @FXML
    private Pane pane36;

    @FXML
    private Pane pane48;

    private Pane[] panes = {pane00, pane01, pane02, pane03, pane04, pane05, pane06, pane07, pane08,
            pane10, pane11, pane12, pane13, pane14, pane15, pane16, pane17, pane18,
            pane20, pane21, pane22, pane23, /*townpane*/ pane25, pane26, pane27, pane28,
            pane30, pane31, pane32, pane33, pane34, pane35, pane36, pane37, pane38,
            pane40, pane41, pane42, pane43, pane44, pane45, pane46, pane47, pane48};

    @FXML
    private Label lblPlayerName;

    @FXML
    private Button btnContinue;

    @FXML
    private Button btnSkip;

    @FXML
    private Label lblInstructions;


    public mapController(){

    }

    private Timeline timeline;


    @FXML
    private void initialize(){

    }

    @FXML
    public void openTown(){
        //Provide conditional when this method can work.
        //ie if(land purchases are done)
        if(landSelectionFinished) {
            //round.nextRound();
            round.randEvent(tempPlayers, tempPlayers.get(0));
            main.setReturningFromStore(false);
            main.showTownScreen();
            //main.updateRound(round);
            currentPlayer = tempPlayers.get(0);
            main.setCurrentPlayer(currentPlayer);
        }
    }

    @FXML
    public void skipTurn() {
        skips++;
        if (skips == tempPlayers.size()) {
            System.out.println("All players skipped. Land selection phase ended.");
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
    @FXML
    public void playersSelectLand() {
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
                    } else if (currentPlayer.getMoney() >= chosenLand.getCost()) {
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
                    System.out.println("Land is already owned. Pick another piece of land or skip.");
                }
            } else {
                //All Players have finished buying land or skipping
                //Start turns
                skips = 0;
                System.out.println("Start Town Game");
            }
        }
    }

    public void updatePlayer(){
        currentPlayer = tempPlayers.get(numPlayers);
        updatePlayerLabel();
    }

    public void updatePlayerLabel() {
        this.lblPlayerName.setText(currentPlayer.getName());
    }

    public void updateCurrentPane(Pane pane) {
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

    public void setInterfaceInvis(boolean bool){
        btnContinue.visibleProperty().setValue(bool);
        btnSkip.visibleProperty().setValue(bool);
        lblInstructions.setText("Go to the Town");
        lblPlayerName.setText(tempPlayers.get(0).getName());
    }

    public void setLandPhaseSkipped(boolean bool) {
        this.landPhaseSkipped = bool;
    }
    public void setStoreController(storeController storeController) {
        this.storeController = storeController;
    }
    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    public void setPrevScene(Scene scene){
        this.prevScene = scene;
    }
    public void getMap(Map map){
        this.tempMap = map;
    }

    public void sortPlayers(){
        FXCollections.sort(this.tempPlayers, new PlayerComparator());
    }
    public void setPlayerData(ObservableList<Player> player){
        this.tempPlayers = player;
        sortPlayers();
        currentPlayer = tempPlayers.get(0);
        this.lblPlayerName.setText(currentPlayer.getName());
    }

    public void setController(mapController controller){
        this.controller = controller;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public void setLandSelectionFinished(boolean bool) {
        this.landSelectionFinished = bool;
        if (bool) {
            btnContinue.visibleProperty().setValue(false);
            btnSkip.visibleProperty().setValue(false);
            lblInstructions.setText("Go to the Town");
        }
    }

    //Save function
    public void saveGame(){
        main.saveGame();
    }

    //Load Function
    public void loadGame(){
        main.loadGame();
    }



    public static class PlayerComparator implements Comparator<Player> {

        public int compare(Player a, Player b){
            int aScore = a.calcScore();
            int bScore = b.calcScore();
            if(aScore > bScore){
                return 1;
            }else if(aScore < bScore){
                return -1;
            }else{
                return 0;
            }
        }
    }

    public void setMulePhaseLabels() {
        lblPlayerName.setText(currentPlayer.getName());
        lblInstructions.setText("Select Where to Place Mule");
        btnContinue.setVisible(false);
        btnSkip.setVisible(false);
    }

    //Will be the action of an onMouseClick
    public void placeOreMule() {
        if (tempMap.getLand(row, column).getPlayer().equals(currentPlayer) && !tempMap.getLand(row, column).hasMule()) {
            tempMap.getLand(row, column).setOreMule(1);
            currentPlayer.setHoldingMule(null);
            storeController.leaveStore();
        } else if (tempMap.getLand(row, column).getPlayer().equals(currentPlayer) && tempMap.getLand(row, column).hasMule()) {
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

    //Will be the action of an onMouseClick
    public void placeEnergyMule() {
        if (tempMap.getLand(row, column).getPlayer().equals(currentPlayer) && !tempMap.getLand(row, column).hasMule()) {
            tempMap.getLand(row, column).setEnergyMule(1);
            currentPlayer.setHoldingMule(null);
            storeController.leaveStore();
        } else if (tempMap.getLand(row, column).getPlayer().equals(currentPlayer) && tempMap.getLand(row, column).hasMule()) {
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

    //Will be the action of an onMouseClick
    public void placeFoodMule() {
        if (tempMap.getLand(row, column).getPlayer().equals(currentPlayer) && !tempMap.getLand(row, column).hasMule()) {
            tempMap.getLand(row, column).setFoodMule(1);
            currentPlayer.setHoldingMule(null);
            storeController.leaveStore();
        } else if (tempMap.getLand(row, column).getPlayer().equals(currentPlayer) && tempMap.getLand(row, column).hasMule()) {
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

    public void setRound(Round round){
        this.round = round;
    }

    //Ugly method of connecting Map Land's object to gridPane pane's
    public void connectMapWithPanes(){
        tempMap.getLand(0,0).setMyPane(pane00);
        tempMap.getLand(0,1).setMyPane(pane01);
        tempMap.getLand(0,2).setMyPane(pane02);
        tempMap.getLand(0,3).setMyPane(pane03);
        tempMap.getLand(0,4).setMyPane(pane04);
        tempMap.getLand(0,5).setMyPane(pane05);
        tempMap.getLand(0,6).setMyPane(pane06);
        tempMap.getLand(0,7).setMyPane(pane07);
        tempMap.getLand(0,8).setMyPane(pane08);

        tempMap.getLand(1,0).setMyPane(pane10);
        tempMap.getLand(1,1).setMyPane(pane11);
        tempMap.getLand(1,2).setMyPane(pane12);
        tempMap.getLand(1,3).setMyPane(pane13);
        tempMap.getLand(1,4).setMyPane(pane14);
        tempMap.getLand(1,5).setMyPane(pane15);
        tempMap.getLand(1,6).setMyPane(pane16);
        tempMap.getLand(1,7).setMyPane(pane17);
        tempMap.getLand(1,8).setMyPane(pane18);

        tempMap.getLand(2,0).setMyPane(pane20);
        tempMap.getLand(2,1).setMyPane(pane21);
        tempMap.getLand(2,2).setMyPane(pane22);
        tempMap.getLand(2,3).setMyPane(pane23);
        tempMap.getLand(2,5).setMyPane(pane25);
        tempMap.getLand(2,6).setMyPane(pane26);
        tempMap.getLand(2,7).setMyPane(pane27);
        tempMap.getLand(2,8).setMyPane(pane28);

        tempMap.getLand(3,0).setMyPane(pane30);
        tempMap.getLand(3,1).setMyPane(pane31);
        tempMap.getLand(3,2).setMyPane(pane32);
        tempMap.getLand(3,3).setMyPane(pane33);
        tempMap.getLand(3,4).setMyPane(pane34);
        tempMap.getLand(3,5).setMyPane(pane35);
        tempMap.getLand(3,6).setMyPane(pane36);
        tempMap.getLand(3,7).setMyPane(pane37);
        tempMap.getLand(3,8).setMyPane(pane38);

        tempMap.getLand(4,0).setMyPane(pane40);
        tempMap.getLand(4,1).setMyPane(pane41);
        tempMap.getLand(4,2).setMyPane(pane42);
        tempMap.getLand(4,3).setMyPane(pane43);
        tempMap.getLand(4,4).setMyPane(pane44);
        tempMap.getLand(4,5).setMyPane(pane45);
        tempMap.getLand(4,6).setMyPane(pane46);
        tempMap.getLand(4,7).setMyPane(pane47);
        tempMap.getLand(4,8).setMyPane(pane48);
    }

    public void updateColor(Pane pane){
        //Sets the Land border to player color
        //Temp[0] hold the original background
        String temp[] = pane.getStyle().split(";");
        pane.setStyle(temp[0] + ";" + "-fx-border-color: " + currentPlayer.getColor());
    }
    public void revertColor(Pane pane){
        //Set back to original color
        String temp[] = pane.getStyle().split(";");
        pane.setStyle(temp[0] + ";" + "-fx-border-color: Black");
    }

    public void placeMule() {
        if (currentPlayer.getHoldingMule().equals("Energy")) {
            placeEnergyMule();
        } else if (currentPlayer.getHoldingMule().equals("Food")) {
            placeFoodMule();
        } else {
            placeOreMule();
        }
    }

    @FXML
    public void setLandChoice00(){
        this.column = 0;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
        System.out.println("Mules: " + tempMap.getLand(row,column).getEnergyMule());
    }
    @FXML
    public void setLandChoice01() {
        this.column = 1;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice02() {
        this.column = 2;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice03() {
        this.column = 3;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice04() {
        this.column = 4;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice05() {
        this.column = 5;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice06() {
        this.column = 6;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice07() {
        this.column = 7;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice08() {
        this.column = 8;
        this.row = 0;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice10(){
        this.column = 0;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice11() {
        this.column = 1;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice12() {
        this.column = 2;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice13() {
        this.column = 3;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice14() {
        this.column = 4;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice15() {
        this.column = 5;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice16() {
        this.column = 6;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice17() {
        this.column = 7;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice18() {
        this.column = 8;
        this.row = 1;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice20(){
        this.column = 0;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice21() {
        this.column = 1;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice22() {
        this.column = 2;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice23() {
        this.column = 3;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice25() {
        this.column = 5;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice26() {
        this.column = 6;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice27() {
        this.column = 7;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice28() {
        this.column = 8;
        this.row = 2;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice30(){
        this.column = 0;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice31() {
        this.column = 1;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice32() {
        this.column = 2;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice33() {
        this.column = 3;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice34() {
        this.column = 4;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice35() {
        this.column = 5;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice36() {
        this.column = 6;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice37() {
        this.column = 7;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice38() {
        this.column = 8;
        this.row = 3;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice40(){
        this.column = 0;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice41() {
        this.column = 1;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice42() {
        this.column = 2;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice43() {
        this.column = 3;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice44() {
        this.column = 4;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice45() {
        this.column = 5;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice46() {
        this.column = 6;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice47() {
        this.column = 7;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice48() {
        this.column = 8;
        this.row = 4;
        if (currentPlayer.getHoldingMule() != null) {
            placeMule();
        } else if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    public void getStage(Stage stage){
        this.primaryStage  = stage;
    }
    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    public void setCurrentScene(Scene scene){
        this.currentScene = scene;
    }
    public void setCurrentTimer(GameTimer timer){
        this.currentTimer = timer;
    }
    public void setMulePhase(boolean mulePhase) {
        this.mulePhase = mulePhase;
    }
}
