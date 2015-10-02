package View;
import Main.Main;
import Model.Land;
import Model.Map;
import Model.Player;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Created by Ashley on 9/25/2015.
 */
public class storeController {

    private Pane currentPane;

    private Button btnReturn;
    private Button btnBuy;
    private Button btnSell;

    public storeController() {

    }

    @FXML
    private void initialize(){
        currentPane = null;
    }

}
