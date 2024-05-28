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

public class PreStartDispatcher implements Initializable, UpdateStatusInterface {
    private final String fxmlLocation = "/prestart.fxml";
    private FXMLLoader preStartLoader;
    private StatusGameControllerInterface statusGameController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.preStartLoader = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
        this.statusGameController = StatusGameController.getInstance();
    }

    public void showPreStartPage(){
        AnchorPane preStartPage;
        try {
            if(preStartLoader.getRoot() != null)
                preStartLoader = new FXMLLoader(getClass().getResource(fxmlLocation), preStartLoader.getResources());
            URL fxmlUrl = getClass().getResource(fxmlLocation);
            if (fxmlUrl == null) {
                return;
            }
            preStartPage = preStartLoader.load();
            MainFx.showPreStartPage(preStartPage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void startNewGame() {
        statusGameController.setStatusToGame();
    }

    @Override
    public void updateViewStatusPreStart() {
        showPreStartPage();
    }

    @Override
    public void updateViewStatusGame() {
        MainFx.showGameBoard();
    }

    @Override
    public void updateViewStatusEnd() {
        //non ha compiti durante il game
    }
}
