package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Player;

import java.util.List;

public interface GridControllerInterface {
    void insertPiece(final int column) throws InsertPieceException, IllegalColumnException;

    Cell getCell();

    List<Player> getPlayers();
    Cell[][] getGrid();
}
