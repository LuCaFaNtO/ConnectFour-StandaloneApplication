package ch.supsi.connectfour.backend.business.savingGame;

import ch.supsi.connectfour.backend.business.domain.Cell;

import java.io.File;
import java.io.IOException;

public interface SavingGameDataAccessInterface {
    void saveGameOnFile(File file, Cell[][] grid, boolean turn) throws IOException;
}
