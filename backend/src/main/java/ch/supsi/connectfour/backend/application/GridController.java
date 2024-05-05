package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.business.Cell;
import ch.supsi.connectfour.backend.business.GridModel;
import ch.supsi.connectfour.backend.application.observer.GridObserver;

import java.util.ArrayList;
import java.util.List;

public class GridController implements GridControllerInterface {
    private static GridController instance = null;
    private final GridBusinessInterface gridModel;
    private List<GridObserver> observers = new ArrayList<>();

    protected GridController() {
        gridModel = GridModel.getInstance();
    }

    public static GridController getInstance() {
        return instance == null ? new GridController() : instance;
    }

    @Override
    public void insertPiece(final int column) throws InsertPieceException {
        gridModel.insertPiece(column);
        notifyObservers();
    }

    @Override
    public void registerObserver(GridObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(GridObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (GridObserver observer : observers) {
            observer.onGridUpdate();
        }
    }

    @Override
    public Cell getCell() {
        return gridModel.getCell();
    }


}
