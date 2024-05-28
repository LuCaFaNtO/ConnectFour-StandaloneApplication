package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.application.ObserverControllerInterface;
import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.application.observer.ColumnObserver;
import ch.supsi.connectfour.backend.application.observer.GridObserver;
import ch.supsi.connectfour.backend.application.observer.ObserverController;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.frontend.dispatcher.ColumnsSelectorDispatcher;
import ch.supsi.connectfour.frontend.dispatcher.GameControllerInterface;
import ch.supsi.connectfour.frontend.model.GameModel;
import ch.supsi.connectfour.frontend.model.UpdateGridInterface;

public class GameController implements GameControllerInterface, GridObserver {
    private static GameController gameController = null;
    private final GameModelInterface gameModel;
    private final ObserverControllerInterface observerController;
    private UpdateGridInterface boardView;

    protected GameController() {
        gameModel = GameModel.getInstance();
        observerController = ObserverController.getInstance();

        observerController.registerGridObserver(this);

    }

    public static GameController getInstance() {
        return gameController == null ? gameController = new GameController() : gameController;
    }

    @Override
    public void addUpdaterGrid(UpdateGridInterface boardView) {
        this.boardView = boardView;
    }

    @Override
    public void insertPiece(final int column) {
        try {
            gameModel.insertPiece(column);
        } catch (InsertPieceException | IllegalColumnException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void onGridUpdate() {
        boardView.updateGrid(gameModel.getCell());
    }
}
