package View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Sonali Singh.
 */
public class endPlayerTurn {

        @FXML
        private Button buttonUp;

        @FXML
        private Label error;

        @FXML
        private void buttonAction(ActionEvent button) throws IOException {
            Stage s;
            Parent root;
            Button a = (Button) button.getSource();
            s = (Stage) a.getScene().getWindow();
            if (a == buttonUp) {
                root = FXMLLoader.load(getClass().getResource("townMap.fxml"));
            } else {
                root = null;
            }
            Scene scene = new Scene(root);
            s.setScene(scene);
            s.show();
        }

}