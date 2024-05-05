package ch.supsi.connectfour.frontend.model.edit;

import javafx.fxml.FXMLLoader;

import java.util.ResourceBundle;

public interface UpdateLanguageInterface extends UpdateViewInterface {
    void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle);
}
