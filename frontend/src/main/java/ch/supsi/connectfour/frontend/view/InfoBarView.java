package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoBarView implements Initializable, UpdateLanguageInterface {
    private final String fxmlLocation = "/infobar.fxml";

    @FXML
    public Text infobar;

    private FXMLLoader fxmlLoaderInfoBarView;

    private String oldKeyUsed;
    private String oldTextUsed;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoaderInfoBarView = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
        this.oldKeyUsed = "InfoBar.infobar";
        this.oldTextUsed = "";
    }

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        String tempText = fxmlLoaderInfoBarView.getResources().getString(oldKeyUsed);
        oldTextUsed = infobar.getText().substring(tempText.length());
        fxmlLoaderInfoBarView = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    @Override
    public void changeSceneFx() {
        infobar.setText(fxmlLoaderInfoBarView.getResources().getString(oldKeyUsed) + oldTextUsed);
    }

    @Override
    public FXMLLoader getFxmlLoader() {
        return fxmlLoaderInfoBarView;
    }

    public void win(String playerName, String playerSymbol){
        oldKeyUsed = "InfoBar.win";
        changeSceneFx();
        infobar.setText(infobar.getText() + " " + playerName + " " + playerSymbol);
    }

    public void gridFull(){
        oldKeyUsed = "InfoBar.gridFull";
        changeSceneFx();
    }
}
