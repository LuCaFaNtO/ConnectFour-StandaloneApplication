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

    private SavingGameModel() {
        this.savingGameController = SavingGameController.getInstance();
    }

    public static SavingGameModel getInstance() {
        return instance == null ? instance = new SavingGameModel() : instance;
    }

    @Override
    public void saveGame() {
        try {
            savingGameController.saveGame(currentGameSavingFile);
        } catch (IllegalFIleException e) {
            System.out.println("Error saving game: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException");
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
}
