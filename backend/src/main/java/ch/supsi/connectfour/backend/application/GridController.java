package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.application.observer.ObserverController;
import ch.supsi.connectfour.backend.application.preferences.PreferencesBusinessInterface;
import ch.supsi.connectfour.backend.business.GridModel;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.backend.business.preferences.PreferencesModel;

import java.util.List;

public class GridController implements GridControllerInterface {
    private static GridController instance = null;
    private final GridBusinessInterface gridModel;
    private final PreferencesBusinessInterface preferencesModel;
    private final ObserverControllerInterface observerController;

    protected GridController() {
        this.gridModel = GridModel.getInstance();
        this.preferencesModel = PreferencesModel.getInstance();
        this.observerController = ObserverController.getInstance();
    }

    public static GridController getInstance() {
        return instance == null ? new GridController() : instance;
    }

    @Override
    public void insertPiece(final int column) throws InsertPieceException, IllegalColumnException {
        gridModel.insertPiece(column);

        observerController.notifyOnGridObserver();

        checkLastRow(column);

        if (gridModel.checkWin(column)) {
            observerController.notifyWin(gridModel.getWinner().getName(), gridModel.getWinner().getPiece().getSymbol());
            for (int i = 0; i < gridModel.getNumberOfGridsColumn(); i++)
                observerController.notifyDisableColumnObserver(i);
        } else if (gridModel.isGridFull()) {
            observerController.notifyGridFull();
        } else {
            gridModel.changeTurn();
            observerController.notifyChangeTurn(gridModel.getCurrentPlayer().getName(), gridModel.getCurrentPlayer().getPiece().getSymbol());
        }
    }

    private void checkLastRow(final int column) {
        if (gridModel.isLastRowInserted())
            observerController.notifyDisableColumnObserver(column);
    }

    @Override
    public Cell getCell() {
        return gridModel.getCell();
    }

    @Override
    public List<Player> getPlayers() {
        if (gridModel.arePlayersNull())
            this.gridModel.initializePlayers(preferencesModel.getCurrentPieces());
        return gridModel.getPlayers();
    }

    @Override
    public Cell[][] getGrid() {
        return gridModel.getGrid();
    }

    @Override
    public void initializeNewStructureForNewGame() {
        this.gridModel.initializeNewStructureForNewGame();

        List<Piece> currentPieces = this.preferencesModel.getCurrentPieces();
        this.gridModel.initializePlayers(currentPieces);
        this.gridModel.diceRollPerTurn();
        notifyEmptyGrid();
    }

    @Override
    public void diceRollPerTurn() {
        this.observerController.notifyChangeTurn(gridModel.getCurrentPlayer().getName(), gridModel.getCurrentPlayer().getPiece().getSymbol());
    }

    private void notifyEmptyGrid() {
        this.observerController.notifyEmptyGrid(gridModel.getNumberOfGridsColumn(), gridModel.getGrid().length);
        for (int i = 0; i < gridModel.getNumberOfGridsColumn(); i++)
            this.observerController.notifyEnableColumnObserver(i);
    }
}
