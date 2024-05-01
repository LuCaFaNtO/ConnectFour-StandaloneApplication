package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.controller.edit.LanguageController;
import ch.supsi.connectfour.frontend.model.edit.UpdateLanguageInterface;
import ch.supsi.connectfour.frontend.dispatcher.edit.LanguageControllerInterface;
import com.sun.javafx.menu.MenuItemBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import java.util.ResourceBundle;

public class MenuBarDispatcher implements UpdateLanguageInterface {
    private final LanguageControllerInterface languageController;

    @FXML
    private MenuBar containerMenuBar;

    public MenuBarDispatcher() {
        languageController = LanguageController.getInstance();

        languageController.setUpdaterLanguageList(this);
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
    public void updateLanguage(ResourceBundle resourceBundle) {
        for (Menu menu : containerMenuBar.getMenus()) {
            updateText(menu.getId(), resourceBundle, menu);
            for (MenuItem menuItem : menu.getItems()) {
                if(menuItem instanceof SeparatorMenuItem)
                    continue;
                updateText(menuItem.getId(), resourceBundle, menuItem);
            }
        }
    }

    private void updateText(String key, ResourceBundle resourceBundle, MenuItem ob){
        String text;
        key = "MenuBar." + key;
        text = resourceBundle.getString(key);
        ob.setText(text);
    }
}
