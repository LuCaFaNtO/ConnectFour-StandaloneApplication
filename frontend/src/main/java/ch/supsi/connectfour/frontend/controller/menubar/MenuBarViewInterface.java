package ch.supsi.connectfour.frontend.controller.menubar;

import ch.supsi.connectfour.frontend.dispatcher.MenuBarDispatcher;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.Set;

public interface MenuBarViewInterface {
    void addSupportedLanguages(MenuBarDispatcher menuBarDispatcher, Set<String> availableLanguagesSet, Menu languagesMenu);

    void setNewMenuItem(MenuItem menuItem);

    void setSaveMenuItem(MenuItem menuItem);

    void setSaveAsMenuItem(MenuItem menuItem);

    void setContainerMenuBar(MenuBar containerMenuBar);

    void showHelp();
}
