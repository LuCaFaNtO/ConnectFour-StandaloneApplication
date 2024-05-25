package ch.supsi.connectfour.frontend.model.edit.language;

import java.util.ResourceBundle;

public interface UpdateLanguageInterface {
    void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle);
    void changeSceneFx();
}
