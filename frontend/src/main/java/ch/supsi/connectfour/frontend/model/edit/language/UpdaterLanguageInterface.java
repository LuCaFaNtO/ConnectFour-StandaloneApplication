package ch.supsi.connectfour.frontend.model.edit.language;

import java.util.ResourceBundle;

public interface UpdaterLanguageInterface {
    void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle);
    void changeSceneFx();
}
