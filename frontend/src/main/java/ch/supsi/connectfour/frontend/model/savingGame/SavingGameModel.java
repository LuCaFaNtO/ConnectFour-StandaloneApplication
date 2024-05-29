package ch.supsi.connectfour.frontend.model.savingGame;

import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;
import ch.supsi.connectfour.backend.application.savingGame.SavingGameController;
import ch.supsi.connectfour.backend.application.savingGame.SavingGameControllerInterface;
import ch.supsi.connectfour.frontend.controller.savingGame.SavingGameModelInterface;

import java.io.File;
import java.io.IOException;

public class SavingGameModel implements SavingGameModelInterface {
    private static SavingGameModel instance = null;
    private final SavingGameControllerInterface savingGameController;
    private File currentGameSavingFile;
    private boolean alreadySaved;

    private SavingGameModel() {
        this.savingGameController = SavingGameController.getInstance();
        this.alreadySaved = false;
        this.currentGameSavingFile = null;
    }

    public static SavingGameModel getInstance() {
        return instance == null ? instance = new SavingGameModel() : instance;
    }

    @Override
    public void saveGame() throws IllegalFIleException, IOException {
        savingGameController.saveGame(currentGameSavingFile);
        alreadySaved = true;
    }

    @Override
    public void loadGame() throws IllegalFIleException {
        savingGameController.loadGame(currentGameSavingFile);
        alreadySaved = true;
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
    public void setAlreadySaved(boolean alreadySaved) {
        this.alreadySaved = alreadySaved;
    }
}
