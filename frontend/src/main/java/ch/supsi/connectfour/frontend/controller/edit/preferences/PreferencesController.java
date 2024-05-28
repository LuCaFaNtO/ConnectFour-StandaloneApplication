package ch.supsi.connectfour.frontend.controller.edit.preferences;

import ch.supsi.connectfour.backend.application.GridControllerInterface;
import ch.supsi.connectfour.backend.application.ObserverControllerInterface;
import ch.supsi.connectfour.backend.application.exceptions.IllegalPreferencesException;
import ch.supsi.connectfour.backend.application.observer.ObserverController;
import ch.supsi.connectfour.backend.application.observer.UpdatePreferencesObserver;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.frontend.dispatcher.edit.preferences.PreferencesControllerInterface;
import ch.supsi.connectfour.frontend.dispatcher.edit.preferences.PreferencesDispatcher;
import ch.supsi.connectfour.frontend.model.UpdateGridInterface;
import ch.supsi.connectfour.frontend.model.edit.preferences.PreferencesModel;
import ch.supsi.connectfour.frontend.view.ErrorView;
import ch.supsi.connectfour.frontend.view.ErrorViewInterface;

import java.util.List;
import java.util.Set;

public class PreferencesController implements PreferencesControllerInterface, UpdatePreferencesObserver {
    private static PreferencesController instance = null;
    private final PreferencesModelInterface preferencesModel;
    private final ObserverControllerInterface observerController;

    private final ErrorViewInterface errorView;
    private UpdateGridInterface updateGrid;
    private PreferencesDispatcher preferencesDispatcher;

    private PreferencesController() {
        this.preferencesModel = PreferencesModel.getInstance();
        this.observerController = ObserverController.getInstance();
        this.errorView = ErrorView.getInstance();

        this.observerController.registerUpdaterPreferencesObserver(this);
    }

    public static PreferencesController getInstance() {
        return instance == null ? instance = new PreferencesController() : instance;
    }

    @Override
    public Set<String> getSupportedColors() {
        return preferencesModel.getSupportedColors();
    }

    @Override
    public Set<String> getSupportedSymbols() {
        return preferencesModel.getSupportedSymbols();
    }

    @Override
    public List<Player> getPlayers() {
        return preferencesModel.getPlayers();
    }

    @Override
    public void setNewPreferences(List<Piece> pieces) {
        try {
            preferencesModel.setNewPreferences(pieces);
            preferencesDispatcher.exit();
        } catch (IllegalPreferencesException e) {
            errorView.showPopUpError(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public void addUpdaterGrid(UpdateGridInterface updateGrid) {
        this.updateGrid = updateGrid;
    }

    @Override
    public void addPreferencesView(PreferencesDispatcher preferencesDispatcher) {
        this.preferencesDispatcher = preferencesDispatcher;
    }

    @Override
    public void showPreferencesPage() {
        preferencesDispatcher.showPreferencesPage();
    }

    @Override
    public void updateGridWithNewPreferences(Cell[][] grid) {
        updateGrid.updateGridWithNewPreferences(grid);
    }

    @Override
    public void updateEmptyGrid(int col, int row) {
        updateGrid.updateEmptyGrid(col, row);
    }
}
