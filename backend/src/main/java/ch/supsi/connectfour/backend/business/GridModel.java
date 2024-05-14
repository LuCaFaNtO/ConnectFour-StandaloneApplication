package ch.supsi.connectfour.backend.business;

import ch.supsi.connectfour.backend.application.GridBusinessInterface;
import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Grid;
import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GridModel implements GridBusinessInterface {
    private static GridModel gridModel = null;
    private final Grid grid;
    private int lastRowInserted;
    private Player player1;
    private Player player2;

    //TODO: Sistemare turni
    private boolean turn = false;

    protected GridModel() {
        grid = new Grid();
    }

    public static GridModel getInstance() {
        return gridModel == null ? gridModel = new GridModel() : gridModel;
    }

    @Override
    public void insertPiece(final int column) throws InsertPieceException, IllegalColumnException {
        if (!grid.isColumnValid(column)) throw new IllegalColumnException("ERROR: THE COLUMN DOESN'T EXISTS");
        this.lastRowInserted = grid.getRowFromColumn(column);
        if (this.lastRowInserted == -1) throw new InsertPieceException("ERROR: THE COLUMN IS FULL OF PIECES!");
        grid.insertPiece(this.lastRowInserted, column, turn ? player1 : player2);
    }

    @Override
    public void changeTurn(){
        turn = !turn;
    }

    @Override
    public boolean isGridFull() {
        return grid.isFull();
    }

    @Override
    public boolean checkWin() {
        return false;
    }

    @Override
    public Player getWinner() {
        return turn ? player1 : player2;
    }

    @Override
    public boolean isLastRowInserted() {
        return lastRowInserted == 0;
    }

    @Override
    public void initializePlayers(List<Piece> defaultPlayerPieces) {
        player1 = new Player("Player1", defaultPlayerPieces.get(0));
        player2 = new Player("Player2", defaultPlayerPieces.get(1));
    }

    @Override
    public List<Player> getPlayers() {
        return List.of(player1, player2);
    }

    @Override
    public void setNewPlayerPreferences(List<Piece> newPlayerPieces) {
        Piece newPiece1 = newPlayerPieces.get(0);
        Piece newPiece2 = newPlayerPieces.get(1);

        player1.setPiece(newPiece1);
        player2.setPiece(newPiece2);
    }

    @Override
    public Cell[][] getGrid() {
        return grid.getGrid();
    }

    @Override
    public Cell getCell() {
        return grid.getModifiedCell();
    }
}
