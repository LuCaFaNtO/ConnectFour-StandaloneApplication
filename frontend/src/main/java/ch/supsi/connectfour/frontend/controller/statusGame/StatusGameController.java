package ch.supsi.connectfour.frontend.controller.statusGame;

import ch.supsi.connectfour.backend.application.ObserverControllerInterface;
import ch.supsi.connectfour.backend.application.observer.FinishGameObserver;
import ch.supsi.connectfour.backend.application.observer.ObserverController;
import ch.supsi.connectfour.frontend.dispatcher.StatusGameControllerInterface;
import ch.supsi.connectfour.frontend.model.statusGame.StatusGame;
import ch.supsi.connectfour.frontend.model.statusGame.StatusGameModel;

import java.util.ArrayList;
import java.util.List;

public class StatusGameController implements StatusGameControllerInterface, FinishGameObserver {
    private static StatusGameController instance = null;
    private final StatusGameModelInterface statusGameModel;
    private final List<UpdateStatusInterface> updaterViewByChangeStatusList;

    private StatusGameController() {
        this.updaterViewByChangeStatusList = new ArrayList<>();
        this.statusGameModel = StatusGameModel.getInstance();

        ObserverControllerInterface observerController = ObserverController.getInstance();
        observerController.registerFinishGameObserver(this);
    }

    public static StatusGameController getInstance() {
        return instance == null ? instance = new StatusGameController() : instance;
    }

    @Override
    public void setStatusToPreStart() {
        statusGameModel.setStatusGame(StatusGame.PRESTART);
        preStartCondition();
    }

    @Override
    public void setStatusToGame() {
        statusGameModel.setStatusGame(StatusGame.GAME);
        gameCondition();
    }

    @Override
    public void setStatusToEnd() {
        statusGameModel.setStatusGame(StatusGame.END);
        endCondition();
    }

    private void preStartCondition() {
        for (UpdateStatusInterface updaterView : updaterViewByChangeStatusList)
            updaterView.updateViewStatusPreStart();
        statusGameModel.preStartCondition();
    }

    private void gameCondition() {
        for (UpdateStatusInterface updaterView : updaterViewByChangeStatusList)
            updaterView.updateViewStatusGame();
        statusGameModel.gameCondition();
    }

    private void endCondition() {
        for (UpdateStatusInterface updaterView : updaterViewByChangeStatusList)
            updaterView.updateViewStatusEnd();
    }

    @Override
    public boolean isInStateGame() {
        return statusGameModel.isInStateGame();
    }

    @Override
    public void addUpdateViewByStatus(UpdateStatusInterface updaterViewByStatus) {
        this.updaterViewByChangeStatusList.add(updaterViewByStatus);
    }

    @Override
    public void win(String playerName, String playerSymbol) {
        setStatusToEnd();
    }

    @Override
    public void gridFull() {
        setStatusToEnd();
    }
}
