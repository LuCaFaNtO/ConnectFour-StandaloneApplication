package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.application.*;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.application.observer.GridObserver;
import ch.supsi.connectfour.backend.business.Cell;
import ch.supsi.connectfour.frontend.controller.GameModelInterface;
import ch.supsi.connectfour.frontend.model.edit.UpdateLanguageInterface;
import ch.supsi.connectfour.frontend.view.BoardView;

public class GameModel implements GameModelInterface, GridObserver {
    private static GameModel gameModel = null;
    private final GridControllerInterface gridController;
    private BoardView boardView; //TODO: rivedere

    protected GameModel() {
        gridController = GridController.getInstance();
        gridController.registerObserver(this); //GameModel diventa un osservatore di GridController
        this.boardView = new BoardView();
    }

    public static GameModel getInstance() {
        return gameModel == null ? gameModel = new GameModel() : gameModel;
    }

    @Override
    public void insertPiece(final int column) {
        try {
            gridController.insertPiece(column);
        } catch (InsertPieceException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void addUpdaterGrid(BoardView boardView) {
        this.boardView = boardView;
    }

    @Override
    public void onGridUpdate() {
        Cell cell = gridController.getCell();
        boardView.updateGrid(cell);
    }
}
