package ch.supsi.connectfour.frontend.controller.statusGame;

import ch.supsi.connectfour.frontend.dispatcher.StatusGameControllerInterface;
import ch.supsi.connectfour.frontend.model.statusGame.StatusGame;
import ch.supsi.connectfour.frontend.model.statusGame.StatusGameModel;
import ch.supsi.connectfour.frontend.model.statusGame.UpdateStatusViewInterface;

public class StatusGameController implements StatusGameControllerInterface {
    private static StatusGameController instance = null;
    private final StatusGameModelInterface statusGameModel;

    private StatusGameController() {
        this.statusGameModel = StatusGameModel.getInstance();
    }

    public static StatusGameController getInstance() {
        return instance == null ? instance = new StatusGameController() : instance;
    }

    @Override
    public void setStatusToPreStart() {
        statusGameModel.setStatusGame(StatusGame.PRESTART);
    }

    @Override
    public void setStatusToGame() {
        statusGameModel.setStatusGame(StatusGame.GAME);
    }

    @Override
    public void setStatusToEnd() {
        statusGameModel.setStatusGame(StatusGame.END);
    }

    @Override
    public boolean isInStateGame() {
        return statusGameModel.isInStateGame();
    }

    @Override
    public void addUpdateViewByStatus(UpdateStatusViewInterface updaterViewByStatus) {
        statusGameModel.addUpdateViewByStatus(updaterViewByStatus);
    }

    @Override
    public void removeUpdateViewByStatus(UpdateStatusViewInterface updaterViewByStatus) {
        statusGameModel.removeUpdateViewByStatus(updaterViewByStatus);
    }
}
