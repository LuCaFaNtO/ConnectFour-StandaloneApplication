package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.application.observer.ColumnObserver;
import ch.supsi.connectfour.backend.application.observer.GridObserver;
import ch.supsi.connectfour.backend.business.domain.Cell;

public interface GridControllerInterface {
    void insertPiece(final int column) throws InsertPieceException, IllegalColumnException;
    void registerObserver(GridObserver observer);
    void removeObserver(GridObserver observer);

    //TODO: Stessa cosa sistemare interfaccia
    void registerObserver(ColumnObserver observer);
    void removeObserver(ColumnObserver observer);

    void notifyGridObserver();
    void notifyColumnObserver(final int column);
    Cell getCell();
}
