package ch.supsi.connectfour.frontend.view.prestart;

import ch.supsi.connectfour.frontend.model.statusGame.UpdateStatusInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreStartView implements PreStartViewInterface, UpdateStatusInterface {
    private static PreStartView instance = null;
    private final String fxmlLocation = "/prestart.fxml";
    private FXMLLoader preStartLoader;

    private BorderPane mainBorderPane;
    private BorderPane gameBorderPane;

    private PreStartView() {
        this.preStartLoader = new FXMLLoader(getClass().getResource(fxmlLocation), ResourceBundle.getBundle("i18n.labels"));
    }

    public static PreStartView getInstance() {
        return instance == null ? instance = new PreStartView() : instance;
    }

    private void showPreStartPage() {
        AnchorPane preStartPage;
        try {
            if (preStartLoader.getRoot() != null)
                preStartLoader = new FXMLLoader(getClass().getResource(fxmlLocation), preStartLoader.getResources());
            URL fxmlUrl = getClass().getResource(fxmlLocation);
            if (fxmlUrl == null) {
                return;
            }
            preStartPage = preStartLoader.load();
            mainBorderPane.setCenter(preStartPage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateViewStatusPreStart() {
        showPreStartPage();
    }

    @Override
    public void updateViewStatusGame() {
        mainBorderPane.setCenter(gameBorderPane);
    }

    @Override
    public void updateViewStatusEnd() {
        //non ha compiti durante il game
    }

    @Override
    public void addMainBorderPain(BorderPane borderPane) {
        this.mainBorderPane = borderPane;
    }

    @Override
    public void addGameBorderPain(BorderPane borderPane) {
        this.gameBorderPane = borderPane;
    }
}
