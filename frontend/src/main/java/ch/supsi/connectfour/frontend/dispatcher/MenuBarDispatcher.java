package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.controller.AboutController;
import ch.supsi.connectfour.frontend.controller.edit.language.LanguageController;
import ch.supsi.connectfour.frontend.controller.edit.preferences.PreferencesController;
import ch.supsi.connectfour.frontend.controller.menubar.MenuBarController;
import ch.supsi.connectfour.frontend.controller.savingGame.SavingGameController;
import ch.supsi.connectfour.frontend.controller.statusGame.StatusGameController;
import ch.supsi.connectfour.frontend.dispatcher.edit.language.LanguageControllerInterface;
import ch.supsi.connectfour.frontend.dispatcher.edit.preferences.PreferencesControllerInterface;
import ch.supsi.connectfour.frontend.model.edit.language.UpdaterLanguageInterface;
import ch.supsi.connectfour.frontend.model.statusGame.UpdateStatusInterface;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuBarDispatcher implements Initializable, UpdateStatusInterface {
    private final MenuBarControllerInterface menuBarController;
    private final AboutControllerInterface aboutController;
    private final LanguageControllerInterface languageController;
    private final PreferencesControllerInterface preferencesController;
    private final StatusGameControllerInterface statusGameController;
    private final SavingGameControllerInterface savingGameController;

    private KeyCombination ctrlS;
    private KeyCombination ctrlShiftS;

    @FXML
    public MenuBar containerMenuBar;
    @FXML
    public Menu languagesMenu;
    @FXML
    public MenuItem newMenuItem;
    @FXML
    public MenuItem saveMenuItem;
    @FXML
    public MenuItem saveasMenuItem;
    @FXML
    public MenuItem preferencesMenuItem;

    public MenuBarDispatcher() {
        this.menuBarController = MenuBarController.getInstance();
        this.aboutController = AboutController.getInstance();
        this.languageController = LanguageController.getInstance();
        this.preferencesController = PreferencesController.getInstance();
        this.statusGameController = StatusGameController.getInstance();
        this.savingGameController = SavingGameController.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuBarController.setNewMenuItem(newMenuItem);
        menuBarController.setSaveMenuItem(saveMenuItem);
        menuBarController.setSaveasMenuItem(saveasMenuItem);
        addSupportedLanguages();

        ctrlS = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);

        containerMenuBar.sceneProperty()
                .addListener((observable, oldScene, newScene) -> {
                    if (newScene != null)
                        disableSaveShortcut(newScene);
                });

        ctrlShiftS = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN);

        containerMenuBar.sceneProperty()
                .addListener((observable, oldScene, newScene) -> {
                    if (newScene != null)
                        disableSaveAsShortcut(newScene);
                });
    }

    public void newGame() {
        if (statusGameController.isInStateGame() && !savingGameController.isAlreadySave())
            savingGameController.showSaveGamePopUp();
        savingGameController.resetSavingConditions();
        statusGameController.setStatusToPreStart();
    }

    public void openGame() {
        if (statusGameController.isInStateGame() && !savingGameController.isAlreadySave())
            savingGameController.showSaveGamePopUp();

        if (savingGameController.openGame())
            statusGameController.setStatusToGame();
    }

    public void saveGame() {
        savingGameController.saveGame();
    }

    public void saveGameAs() {
        savingGameController.saveGameAs();
    }

    public void quit() {
        if (statusGameController.isInStateGame() && !savingGameController.isAlreadySave())
            savingGameController.showSaveGamePopUp();
        Platform.exit();
    }

    public void editPreferences() {
        preferencesController.showPreferencesPage();
    }

    public void showAbout() {
        aboutController.showAbout();
    }

    public void showHelp() {
        menuBarController.showHelp();
    }

    private void addSupportedLanguages() {
        menuBarController.addSupportedLanguages(this, languagesMenu);
    }

    public void changeLanguage(ActionEvent actionEvent) {
        String idLanguage = ((MenuItem) actionEvent.getSource()).getId();
        languageController.changeLanguage(idLanguage);
    }

    private void enableSaveShortcut(Scene scene) {
        scene.getAccelerators().put(ctrlS, this::saveGame);
    }

    private void disableSaveShortcut(Scene scene) {
        scene.getAccelerators().remove(ctrlS);
    }

    private void enableSaveAsShortcut(Scene scene) {
        scene.getAccelerators().put(ctrlShiftS, this::saveGameAs);
    }

    private void disableSaveAsShortcut(Scene scene) {
        scene.getAccelerators().remove(ctrlShiftS);
    }

    @Override
    public void updateViewStatusPreStart() {
        Scene scene = containerMenuBar.getScene();
        if (scene != null) {
            disableSaveShortcut(containerMenuBar.getScene());
            disableSaveAsShortcut(containerMenuBar.getScene());
        }
    }

    @Override
    public void updateViewStatusGame() {
        enableSaveShortcut(containerMenuBar.getScene());
        enableSaveAsShortcut(containerMenuBar.getScene());
    }

    @Override
    public void updateViewStatusEnd() {
        disableSaveShortcut(containerMenuBar.getScene());
        disableSaveAsShortcut(containerMenuBar.getScene());
    }
}
