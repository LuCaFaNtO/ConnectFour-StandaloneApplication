package ch.supsi.connectfour.frontend.view.menubar;

import ch.supsi.connectfour.frontend.controller.menubar.MenuBarViewInterface;
import ch.supsi.connectfour.frontend.dispatcher.MenuBarDispatcher;
import ch.supsi.connectfour.frontend.model.edit.language.UpdaterLanguageInterface;
import ch.supsi.connectfour.frontend.model.statusGame.UpdateStatusInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;
import java.util.Set;

public class MenuBarView implements MenuBarViewInterface, UpdateStatusInterface, UpdaterLanguageInterface {
    private static MenuBarView instance = null;

    private final String fxmlLocation = "/menubar.fxml";
    private FXMLLoader fxmlLoaderMenuBar;

    private MenuBar containerMenuBar;
    private MenuItem newMenuItem;
    private MenuItem saveMenuItem;
    private MenuItem saveAsMenuItem;

    private MenuBarView() {
        this.fxmlLoaderMenuBar = new FXMLLoader(getClass().getResource(fxmlLocation), ResourceBundle.getBundle("i18n.labels"));
    }

    public static MenuBarView getInstance() {
        return instance == null ? instance = new MenuBarView() : instance;
    }

    @Override
    public void addSupportedLanguages(MenuBarDispatcher menuBarDispatcher, Set<String> availableLanguagesSet, Menu languagesMenu) {
        for (String languageName : availableLanguagesSet) {
            MenuItem menuItem = new MenuItem(languageName);
            menuItem.setId(languageName);
            menuItem.setMnemonicParsing(false);
            menuItem.setOnAction(menuBarDispatcher::changeLanguage);
            menuItem.setText(fxmlLoaderMenuBar.getResources().getString("MenuBar." + languageName));
            languagesMenu.getItems().add(menuItem);
        }
    }

    @Override
    public void setNewMenuItem(MenuItem menuItem) {
        this.newMenuItem = menuItem;
    }

    @Override
    public void setSaveMenuItem(MenuItem menuItem) {
        this.saveMenuItem = menuItem;
    }

    @Override
    public void setSaveAsMenuItem(MenuItem menuItem) {
        this.saveAsMenuItem = menuItem;
    }

    @Override
    public void setContainerMenuBar(MenuBar containerMenuBar) {
        this.containerMenuBar = containerMenuBar;
    }

    @Override
    public void showHelp() {
        try {
            Desktop.getDesktop().browse(new URI(fxmlLoaderMenuBar.getResources().getString("Help.link")));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
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
        saveAsMenuItem.setDisable(true);
    }

    public void enableSaveAsMenuItems() {
        saveAsMenuItem.setDisable(false);
    }

    @Override
    public void updateViewStatusPreStart() {
        disableNewMenuItems();
        disableSaveMenuItems();
        disableSaveAsMenuItems();
    }

    @Override
    public void updateViewStatusGame() {
        enableNewMenuItems();
        enableSaveMenuItems();
        enableSaveAsMenuItems();
    }

    @Override
    public void updateViewStatusEnd() {
        disableSaveMenuItems();
        disableSaveAsMenuItems();
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
}
