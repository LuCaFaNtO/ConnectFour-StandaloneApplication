package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.controller.edit.language.UpdaterLanguageInterface;
import ch.supsi.connectfour.frontend.model.statusGame.UpdateStatusAbstract;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoBar extends UpdateStatusAbstract implements Initializable, UpdaterLanguageInterface {
    private final String fxmlLocation = "/infobar.fxml";

    @FXML
    public Text infobar;

    private FXMLLoader fxmlLoaderInfoBarView;

    private String oldKeyUsed;
    private String plusTextUsed;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoaderInfoBarView = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
        this.oldKeyUsed = "InfoBar.infobar";
        this.plusTextUsed = "";
    }

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        String tempText = fxmlLoaderInfoBarView.getResources().getString(oldKeyUsed);
        plusTextUsed = infobar.getText().substring(tempText.length());
        fxmlLoaderInfoBarView = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    @Override
    public void changeImmediatelySceneFx() {
        infobar.setText(fxmlLoaderInfoBarView.getResources().getString(oldKeyUsed) + plusTextUsed);
    }

    public void win(String playerName, String playerSymbol) {
        oldKeyUsed = "InfoBar.win";
        plusTextUsed = " " + playerName + " " + playerSymbol;
        changeImmediatelySceneFx();
    }

    public void gridFull() {
        oldKeyUsed = "InfoBar.gridFull";
        plusTextUsed = "";
        changeImmediatelySceneFx();
    }

    public void showTurn(String playerName, String playerSymbol) {
        oldKeyUsed = "InfoBar.turn";
        plusTextUsed = " " + playerName + " " + playerSymbol;
        changeImmediatelySceneFx();
    }

    @Override
    public void updateViewStatusPreStart() {
        oldKeyUsed = "InfoBar.infobar";
        plusTextUsed = "";
        changeImmediatelySceneFx();
    }

    public void showSaveGame() {
        plusTextUsed += " - " + fxmlLoaderInfoBarView.getResources().getString("InfoBar.save");
        changeImmediatelySceneFx();
    }
}
