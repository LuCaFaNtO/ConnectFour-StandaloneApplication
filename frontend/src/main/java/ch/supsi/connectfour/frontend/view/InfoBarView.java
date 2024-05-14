package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoBarView implements Initializable, UpdateLanguageInterface {
    private final String fxmlLocation = "/infobar.fxml";

    @FXML
    public Text infobar;

    private FXMLLoader fxmlLoaderInfoBarView;

    private String oldKeyUsed;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoaderInfoBarView = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
        this.oldKeyUsed = "InfoBar.infobar";
    }

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        fxmlLoaderInfoBarView = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    @Override
    public void changeSceneFx() {
        infobar.setText(fxmlLoaderInfoBarView.getResources().getString(oldKeyUsed));
    }

    @Override
    public FXMLLoader getFxmlLoader() {
        return fxmlLoaderInfoBarView;
    }

    public void win(String playerName){
        oldKeyUsed = "InfoBar.win";
        changeSceneFx();
        String oldText = infobar.getText();
        infobar.setText(oldText + " " + playerName);
    }

    public void gridFull(){
        oldKeyUsed = "InfoBar.gridFull";
        changeSceneFx();
    }
}
