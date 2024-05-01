package ch.supsi.connectfour.backend.business;

import ch.supsi.connectfour.backend.application.GridBusinessInterface;

public class GridModel implements GridBusinessInterface {
    private static GridModel gridModel = null;
    private final Grid grid;

    protected GridModel() {
        grid = new Grid();
    }

    public static GridModel getInstance() {
        return gridModel == null? gridModel = new GridModel() : gridModel;
    }

    //TODO: return della riga (colonna già conosciuta)
    @Override
    public void insertPiece(final int column) {
        System.out.println(column);
    }
}
