package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import javafx.fxml.FXMLLoader;

import java.util.ResourceBundle;

public class InfoBarView implements UpdateLanguageInterface {
    private final String fxmlLocation = "/infobar.fxml";

    private FXMLLoader fxmlLoaderInfoBarView = null;

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        fxmlLoaderInfoBarView = new FXMLLoader(getClass().getResource(fxmlLocation));
        fxmlLoaderInfoBarView.setResources(resourceBundle);
    }

    @Override
    public void changeSceneFx() {
        MainFx.updateSceneInfoBarWithNewLanguage();
    }

    @Override
    public FXMLLoader getFxmlLoader() {
        return fxmlLoaderInfoBarView;
    }
}
