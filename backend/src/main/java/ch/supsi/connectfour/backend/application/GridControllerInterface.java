package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.application.observer.GridObserver;
import ch.supsi.connectfour.backend.business.Cell;

public interface GridControllerInterface {
    void insertPiece(final int column) throws InsertPieceException;
    void registerObserver(GridObserver observer);
    void removeObserver(GridObserver observer);
    void notifyObservers();
    Cell getCell();
}
