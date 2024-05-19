package ch.supsi.connectfour.backend.application.savingGame;

import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;
import ch.supsi.connectfour.backend.business.domain.Cell;

import java.io.File;
import java.io.IOException;

public interface SavingGameBusinessInterface {
    void saveGame(File file, Cell[][] grid, boolean turn) throws IllegalFIleException, IOException;
}
