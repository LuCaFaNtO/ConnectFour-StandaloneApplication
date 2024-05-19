package ch.supsi.connectfour.backend.business.savingGame;

import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface SavingGameDataAccessInterface {
    void saveGameOnFile(File file, Cell[][] grid, boolean turn) throws IOException;
    boolean loadTurnFromFile(File file) throws IOException;
    Cell[][] loadGridFromFile(File file, List<Player> players) throws IOException;
}
