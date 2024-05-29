package ch.supsi.connectfour.frontend.controller.savingGame;

import ch.supsi.connectfour.backend.application.ObserverControllerInterface;
import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;
import ch.supsi.connectfour.backend.application.observer.GridObserver;
import ch.supsi.connectfour.backend.application.observer.ObserverController;
import ch.supsi.connectfour.frontend.dispatcher.SavingGameControllerInterface;
import ch.supsi.connectfour.frontend.model.savingGame.SavingGameModel;
import ch.supsi.connectfour.frontend.view.ErrorView;
import ch.supsi.connectfour.frontend.view.ErrorViewInterface;
import ch.supsi.connectfour.frontend.view.saving.SavingView;

import java.io.File;
import java.io.IOException;

public class SavingGameController implements SavingGameControllerInterface, GridObserver {
    private static SavingGameController instance = null;
    private final SavingGameModelInterface savingGameModel;
    private final ObserverControllerInterface observerController;
    private final ErrorViewInterface errorView;
    private final SavingGameViewInterface savingGameView;

    private SavingGameController() {
        this.savingGameModel = SavingGameModel.getInstance();
        this.observerController = ObserverController.getInstance();
        this.errorView = ErrorView.getInstance();
        this.savingGameView = SavingView.getInstance();

        this.observerController.registerGridObserver(this);
    }

    public static SavingGameController getInstance() {
        return instance == null ? instance = new SavingGameController() : instance;
    }

    @Override
    public void saveGame() {
        if (!savingGameModel.savingGameFileExists())
            saveGameAs();
        else if (!savingGameModel.isAlreadySave())
            save();
    }

    @Override
    public void saveGameAs() {
        File saveGameFile = savingGameView.getSaveFile();
        if (saveGameFile != null) {
            savingGameModel.setNewSavingGameFile(saveGameFile);
            save();
        }
    }

    private void save() {
        try {
            savingGameModel.saveGame();
        } catch (IllegalFIleException | IOException e) {
            savingGameModel.setNewSavingGameFile(null);
            errorView.showPopUpError(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public boolean openGame() {
        File openFile = savingGameView.getOpenFile();
        if (openFile != null) {
            savingGameModel.setNewSavingGameFile(openFile);
            return loadGame();
        }
        return false;
    }

    private boolean loadGame() {
        try {
            savingGameModel.loadGame();
            return true;
        } catch (IllegalFIleException e) {
            savingGameModel.setNewSavingGameFile(null);
            errorView.showPopUpError(e.getClass().getSimpleName(), e.getMessage());
            return false;
        }
    }

    @Override
    public void setNewSavingGameFile(File file) {
        savingGameModel.setNewSavingGameFile(file);
    }


    @Override
    public boolean isAlreadySave() {
        return savingGameModel.isAlreadySave();
    }


    @Override
    public void showSaveGamePopUp() {
        savingGameView.showSaveChoicePopUp();
    }

    @Override
    public void closeSaveGamePopUp() {
        savingGameView.closeStage();
    }

    @Override
    public void onGridUpdate() {
        savingGameModel.setAlreadySaved(false);
    }

    @Override
    public void resetSavingConditions() {
        savingGameModel.setAlreadySaved(false);
        savingGameModel.setNewSavingGameFile(null);
    }
}
