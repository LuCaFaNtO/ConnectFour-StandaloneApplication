package ch.supsi.connectfour.frontend.controller.edit.language;

import java.util.ResourceBundle;

public interface UpdaterLanguageInterface {
    void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle);

    void changeImmediatelySceneFx();
}
