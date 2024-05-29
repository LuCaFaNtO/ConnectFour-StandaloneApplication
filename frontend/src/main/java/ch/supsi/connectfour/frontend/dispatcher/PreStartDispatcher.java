package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.controller.statusGame.StatusGameController;

public class PreStartDispatcher {
    private final StatusGameControllerInterface statusGameController;

    public PreStartDispatcher() {
        this.statusGameController = StatusGameController.getInstance();
    }

    public void startNewGame() {
        statusGameController.setStatusToGame();
    }
}
