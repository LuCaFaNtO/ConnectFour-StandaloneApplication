package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.business.GridModel;

public class GridController implements GridControllerInterface {
    private static GridController instance = null;
    private final GridBusinessInterface gridModel;

    protected GridController() {
        gridModel = GridModel.getInstance();
    }

    public static GridController getInstance() {
        return instance == null ? new GridController() : instance;
    }

    @Override
    public void insertPiece(final int column) {
        gridModel.insertPiece(column);
    }
}
