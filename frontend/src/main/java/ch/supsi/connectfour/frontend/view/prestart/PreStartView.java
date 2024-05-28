package ch.supsi.connectfour.frontend.view.prestart;

import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.dispatcher.PreStartDispatcher;
import ch.supsi.connectfour.frontend.model.statusGame.UpdateStatusInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PreStartView implements UpdateStatusInterface {
    private static PreStartView instance = null;
    private final String fxmlLocation = "/prestart.fxml";
    private FXMLLoader preStartLoader;

    private PreStartView() {
        this.preStartLoader = new FXMLLoader(getClass().getResource(fxmlLocation), ResourceBundle.getBundle("i18n.labels"));
    }

    public static PreStartView getInstance() {
        return instance == null? instance = new PreStartView():instance;
    }

    private void showPreStartPage(){
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
