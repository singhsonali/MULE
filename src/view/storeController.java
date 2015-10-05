package View;
import Main.Main;
import Model.Land;
import Model.Map;
import Model.Player;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.awt.*;

/**
 * Created by Ashley on 9/25/2015.
 */
public class storeController {

    private Pane currentPane;

    @FXML
    private Button btnReturn;
    @FXML
    private Button btnBuy;
    @FXML
    private Button btnSell;
    @FXML
    private TextField amnt;

    public storeController() {

    }

    @FXML
    private void initialize(){
        currentPane = null;
    }

}
