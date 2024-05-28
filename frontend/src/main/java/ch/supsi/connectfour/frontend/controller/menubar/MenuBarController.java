package ch.supsi.connectfour.frontend.controller.menubar;

import ch.supsi.connectfour.frontend.controller.edit.language.LanguageModelInterface;
import ch.supsi.connectfour.frontend.dispatcher.MenuBarControllerInterface;
import ch.supsi.connectfour.frontend.dispatcher.MenuBarDispatcher;
import ch.supsi.connectfour.frontend.model.edit.language.LanguageModel;
import ch.supsi.connectfour.frontend.view.menubar.MenuBarView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuBarController implements MenuBarControllerInterface {
    private static MenuBarController instance = null;
    private final MenuBarViewInterface menuBarView;
    private final LanguageModelInterface languageModel;


    private MenuBarController() {
        this.menuBarView = MenuBarView.getInstance();
        this.languageModel = LanguageModel.getInstance();
    }

    public static MenuBarController getInstance() {
        return instance == null ? instance = new MenuBarController() : instance;
    }

    @Override
    public void addSupportedLanguages(MenuBarDispatcher menuBarDispatcher, Menu languagesMenu) {
        Set<String> availableLanguagesSet = languageModel.getSupportedLanguages().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        menuBarView.addSupportedLanguages(menuBarDispatcher, availableLanguagesSet, languagesMenu);
    }

    @Override
    public void setNewMenuItem(MenuItem menuItem) {
        menuBarView.setNewMenuItem(menuItem);
    }

    @Override
    public void setSaveMenuItem(MenuItem menuItem) {
        menuBarView.setSaveMenuItem(menuItem);
    }

    @Override
    public void setSaveasMenuItem(MenuItem menuItem) {
        menuBarView.setSaveasMenuItem(menuItem);
    }

    @Override
    public void showHelp() {
        menuBarView.showHelp();
    }
}
