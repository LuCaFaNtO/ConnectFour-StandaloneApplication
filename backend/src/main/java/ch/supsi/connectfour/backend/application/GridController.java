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

        /*List<Piece> defaultPlayerPieces = this.preferencesModel.getDefaultPieces();
        this.gridModel.initializePlayers(defaultPlayerPieces);

        //TODO: Creare una funzione new Game che inzializza elementi di gioco e fa questo notify
        this.observerController.notifyChangeTurn(gridModel.getCurrentPlayer().getName(), gridModel.getCurrentPlayer().getPiece().getSymbol());*/
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
                observerController.notifyColumnObserver(i);
        } else if (gridModel.isGridFull()){
            observerController.notifyGridFull();
        } else{
            gridModel.changeTurn();
            observerController.notifyChangeTurn(gridModel.getCurrentPlayer().getName(), gridModel.getCurrentPlayer().getPiece().getSymbol());
        }
    }

    private void checkLastRow(final int column) {
        if (gridModel.isLastRowInserted())
            observerController.notifyColumnObserver(column);
    }

    @Override
    public Cell getCell() {
        return gridModel.getCell();
    }

    @Override
    public List<Player> getPlayers() {
        if(gridModel.arePlayersNull())
            this.gridModel.initializePlayers(this.preferencesModel.getDefaultPieces());
        return gridModel.getPlayers();
    }

    @Override
    public Cell[][] getGrid() {
        return gridModel.getGrid();
    }

    @Override
    public void initializeNewStructureForNewGame() {
        this.gridModel.initializeNewStructureForNewGame();

        List<Piece> defaultPlayerPieces = this.preferencesModel.getDefaultPieces();
        this.gridModel.initializePlayers(defaultPlayerPieces);
    }

    @Override
    public void diceRollPerTurn() {
        this.gridModel.diceRollPerTurn();
        this.observerController.notifyChangeTurn(gridModel.getCurrentPlayer().getName(), gridModel.getCurrentPlayer().getPiece().getSymbol());
        this.observerController.notifyEmptyGrid(gridModel.getGrid()[0].length, gridModel.getGrid().length);
    }
}
