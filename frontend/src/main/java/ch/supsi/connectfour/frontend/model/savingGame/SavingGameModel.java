package ch.supsi.connectfour.frontend.model.savingGame;

import ch.supsi.connectfour.backend.application.ObserverControllerInterface;
import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;
import ch.supsi.connectfour.backend.application.observer.GridObserver;
import ch.supsi.connectfour.backend.application.observer.ObserverController;
import ch.supsi.connectfour.backend.application.savingGame.SavingGameController;
import ch.supsi.connectfour.backend.application.savingGame.SavingGameControllerInterface;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.frontend.controller.savingGame.SavingGameModelInterface;
import ch.supsi.connectfour.frontend.dispatcher.SaveGameChoicePopUpDispatcher;
import ch.supsi.connectfour.frontend.model.UpdateGridInterface;
import ch.supsi.connectfour.frontend.view.ErrorView;
import ch.supsi.connectfour.frontend.view.ErrorViewInterface;

import java.io.File;
import java.io.IOException;

public class SavingGameModel implements SavingGameModelInterface, GridObserver {
    private static SavingGameModel instance = null;
    private final SavingGameControllerInterface savingGameController;
    private final ObserverControllerInterface observerController;
    private final ErrorViewInterface errorView;
    private File currentGameSavingFile;
    private boolean alreadySaved;

    private SaveGameChoicePopUpDispatcher saveGameChoicePopUpDispatcher;

    private SavingGameModel() {
        this.savingGameController = SavingGameController.getInstance();
        this.observerController = ObserverController.getInstance();
        this.errorView = ErrorView.getInstance();
        this.alreadySaved = false;

        this.observerController.registerGridObserver(this);
    }

    public static SavingGameModel getInstance() {
        return instance == null ? instance = new SavingGameModel() : instance;
    }

    @Override
    public void saveGame() {
        try {
            savingGameController.saveGame(currentGameSavingFile);
            alreadySaved = true;
        } catch (IllegalFIleException | IOException e) {
            currentGameSavingFile = null;
            errorView.showPopUpError(e.getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public boolean loadGame() {
        try {
            savingGameController.loadGame(currentGameSavingFile);
            alreadySaved = true;
            return true;
        } catch (IllegalFIleException e) {
            currentGameSavingFile = null;
            errorView.showPopUpError(e.getClass().getSimpleName(), e.getMessage());
            return false;
        }
    }

    @Override
    public void setNewSavingGameFile(File file) {
        currentGameSavingFile = file;
    }

    @Override
    public boolean savingGameFileExists() {
        return currentGameSavingFile != null;
    }

    @Override
    public boolean isAlreadySave() {
        return alreadySaved;
    }

    @Override
    public void addSavingGamePopUp(SaveGameChoicePopUpDispatcher saveGameChoicePopUpDispatcher) {
        this.saveGameChoicePopUpDispatcher = saveGameChoicePopUpDispatcher;
    }

    @Override
    public void showSaveGamePopUp() {
        saveGameChoicePopUpDispatcher.showSaveChoicePopUp();
    }

    @Override
    public void onGridUpdate() {
        this.alreadySaved = false;
    }
}
