package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.application.*;
import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.application.observer.ColumnObserver;
import ch.supsi.connectfour.backend.application.observer.FinishGameObserver;
import ch.supsi.connectfour.backend.application.observer.GridObserver;
import ch.supsi.connectfour.backend.application.observer.ObserverController;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.frontend.controller.GameModelInterface;
import ch.supsi.connectfour.frontend.dispatcher.ColumnsSelectorDispatcher;

import java.util.List;

public class GameModel implements GameModelInterface, GridObserver, ColumnObserver {
    private static GameModel gameModel = null;
    private final GridControllerInterface gridController;
    private final ObserverControllerInterface observerController;
    private UpdateGridInterface boardView;
    private ColumnsSelectorDispatcher columnSelectorDispatcher;


    protected GameModel() {
        gridController = GridController.getInstance();
        observerController = ObserverController.getInstance();

        observerController.registerGridObserver(this);
        observerController.registerColumnObserver(this);
    }

    public static GameModel getInstance() {
        return gameModel == null ? gameModel = new GameModel() : gameModel;
    }

    @Override
    public void insertPiece(final int column) {
        try {
            gridController.insertPiece(column);
        } catch (InsertPieceException | IllegalColumnException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void addUpdaterGrid(UpdateGridInterface boardView) {
        this.boardView = boardView;
    }

    @Override
    public void addDisableColumn(ColumnsSelectorDispatcher columnsSelectorDispatcher) {
        this.columnSelectorDispatcher = columnsSelectorDispatcher;
    }

    @Override
    public void onGridUpdate() {
        Cell cell = gridController.getCell();
        boardView.updateGrid(cell);
    }

    @Override
    public void disableColumn(final int column) {
        columnSelectorDispatcher.disableColumnButton(column);
    }
}
