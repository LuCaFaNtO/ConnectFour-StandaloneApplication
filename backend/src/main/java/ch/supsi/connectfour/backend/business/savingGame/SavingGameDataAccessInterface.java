package ch.supsi.connectfour.backend.business.savingGame;

import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Player;

import java.io.File;
import java.util.List;

public interface SavingGameDataAccessInterface {
    void saveGameOnFile(File file, Cell[][] grid, boolean turn) throws IllegalFIleException;
    boolean loadTurnFromFile(File file) throws IllegalFIleException;
    Cell[][] loadGridFromFile(File file, List<Player> players) throws IllegalFIleException;
}
