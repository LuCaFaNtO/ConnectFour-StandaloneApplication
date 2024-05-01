package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.controller.edit.LanguageController;
import ch.supsi.connectfour.frontend.dispatcher.GameControllerInterface;
import ch.supsi.connectfour.frontend.dispatcher.edit.LanguageControllerInterface;
import ch.supsi.connectfour.frontend.model.GameModel;

public class GameController implements GameControllerInterface {
    private static GameController gameController = null;
    private final GameModelInterface gameModel;

    protected GameController() {
        gameModel = GameModel.getInstance();
    }

    public static GameController getInstance() {
        return gameController == null? gameController = new GameController() : gameController;
    }

    @Override
    public void insertPiece(final int column) {
        gameModel.insertPiece(column);
    }
}
