package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.controller.AboutController;
import ch.supsi.connectfour.frontend.controller.edit.language.LanguageController;
import ch.supsi.connectfour.frontend.controller.edit.preferences.PreferencesController;
import ch.supsi.connectfour.frontend.dispatcher.edit.language.LanguageControllerInterface;
import ch.supsi.connectfour.frontend.dispatcher.edit.preferences.PreferencesControllerInterface;
import ch.supsi.connectfour.frontend.dispatcher.edit.preferences.PreferencesDispatcher;
import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuBarDispatcher implements UpdateLanguageInterface, Initializable {
    private final AboutControllerInterface aboutController;
    private final LanguageControllerInterface languageController;
    private final PreferencesControllerInterface preferencesController;

    private final String fxmlLocation = "/menubar.fxml";

    @FXML
    public Menu languagesMenu;

    private FXMLLoader fxmlLoaderMenuBar;

    public MenuBarDispatcher() {
        aboutController = AboutController.getInstance();
        languageController = LanguageController.getInstance();
        preferencesController = PreferencesController.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoaderMenuBar = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
        addSupportedLanguages();
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

    public void editPreferences(ActionEvent actionEvent) {
        preferencesController.showPreferencesPage();
    }

    public void showAbout(ActionEvent actionEvent) {
        aboutController.showAbout();
    }

    public void showHelp(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URI(fxmlLoaderMenuBar.getResources().getString("Help.link")));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addSupportedLanguages() {
        Set<String> availableLanguagesSet = languageController.getSupportedLanguages().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));

        for (String languageName : availableLanguagesSet) {
            MenuItem menuItem = new MenuItem(languageName);
            menuItem.setId(languageName);
            menuItem.setMnemonicParsing(false);
            menuItem.setOnAction(this::changeLanguage);
            menuItem.setText(fxmlLoaderMenuBar.getResources().getString("MenuBar." + languageName));
            languagesMenu.getItems().add(menuItem);
        }
    }

    public void changeLanguage(ActionEvent actionEvent) {
        String idLanguage = ((MenuItem) actionEvent.getSource()).getId();
        languageController.changeLanguage(idLanguage);
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
    public FXMLLoader getFxmlLoader() {
        return fxmlLoaderMenuBar;
    }
}
