package View;
import Main.Main;
import Model.Land;
import Model.Map;
import Model.Player;
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
    private int column =0;
    private int row = 0;

    @FXML
    private Pane townPane;

    @FXML
    private Label lblPlayerName;

    @FXML
    private Button btnContinue;

    @FXML
    private Button btnSkip;

    public mapController(){

    }

    @FXML
    private void initialize(){
    }

    @FXML
    public void openTown(){
        //Provide conditional when this method can work.
        //ie if(land purchases are done)
        main.showTownScreen();
        Stage stage = (Stage)prevScene.getWindow();
        stage.close();
    }

    @FXML
    public void skipTurn(){
        nextPlayer();
        if(numPlayers> tempPlayers.size()){
            //Done with buying land
            //Start Turns
            System.out.println("Finsihed buying Land.");
        }
    }
    @FXML
    public void playersSelectLand(){
        if(numPlayers < tempPlayers.size()){
            //Go through land procedure
            //If land is not owned
            if(tempMap.getLand(row,column).isOpen()){
                Land chosenLand = tempMap.getLand(row,column);
                if(currentPlayer.haveLandGrants()){
                    currentPlayer.useLandGrant();
                    chosenLand.setOpen(false);
                    currentPlayer.addLand(chosenLand);
                    chosenLand.setPlayerOwner(currentPlayer);
                    numPlayers++;
                    //
                    //chosenLand.setColor(currentPlayer.getColor()); //Returns a String
                }else if(currentPlayer.getMoney() >= 300){
                    //Has enough money but not any landGrants
                    chosenLand.setOpen(false);
                    currentPlayer.addLand(chosenLand);
                    chosenLand.setPlayerOwner(currentPlayer);
                    numPlayers++;
                }else{
                    //Not enough money or any Land Grants
                    System.out.println("Player can't afford this land.");
                }
            }else{
                System.out.println("Land is already owned. Pick another piece of land or skip.");
            }
        }else{
            //All Players have finished buying land or skipping
            //Start turns
        }
    }

    public void nextPlayer(){
        currentPlayer = tempPlayers.get(++numPlayers);
        this.lblPlayerName.setText(currentPlayer.getName());
    }
    public void setMainApp(Main mainApp) {
        this.main = mainApp;
    }

    public void setPrevScene(Scene scene){
        this.prevScene = scene;
    }

    public void setPlayerData(ObservableList<Player> player){
        this.tempPlayers = FXCollections.observableArrayList(player);
        currentPlayer = tempPlayers.get(0);
        this.lblPlayerName.setText(currentPlayer.getName());
    }

    public void printPlayers(){
//        for(Player p : tempPlayers){
//            System.out.println(p.getName());
//        }
        System.out.println("Row:" + row + "Column" + column);
    }
    public void getMap(Map map){
        this.tempMap = map;
    }

    @FXML
         public void setLandChoice00(){
        this.column = 0;
        this.row = 0;
    }
    @FXML
    public void setLandChoice01() {
        this.column = 1;
        this.row = 0;
    }
    @FXML
    public void setLandChoice02() {
        this.column = 2;
        this.row = 0;
    }
    @FXML
    public void setLandChoice03() {
        this.column = 3;
        this.row = 0;
    }
    @FXML
    public void setLandChoice04() {
        this.column = 4;
        this.row = 0;
    }
    @FXML
    public void setLandChoice05() {
        this.column = 5;
        this.row = 0;
    }
    @FXML
    public void setLandChoice06() {
        this.column = 6;
        this.row = 0;
    }
    @FXML
    public void setLandChoice07() {
        this.column = 7;
        this.row = 0;
    }
    @FXML
    public void setLandChoice08() {
        this.column = 8;
        this.row = 0;
    }
    @FXML
    public void setLandChoice10(){
        this.column = 0;
        this.row = 1;
    }
    @FXML
    public void setLandChoice11() {
        this.column = 1;
        this.row = 1;
    }
    @FXML
    public void setLandChoice12() {
        this.column = 2;
        this.row = 1;
    }
    @FXML
    public void setLandChoice13() {
        this.column = 3;
        this.row = 1;
    }
    @FXML
    public void setLandChoice14() {
        this.column = 4;
        this.row = 1;
    }
    @FXML
    public void setLandChoice15() {
        this.column = 5;
        this.row = 1;
    }
    @FXML
    public void setLandChoice16() {
        this.column = 6;
        this.row = 1;
    }
    @FXML
    public void setLandChoice17() {
        this.column = 7;
        this.row = 1;
    }
    @FXML
    public void setLandChoice18() {
        this.column = 8;
        this.row = 1;
    }
    @FXML
    public void setLandChoice20(){
        this.column = 0;
        this.row = 2;
    }
    @FXML
    public void setLandChoice21() {
        this.column = 1;
        this.row = 2;
    }
    @FXML
    public void setLandChoice22() {
        this.column = 2;
        this.row = 2;
    }
    @FXML
    public void setLandChoice23() {
        this.column = 3;
        this.row = 2;
    }
    @FXML
    public void setLandChoice25() {
        this.column = 5;
        this.row = 2;
    }
    @FXML
    public void setLandChoice26() {
        this.column = 6;
        this.row = 2;
    }
    @FXML
    public void setLandChoice27() {
        this.column = 7;
        this.row = 2;
    }
    @FXML
    public void setLandChoice28() {
        this.column = 8;
        this.row = 2;
    }
    @FXML
    public void setLandChoice30(){
        this.column = 0;
        this.row = 3;
    }
    @FXML
    public void setLandChoice31() {
        this.column = 1;
        this.row = 3;
    }
    @FXML
    public void setLandChoice32() {
        this.column = 2;
        this.row = 3;
    }
    @FXML
    public void setLandChoice33() {
        this.column = 3;
        this.row = 3;
    }
    @FXML
    public void setLandChoice34() {
        this.column = 4;
        this.row = 3;
    }
    @FXML
    public void setLandChoice35() {
        this.column = 5;
        this.row = 3;
    }
    @FXML
    public void setLandChoice36() {
        this.column = 6;
        this.row = 3;
    }
    @FXML
    public void setLandChoice37() {
        this.column = 7;
        this.row = 3;
    }
    @FXML
    public void setLandChoice38() {
        this.column = 8;
        this.row = 3;
    }
    @FXML
    public void setLandChoice40(){
        this.column = 0;
        this.row = 4;
    }
    @FXML
    public void setLandChoice41() {
        this.column = 1;
        this.row = 4;
    }
    @FXML
    public void setLandChoice42() {
        this.column = 2;
        this.row = 4;
    }
    @FXML
    public void setLandChoice43() {
        this.column = 3;
        this.row = 4;
    }
    @FXML
    public void setLandChoice44() {
        this.column = 4;
        this.row = 4;
    }
    @FXML
    public void setLandChoice45() {
        this.column = 5;
        this.row = 4;
    }
    @FXML
    public void setLandChoice46() {
        this.column = 6;
        this.row = 4;
    }
    @FXML
    public void setLandChoice47() {
        this.column = 7;
        this.row = 4;
    }
    @FXML
    public void setLandChoice48() {
        this.column = 8;
        this.row = 4;
    }
}
