package ch.supsi.connectfour.frontend.model.edit.language;

import ch.supsi.connectfour.frontend.view.UpdateViewInterface;

import java.util.ResourceBundle;

public interface UpdateLanguageInterface extends UpdateViewInterface {
    void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle);
}
