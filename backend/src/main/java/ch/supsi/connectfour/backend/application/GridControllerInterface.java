package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.application.observer.ColumnObserver;
import ch.supsi.connectfour.backend.application.observer.GridObserver;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Player;

import java.util.List;

public interface GridControllerInterface {
    void insertPiece(final int column) throws InsertPieceException, IllegalColumnException;
    void registerGridObserver(GridObserver observer);
    void removeObserver(GridObserver observer);

    //TODO: Stessa cosa sistemare interfaccia
    void registerColumnObserver(ColumnObserver observer);
    void removeObserver(ColumnObserver observer);

    void notifyGridObserver();
    void notifyColumnObserver(final int column);
    Cell getCell();

    List<Player> getPlayers();
    Player getPlayer();
}
