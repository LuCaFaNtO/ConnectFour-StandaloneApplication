package ch.supsi.connectfour.backend.business.savingGame;

import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;
import ch.supsi.connectfour.backend.application.savingGame.SavingGameBusinessInterface;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.dataaccess.savingGame.SavingGameDataAccess;

import java.io.File;
import java.io.IOException;

public class SavingGameModel implements SavingGameBusinessInterface {
    private static SavingGameModel instance = null;
    private final SavingGameDataAccessInterface savingGameDataAccess;

    private SavingGameModel() {
        this.savingGameDataAccess = SavingGameDataAccess.getInstance();
    }

    public static SavingGameModel getInstance() {
        return instance == null ? instance = new SavingGameModel() : instance;
    }

    @Override
    public void saveGame(File file, Cell[][] grid, boolean turn) throws IllegalFIleException, IOException {
        if(file == null) throw new IllegalFIleException("ERROR: an error occurs during saving file");

        File saveFile = new File(file.getAbsolutePath());
        //noinspection ResultOfMethodCallIgnored
        saveFile.createNewFile();
        savingGameDataAccess.saveGameOnFile(file, grid, turn);
    }
}
