package ch.supsi.connectfour.backend.business;

import ch.supsi.connectfour.backend.application.GridBusinessInterface;
import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Grid;
import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;

import java.util.List;
import java.util.Random;

public class GridModel implements GridBusinessInterface {
    private static GridModel gridModel = null;
    private final Grid grid;
    private int lastRowInserted;
    private Player player1;
    private Player player2;

    //TODO: Sistemare turni
    private boolean turn = new Random().nextBoolean(); //false -> player1     true -> player2

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
        grid.insertPiece(this.lastRowInserted, column, getCurrentPlayer());
    }

    @Override
    public void changeTurn() {
        turn = !getTurn();
    }

    @Override
    public boolean isGridFull() {
        return grid.isFull();
    }

    @Override
    public boolean checkWin(int column) {
        Player currentPlayer = getCurrentPlayer();
        int cont;

        //controllo in verticale
        cont = 1;
        for (int row = lastRowInserted + 1; row < Grid.NUM_ROWS; row++) {
            Player player = grid.getCell(row, column).getPlayer();
            if (player != null && player.equals(currentPlayer)) {
                cont++;
                if (cont == 4)
                    return true;
            } else
                break;
        }

        //controllo in orizzontale dx
        cont = 1;
        for (int col = column + 1; col < Grid.NUM_COLS; col++) {
            Player player = grid.getCell(lastRowInserted, col).getPlayer();
            if (player != null && player.equals(currentPlayer)) {
                cont++;
                if (cont == 4)
                    return true;
            } else
                break;
        }
        //controllo in orizzontale sx
        for (int col = column - 1; col >= 0; col--) {
            Player player = grid.getCell(lastRowInserted, col).getPlayer();
            if (player != null && player.equals(currentPlayer)) {
                cont++;
                if (cont == 4)
                    return true;
            } else
                break;
        }

        //controllo in obliquo da sx basso a dx alto
        cont = 1;
        for (int col = column + 1, row = lastRowInserted - 1; col < Grid.NUM_COLS && row >= 0; col++, row--) {
            Player player = grid.getCell(row, col).getPlayer();
            if (player != null && player.equals(currentPlayer)) {
                cont++;
                if (cont == 4)
                    return true;
            } else
                break;
        }
        for (int col = column - 1, row = lastRowInserted + 1; col >= 0 && row < Grid.NUM_ROWS; col--, row++) {
            Player player = grid.getCell(row, col).getPlayer();
            if (player != null && player.equals(currentPlayer)) {
                cont++;
                if (cont == 4)
                    return true;
            } else
                break;
        }
        //controllo in obliquo da sx alto a dx basso
        cont = 1;
        for (int col = column - 1, row = lastRowInserted - 1; col >= 0 && row >= 0; col--, row--) {
            Player player = grid.getCell(row, col).getPlayer();
            if (player != null && player.equals(currentPlayer)) {
                cont++;
                if (cont == 4)
                    return true;
            } else
                break;
        }
        for (int col = column + 1, row = lastRowInserted + 1; col < Grid.NUM_COLS  && row < Grid.NUM_ROWS; col++, row++) {
            Player player = grid.getCell(row, col).getPlayer();
            if (player != null && player.equals(currentPlayer)) {
                cont++;
                if (cont == 4)
                    return true;
            } else
                break;
        }

        return false;
    }

    @Override
    public Player getWinner() {
        return getCurrentPlayer();
    }

    @Override
    public int getNumberOfGridsColumn() {
        return Grid.NUM_COLS;
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
    public boolean arePlayersNull() {
        return player1 == null && player2 == null;
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

    @Override
    public Player getCurrentPlayer() {
        return getTurn() ? player1 : player2;
    }

    @Override
    public void initializeNewStructureForNewGame() {
        grid.initializeNewGrid();
    }

    @Override
    public void diceRollPerTurn() {
        turn = new Random().nextBoolean();
    }

    @Override
    public boolean getTurn() {
        return turn;
    }
}
