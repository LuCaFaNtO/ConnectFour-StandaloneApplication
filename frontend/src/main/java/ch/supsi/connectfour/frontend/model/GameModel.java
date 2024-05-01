package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.application.*;
import ch.supsi.connectfour.frontend.controller.GameModelInterface;

public class GameModel implements GameModelInterface {
    private static GameModel gameModel = null;
    private final GridControllerInterface gridController;

    protected GameModel() {
        gridController = GridController.getInstance();
    }

    public static GameModel getInstance() {
        return gameModel == null? gameModel = new GameModel() : gameModel;
    }

    @Override
    public void insertPiece(final int column) {
        gridController.insertPiece(column);
    }
}
