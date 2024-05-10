package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.MainFx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PreferencesDispatcher {

    @FXML
    Button exitButton;

    public void savePreferences(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
        MainFx.showGameBoard();
    }
}