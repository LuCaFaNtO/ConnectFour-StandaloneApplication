package ch.supsi.connectfour.backend.application.savingGame;

import ch.supsi.connectfour.backend.application.GridBusinessInterface;
import ch.supsi.connectfour.backend.application.ObserverControllerInterface;
import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;
import ch.supsi.connectfour.backend.application.observer.ObserverController;
import ch.supsi.connectfour.backend.application.preferences.PreferencesBusinessInterface;
import ch.supsi.connectfour.backend.business.GridModel;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.preferences.PreferencesModel;
import ch.supsi.connectfour.backend.business.savingGame.SavingGameModel;

import java.io.File;
import java.io.IOException;

public class SavingGameController implements SavingGameControllerInterface {
    private static SavingGameController instance = null;
    private final SavingGameBusinessInterface savingGameModel;
    private final GridBusinessInterface gridModel;
    private final PreferencesBusinessInterface preferencesModel;
    private final ObserverControllerInterface observerController;

    private SavingGameController() {
        this.savingGameModel = SavingGameModel.getInstance();
        this.gridModel = GridModel.getInstance();
        this.preferencesModel = PreferencesModel.getInstance();
        this.observerController = ObserverController.getInstance();
    }

    public static SavingGameController getInstance() {
        return instance == null ? instance = new SavingGameController() : instance;
    }

    @Override
    public void saveGame(File file) throws IllegalFIleException, IOException {
        Cell[][] grid = gridModel.getGrid();
        boolean currentTurn = gridModel.getTurn();

        savingGameModel.saveGame(file, grid, currentTurn);
        observerController.notifySaveGame();
    }

    @Override
    public void loadGame(File file) throws IllegalFIleException {
        gridModel.initializePlayers(preferencesModel.getCurrentPieces());

        Cell[][] grid = savingGameModel.loadGridGame(file, gridModel.getPlayers());
        boolean turn = savingGameModel.loadTurnGame(file);

        gridModel.initializeNewStructureForNewGame(grid, turn);
        observerController.notifyEmptyGrid(gridModel.getNumberOfGridsColumn(), gridModel.getGrid().length);
        observerController.notifyAllGridUpdate(grid);
        for (int i = 0; i < gridModel.getNumberOfGridsColumn(); i++) {
            if (gridModel.isColumnFull(i))
                observerController.notifyDisableColumnObserver(i);
            else
                observerController.notifyEnableColumnObserver(i);
        }
    }
}
