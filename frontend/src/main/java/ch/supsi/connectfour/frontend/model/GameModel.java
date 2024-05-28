package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.application.GridController;
import ch.supsi.connectfour.backend.application.GridControllerInterface;
import ch.supsi.connectfour.backend.application.ObserverControllerInterface;
import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.application.observer.ColumnObserver;
import ch.supsi.connectfour.backend.application.observer.GridObserver;
import ch.supsi.connectfour.backend.application.observer.ObserverController;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.frontend.controller.GameModelInterface;
import ch.supsi.connectfour.frontend.dispatcher.ColumnsSelectorDispatcher;

public class GameModel implements GameModelInterface {
    private static GameModel gameModel = null;
    private final GridControllerInterface gridController;

    protected GameModel() {
        gridController = GridController.getInstance();
    }

    public static GameModel getInstance() {
        return gameModel == null ? gameModel = new GameModel() : gameModel;
    }

    @Override
    public void insertPiece(final int column) throws InsertPieceException, IllegalColumnException {
        gridController.insertPiece(column);
    }

    @Override
    public Cell getCell() {
        return gridController.getCell();
    }
}
