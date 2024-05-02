package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.controller.edit.LanguageController;
import ch.supsi.connectfour.frontend.model.edit.UpdateLanguageInterface;
import ch.supsi.connectfour.frontend.dispatcher.edit.LanguageControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;

import java.util.ResourceBundle;

public class MenuBarDispatcher implements UpdateLanguageInterface {
    private final LanguageControllerInterface languageController;

    private final String fxmlLocation = "/menuBar.fxml";

    private FXMLLoader fxmlLoaderMenuBar = null;

    public MenuBarDispatcher() {
        languageController = LanguageController.getInstance();
    }

    public void newGame(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    public void openGame(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    public void saveGame(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    public void saveGameAs(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    public void quit(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    /*public void setLanguageController(LanguageControllerInterface languageControllerExternal) {
        if(languageControllerExternal != null)
            languageController = languageControllerExternal;
        else
            languageController = LanguageController.getInstance();
    }*/

    public void changeLanguage(ActionEvent actionEvent) {
        //Cambia lingua
        // Get the source of the event
        String idLanguage = ((MenuItem) actionEvent.getSource()).getId();
        languageController.changeLanguage(idLanguage);
    }

    public void showAbout(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    public void showHelp(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        fxmlLoaderMenuBar = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    @Override
    public void changeSceneFx() {
        MainFx.updateSceneMenuBarWithNewLanguage();
    }

    @Override
    public FXMLLoader getFxmlLoaderMenuBar() {
        return fxmlLoaderMenuBar;
    }
}
