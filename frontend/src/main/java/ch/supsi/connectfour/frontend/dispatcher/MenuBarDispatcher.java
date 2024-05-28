package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.controller.AboutController;
import ch.supsi.connectfour.frontend.controller.edit.language.LanguageController;
import ch.supsi.connectfour.frontend.controller.edit.preferences.PreferencesController;
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

public class MenuBarDispatcher implements UpdaterLanguageInterface, Initializable, UpdateStatusInterface {
    private final AboutControllerInterface aboutController;
    private final LanguageControllerInterface languageController;
    private final PreferencesControllerInterface preferencesController;
    private final StatusGameControllerInterface statusGameController;
    private final SavingGameControllerInterface savingGameController;

    private final String fxmlLocation = "/menubar.fxml";
    private FXMLLoader fxmlLoaderMenuBar;

    private static List<MenuItem> menuItemList;

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
        this.aboutController = AboutController.getInstance();
        this.languageController = LanguageController.getInstance();
        this.preferencesController = PreferencesController.getInstance();
        this.statusGameController = StatusGameController.getInstance();
        this.savingGameController = SavingGameController.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoaderMenuBar = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
        addSupportedLanguages();
        menuItemList = List.of(newMenuItem, saveMenuItem, saveasMenuItem, preferencesMenuItem);

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
        File openFile = getOpenFile();
        if (openFile != null) {
            savingGameController.setNewSavingGameFile(openFile);
            if(savingGameController.loadGame())
                statusGameController.setStatusToGame();
        }
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
        try {
            Desktop.getDesktop().browse(new URI(fxmlLoaderMenuBar.getResources().getString("Help.link")));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File getOpenFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showOpenDialog(containerMenuBar.getScene().getWindow());
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
        for (Menu menu : containerMenuBar.getMenus()) {
            setNewText(menu);
            for (MenuItem menuItem : menu.getItems())
                if (!(menuItem instanceof SeparatorMenuItem))
                    setNewText(menuItem);
        }
    }

    private void setNewText(MenuItem menuItem) {
        String text = fxmlLoaderMenuBar.getResources().getString("MenuBar." + menuItem.getId());
        menuItem.setText(text);
    }

    public void disableNewMenuItems() {
        newMenuItem.setDisable(true);
    }

    public void enableNewMenuItems() {
        newMenuItem.setDisable(false);
    }

    public void disableSaveMenuItems() {
        saveMenuItem.setDisable(true);
    }

    public void enableSaveMenuItems() {
        saveMenuItem.setDisable(false);
    }

    public void disableSaveAsMenuItems() {
        saveasMenuItem.setDisable(true);
    }

    public void enableSaveAsMenuItems() {
        saveasMenuItem.setDisable(false);
    }

    public void disablePreferencesMenuItems() {
        preferencesMenuItem.setDisable(true);
    }

    public void enablePreferencesMenuItems() {
        preferencesMenuItem.setDisable(false);
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
        disableNewMenuItems();
        disableSaveMenuItems();
        disableSaveAsMenuItems();
        Scene scene = containerMenuBar.getScene();
        if (scene != null) {
            disableSaveShortcut(containerMenuBar.getScene());
            disableSaveAsShortcut(containerMenuBar.getScene());
        }
    }

    @Override
    public void updateViewStatusGame() {
        menuItemList.forEach(menuItem -> menuItem.setDisable(false));
        enableSaveShortcut(containerMenuBar.getScene());
        enableSaveAsShortcut(containerMenuBar.getScene());
    }

    @Override
    public void updateViewStatusEnd() {
        disableSaveMenuItems();
        disableSaveAsMenuItems();
        disablePreferencesMenuItems();
        disableSaveShortcut(containerMenuBar.getScene());
        disableSaveAsShortcut(containerMenuBar.getScene());
    }
}
