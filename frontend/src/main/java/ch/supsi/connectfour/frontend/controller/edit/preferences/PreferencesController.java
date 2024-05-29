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
import ch.supsi.connectfour.frontend.view.preferences.PreferencesView;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Set;

public class PreferencesController implements PreferencesControllerInterface, UpdatePreferencesObserver {
    private static PreferencesController instance = null;
    private final PreferencesModelInterface preferencesModel;
    private final ObserverControllerInterface observerController;

    private final ErrorViewInterface errorView;
    private UpdateGridInterface updateGrid;
    //private PreferencesDispatcher preferencesDispatcher;
    private PreferencesViewInterface preferencesView;

    private PreferencesController() {
        this.preferencesModel = PreferencesModel.getInstance();
        this.observerController = ObserverController.getInstance();
        this.errorView = ErrorView.getInstance();
        this.preferencesView = PreferencesView.getInstance();

        this.observerController.registerUpdaterPreferencesObserver(this);
    }

    public static PreferencesController getInstance() {
        return instance == null ? instance = new PreferencesController() : instance;
    }

    @Override
    public List<Player> getPlayers() {
        return preferencesModel.getPlayers();
    }

    @Override
    public void setNewPreferences(List<Piece> pieces) {
        try {
            preferencesModel.setNewPreferences(pieces);
            preferencesView.exit(null);
        } catch (IllegalPreferencesException e) {
            errorView.showPopUpError(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public void addUpdaterGrid(UpdateGridInterface updateGrid) {
        this.updateGrid = updateGrid;
    }

    @Override
    public void addPreferencesView(PreferencesViewInterface preferencesView) {
        this.preferencesView = preferencesView;
    }

    @Override
    public void showPreferencesPage() {
        preferencesView.showPreferencesPage();
    }

    @Override
    public void fillRectangles(List<Rectangle> rectangles) {
        List<String> supportedColors = preferencesModel.getSupportedColors().stream().toList();
        preferencesView.fillRectangles(rectangles, supportedColors);
    }

    @Override
    public void writeSymbols(List<AnchorPane> anchorPanes) {
        List<String> supportedSymbols = preferencesModel.getSupportedSymbols().stream().toList();
        preferencesView.writeSymbols(anchorPanes, supportedSymbols);
    }

    @Override
    public void exit(Button closeButton) {
        preferencesView.exit(closeButton);
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
