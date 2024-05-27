package ch.supsi.connectfour.frontend.model.statusGame;

import ch.supsi.connectfour.backend.application.GridController;
import ch.supsi.connectfour.backend.application.GridControllerInterface;
import ch.supsi.connectfour.frontend.controller.statusGame.StatusGameModelInterface;

import java.util.ArrayList;
import java.util.List;

public class StatusGameModel implements StatusGameModelInterface {
    private static StatusGameModel instance = null;
    private StatusGame statusGame;
    private List<UpdateStatusViewInterface> updaterViewByChangeStatusList;
    private GridControllerInterface gridController;

    private StatusGameModel() {
        this.updaterViewByChangeStatusList = new ArrayList<>();
        this.gridController = GridController.getInstance();
    }

    public static StatusGameModel getInstance() {
        return instance == null ? instance = new StatusGameModel() : instance;
    }

    @Override
    public void setStatusGame(StatusGame statusGame) {
        this.statusGame = statusGame;
        onChangeStatusUpdate();

    }

    @Override
    public boolean isInStateGame() {
        return statusGame.equals(StatusGame.GAME);
    }

    @Override
    public void addUpdateViewByStatus(UpdateStatusViewInterface updaterViewByStatus) {
        this.updaterViewByChangeStatusList.add(updaterViewByStatus);
    }

    @Override
    public void removeUpdateViewByStatus(UpdateStatusViewInterface updaterViewByStatus) {
        if(updaterViewByStatus != null && updaterViewByChangeStatusList.contains(updaterViewByStatus))
            updaterViewByChangeStatusList.remove(updaterViewByStatus);
    }

    @Override
    public void onChangeStatusUpdate(){
        switch(statusGame){
            case PRESTART:
                preStartCondition();
                break;
            case GAME:
                gameStartCondition();
                break;
            case END:
                endCondition();
        }
    }

    private void preStartCondition() {
        for(UpdateStatusViewInterface updaterView : updaterViewByChangeStatusList)
            updaterView.updateViewStatusPreStart();
        gridController.initializeNewStructureForNewGame();
    }

    private void gameStartCondition() {
        for(UpdateStatusViewInterface updaterView : updaterViewByChangeStatusList)
            updaterView.updateViewStatusGame();
        gridController.diceRollPerTurn();
    }

    private void endCondition() {
        for(UpdateStatusViewInterface updaterView : updaterViewByChangeStatusList)
            updaterView.updateViewStatusEnd();
    }
}
