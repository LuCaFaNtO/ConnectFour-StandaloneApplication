package ch.supsi.connectfour.backend.business.savingGame;

import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;
import ch.supsi.connectfour.backend.application.savingGame.SavingGameBusinessInterface;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.backend.dataaccess.savingGame.SavingGameDataAccess;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @Override
    public Cell[][] loadGridGame(File file, List<Player> players) throws IllegalFIleException{
        if(file == null || !file.getName().contains(".json")) throw new IllegalFIleException("ERROR: an error occurs during loading grid from file");
        return savingGameDataAccess.loadGridFromFile(file, players);
    }

    @Override
    public boolean loadTurnGame(File file) throws IllegalFIleException{
        if(file == null || !file.getName().contains(".json")) throw new IllegalFIleException("ERROR: an error occurs during loading turn from file");
        return savingGameDataAccess.loadTurnFromFile(file);
    }
}
