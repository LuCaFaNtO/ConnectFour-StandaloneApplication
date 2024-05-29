package ch.supsi.connectfour.backend.application.savingGame;

import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface SavingGameBusinessInterface {
    void saveGame(File file, Cell[][] grid, boolean turn) throws IllegalFIleException, IOException;

    Cell[][] loadGridGame(File file, List<Player> players) throws IllegalFIleException;

    boolean loadTurnGame(File file) throws IllegalFIleException;
}
