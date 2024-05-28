package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.controller.statusGame.StatusGameController;
import ch.supsi.connectfour.frontend.model.statusGame.UpdateStatusInterface;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreStartDispatcher {
    private final StatusGameControllerInterface statusGameController;

    public PreStartDispatcher() {
        this.statusGameController = StatusGameController.getInstance();
    }

    public void startNewGame() {
        statusGameController.setStatusToGame();
    }
}
