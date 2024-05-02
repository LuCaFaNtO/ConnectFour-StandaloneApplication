package ch.supsi.connectfour.frontend.model.edit;

import javafx.fxml.FXMLLoader;

import java.util.ResourceBundle;

public interface UpdateLanguageInterface {
    void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle);
    void changeSceneFx();
    FXMLLoader getFxmlLoaderMenuBar();
}
