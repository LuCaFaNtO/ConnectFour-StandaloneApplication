package ch.supsi.connectfour.backend.business;

import ch.supsi.connectfour.backend.application.GridBusinessInterface;
import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Grid;

public class GridModel implements GridBusinessInterface {
    private static GridModel gridModel = null;
    private final Grid grid;
    private int lastRowInserted;

    protected GridModel() {
        grid = new Grid();
    }

    public static GridModel getInstance() {
        return gridModel == null ? gridModel = new GridModel() : gridModel;
    }

    //TODO: return della riga (colonna già conosciuta)
    @Override
    public void insertPiece(final int column) throws InsertPieceException, IllegalColumnException {
        if(!grid.isColumnValid(column)) throw new IllegalColumnException("ERROR: THE COLUMN DOESN'T EXISTS");
        this.lastRowInserted = grid.getRowFromColumn(column);
        if(this.lastRowInserted == -1) throw new InsertPieceException("ERROR: THE COLUMN IS FULL OF PIECES!");
        grid.insertPiece(this.lastRowInserted, column);
        //if(row==0) throw
        //System.out.println(column);n
    }

    @Override
    public boolean isLastRowInserted() {
        return lastRowInserted == 0;
    }

    @Override
    public Cell getCell() {
        return grid.getModifiedCell();
    }
}
