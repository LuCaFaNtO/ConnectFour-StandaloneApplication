package ch.supsi.connectfour.frontend.model.statusGame;

import ch.supsi.connectfour.backend.application.GridController;
import ch.supsi.connectfour.backend.application.GridControllerInterface;
import ch.supsi.connectfour.frontend.controller.statusGame.StatusGameModelInterface;

public class StatusGameModel implements StatusGameModelInterface {
    private static StatusGameModel instance = null;
    private StatusGame statusGame;
    private final GridControllerInterface gridController;

    private StatusGameModel() {
        this.gridController = GridController.getInstance();
    }

    public static StatusGameModel getInstance() {
        return instance == null ? instance = new StatusGameModel() : instance;
    }

    @Override
    public void setStatusGame(StatusGame statusGame) {
        this.statusGame = statusGame;
    }

    @Override
    public boolean isInStateGame() {
        return statusGame.equals(StatusGame.GAME);
    }

    @Override
    public void preStartCondition() {
        gridController.initializeNewStructureForNewGame();
    }

    @Override
    public void gameCondition() {
        gridController.diceRollPerTurn();
    }
}
