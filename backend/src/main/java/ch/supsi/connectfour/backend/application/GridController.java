package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.application.observer.ColumnObserver;
import ch.supsi.connectfour.backend.application.preferences.PreferencesBusinessInterface;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.GridModel;
import ch.supsi.connectfour.backend.application.observer.GridObserver;
import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.backend.business.preferences.PreferencesModel;

import java.util.ArrayList;
import java.util.List;

public class GridController implements GridControllerInterface {
    private static GridController instance = null;
    private final GridBusinessInterface gridModel;
    private final PreferencesBusinessInterface preferencesModel;
    private List<GridObserver> gridObservers = new ArrayList<>();
    private List<ColumnObserver> columnObservers = new ArrayList<>();

    protected GridController() {
        this.gridModel = GridModel.getInstance();
        this.preferencesModel = PreferencesModel.getInstance();

        List<Piece> defaultPlayerPieces = this.preferencesModel.getDefaultPieces();
        this.gridModel.initializePlayers(defaultPlayerPieces);
    }

    public static GridController getInstance() {
        return instance == null ? new GridController() : instance;
    }

    @Override
    public void insertPiece(final int column) throws InsertPieceException, IllegalColumnException {
        gridModel.insertPiece(column);
        gridModel.changeTurn();

        notifyGridObserver();
        checkLastRow(column);

        if(gridModel.checkWin());
            //TODO: notify win

        if(gridModel.isGridFull());
            //TODO: notify grid is full
    }

    private void checkLastRow(final int column) {
        if(gridModel.isLastRowInserted())
            notifyColumnObserver(column);
    }

    @Override
    public void registerGridObserver(GridObserver observer) {
        gridObservers.add(observer);
    }

    @Override
    public void removeObserver(GridObserver observer) {
        gridObservers.remove(observer);
    }

    //TODO: Cercare (se possibile) di implementare una sola volta un register ed un remove
    //      che si basano su un'ipotetica interfaccia padre Observer

    @Override
    public void registerColumnObserver(ColumnObserver observer) {
        columnObservers.add(observer);
    }

    @Override
    public void removeObserver(ColumnObserver observer) {
        columnObservers.remove(observer);
    }

    @Override
    public void notifyGridObserver() {
        for (GridObserver observer : gridObservers) {
            observer.onGridUpdate();
        }
    }

    @Override
    public void notifyColumnObserver(final int column) {
        for(ColumnObserver observer : columnObservers)
            observer.disableColumn(column);
    }

    @Override
    public Cell getCell() {
        return gridModel.getCell();
    }

    @Override
    public List<Player> getPlayers() {
        return gridModel.getPlayers();
    }

    @Override
    public Cell[][] getGrid() {
        return gridModel.getGrid();
    }
}
