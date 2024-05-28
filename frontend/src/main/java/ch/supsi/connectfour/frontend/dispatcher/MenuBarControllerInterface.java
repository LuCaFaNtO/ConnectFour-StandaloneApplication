package ch.supsi.connectfour.frontend.dispatcher;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public interface MenuBarControllerInterface {
    void addSupportedLanguages(MenuBarDispatcher menuBarDispatcher, Menu languageMenu);
    void setNewMenuItem(MenuItem menuItem);
    void setSaveMenuItem(MenuItem menuItem);
    void setSaveasMenuItem(MenuItem menuItem);
    void showHelp();
}
