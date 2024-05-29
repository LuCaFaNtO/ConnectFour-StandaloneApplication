package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;

import java.util.List;

public interface GridBusinessInterface {
    void insertPiece(final int column) throws InsertPieceException, IllegalColumnException;

    Cell getCell();

    boolean isLastRowInserted();

    void initializePlayers(List<Piece> defaultPlayerPieces);

    List<Player> getPlayers();

    boolean arePlayersNull();

    void setNewPlayerPreferences(List<Piece> newPlayerPieces);

    Cell[][] getGrid();

    void changeTurn();

    boolean isGridFull();

    boolean checkWin(int column);

    Player getWinner();

    int getNumberOfGridsColumn();

    Player getCurrentPlayer();

    void initializeNewStructureForNewGame();

    void initializeNewStructureForNewGame(Cell[][] newGrid, boolean turn);

    void diceRollPerTurn();

    boolean getTurn();

    boolean isColumnFull(int column);
}
