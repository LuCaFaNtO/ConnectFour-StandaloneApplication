package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.model.edit.language.UpdaterLanguageInterface;
import ch.supsi.connectfour.frontend.model.statusGame.UpdateStatusInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoBar implements Initializable, UpdaterLanguageInterface, UpdateStatusInterface {
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
    public void changeSceneFx() {
        infobar.setText(fxmlLoaderInfoBarView.getResources().getString(oldKeyUsed) + plusTextUsed);
    }

    public void win(String playerName, String playerSymbol){
        oldKeyUsed = "InfoBar.win";
        plusTextUsed = " " + playerName + " " + playerSymbol;
        changeSceneFx();
    }

    public void gridFull(){
        oldKeyUsed = "InfoBar.gridFull";
        plusTextUsed = "";
        changeSceneFx();
    }

    public void showTurn(String playerName, String playerSymbol){
        oldKeyUsed = "InfoBar.turn";
        plusTextUsed = " " + playerName + " " + playerSymbol;
        changeSceneFx();
    }

    @Override
    public void updateViewStatusPreStart() {
        oldKeyUsed = "InfoBar.infobar";
        plusTextUsed = "";
        changeSceneFx();
    }

    @Override
    public void updateViewStatusGame() {}

    @Override
    public void updateViewStatusEnd() {}

    public void showSaveGame(){
        plusTextUsed += " - " + fxmlLoaderInfoBarView.getResources().getString("InfoBar.save");
        changeSceneFx();
    }
}
