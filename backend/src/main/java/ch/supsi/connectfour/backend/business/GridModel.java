package ch.supsi.connectfour.backend.business;

import ch.supsi.connectfour.backend.application.GridBusinessInterface;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;

public class GridModel implements GridBusinessInterface {
    private static GridModel gridModel = null;
    private final Grid grid;

    protected GridModel() {
        grid = new Grid();
    }

    public static GridModel getInstance() {
        return gridModel == null ? gridModel = new GridModel() : gridModel;
    }

    //TODO: return della riga (colonna già conosciuta)
    @Override
    public void insertPiece(final int column) throws InsertPieceException {
        int row = grid.getRowFromColumn(column);
        if(row == -1) throw new InsertPieceException("ERROR: THE COLUMN IS FULL OF PIECES!");
        grid.insertPiece(row, column);
        //if(row==0) throw
        //System.out.println(column);
    }

    @Override
    public Cell getCell() {
        return grid.getModifiedCell();
    }
}
