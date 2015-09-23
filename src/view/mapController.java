package View;
import Main.Main;
import Model.Land;
import Model.Map;
import Model.Player;
import com.sun.xml.internal.ws.api.FeatureConstructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Observable;

/**
 * Created by Melanie Smith on 9/20/2015.
 */
public class mapController {


    private Scene prevScene;

    private Main main;

    private ObservableList<Player> tempPlayers;
    private Player currentPlayer;
    private Map tempMap;
    private boolean next = false;
    private int numPlayers = 0;
    //-1 for error checking
    private int column =-1;
    private int row = -1;
    private boolean landSelectionFinished = false;
    private Pane currentPane;

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

    @FXML
    private void initialize(){
        currentPane = null;
    }

    @FXML
    public void openTown(){
        //Provide conditional when this method can work.
        //ie if(land purchases are done)
        if(landSelectionFinished) {
            main.showTownScreen();
            Stage stage = (Stage) prevScene.getWindow();
            stage.close();
        }
    }

    @FXML
    public void skipTurn() {
        if (!landSelectionFinished) {
            numPlayers++;
            if (numPlayers < tempPlayers.size()) {
                updatePlayer();
            } else {
                //Done with buying land
                //Start Turns
                landSelectionFinished = true;
                setInterfaceInvis(false);
                System.out.println("Finished buying Land.");
            }
        }
    }
    @FXML
    public void playersSelectLand() {
        if (!landSelectionFinished) {
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
                        }
                    } else if (currentPlayer.getMoney() >= 300) {
                        //Has enough money but not any landGrants
                        chosenLand.setOpen(false);
                        currentPlayer.addLand(chosenLand);
                        chosenLand.setPlayerOwner(currentPlayer);
                        currentPane = null;
                        currentPlayer.subtractMoney(300);
                        updateColor(chosenLand.getMyPane());
                        //If there is another player
                        numPlayers++;
                        if (numPlayers < tempPlayers.size()) {
                            updatePlayer();
                        } else {
                            landSelectionFinished = true;
                            setInterfaceInvis(false);
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
                System.out.println("Start Town Game");
            }

        }
    }
    public void updatePlayer(){
        currentPlayer = tempPlayers.get(numPlayers);
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
        lblInstructions.visibleProperty().setValue(bool);
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

    public void setPlayerData(ObservableList<Player> player){
        this.tempPlayers = FXCollections.observableArrayList(player);
        currentPlayer = tempPlayers.get(0);
        this.lblPlayerName.setText(currentPlayer.getName());
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
        pane.setStyle( temp[0]+ ";" + "-fx-border-color: "+currentPlayer.getColor());
    }
    public void revertColor(Pane pane){
        //Set back to original color
        String temp[] = pane.getStyle().split(";");
        pane.setStyle(temp[0] + ";" + "-fx-border-color: Black");
    }


    @FXML
    public void setLandChoice00(){
        this.column = 0;
        this.row = 0;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice01() {
        this.column = 1;
        this.row = 0;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice02() {
        this.column = 2;
        this.row = 0;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice03() {
        this.column = 3;
        this.row = 0;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice04() {
        this.column = 4;
        this.row = 0;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice05() {
        this.column = 5;
        this.row = 0;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice06() {
        this.column = 6;
        this.row = 0;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice07() {
        this.column = 7;
        this.row = 0;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice08() {
        this.column = 8;
        this.row = 0;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice10(){
        this.column = 0;
        this.row = 1;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice11() {
        this.column = 1;
        this.row = 1;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice12() {
        this.column = 2;
        this.row = 1;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice13() {
        this.column = 3;
        this.row = 1;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice14() {
        this.column = 4;
        this.row = 1;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice15() {
        this.column = 5;
        this.row = 1;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice16() {
        this.column = 6;
        this.row = 1;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice17() {
        this.column = 7;
        this.row = 1;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice18() {
        this.column = 8;
        this.row = 1;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice20(){
        this.column = 0;
        this.row = 2;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice21() {
        this.column = 1;
        this.row = 2;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice22() {
        this.column = 2;
        this.row = 2;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice23() {
        this.column = 3;
        this.row = 2;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice25() {
        this.column = 5;
        this.row = 2;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice26() {
        this.column = 6;
        this.row = 2;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice27() {
        this.column = 7;
        this.row = 2;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice28() {
        this.column = 8;
        this.row = 2;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice30(){
        this.column = 0;
        this.row = 3;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice31() {
        this.column = 1;
        this.row = 3;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice32() {
        this.column = 2;
        this.row = 3;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice33() {
        this.column = 3;
        this.row = 3;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice34() {
        this.column = 4;
        this.row = 3;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice35() {
        this.column = 5;
        this.row = 3;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice36() {
        this.column = 6;
        this.row = 3;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice37() {
        this.column = 7;
        this.row = 3;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice38() {
        this.column = 8;
        this.row = 3;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice40(){
        this.column = 0;
        this.row = 4;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice41() {
        this.column = 1;
        this.row = 4;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice42() {
        this.column = 2;
        this.row = 4;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice43() {
        this.column = 3;
        this.row = 4;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice44() {
        this.column = 4;
        this.row = 4;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice45() {
        this.column = 5;
        this.row = 4;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice46() {
        this.column = 6;
        this.row = 4;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice47() {
        this.column = 7;
        this.row = 4;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
    @FXML
    public void setLandChoice48() {
        this.column = 8;
        this.row = 4;
        if(tempMap.getLand(row,column).isOpen()){
            updateCurrentPane(tempMap.getLand(row,column).getMyPane());
        }
    }
}
