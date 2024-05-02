package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.model.edit.UpdateLanguageInterface;
import javafx.fxml.FXMLLoader;

import java.util.ResourceBundle;

public class BoardView implements UpdateLanguageInterface {

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {

    }

    @Override
    public void changeSceneFx() {

    }

    @Override
    public FXMLLoader getFxmlLoaderMenuBar() {
        return null;
    }
}
