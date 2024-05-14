package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.application.observer.ColumnObserver;
import ch.supsi.connectfour.backend.application.observer.FinishGameObserver;
import ch.supsi.connectfour.backend.application.observer.GridObserver;
import ch.supsi.connectfour.backend.business.domain.Player;

public interface ObserverControllerInterface {
    void registerGridObserver(GridObserver observer);
    void removeGridObserver(GridObserver observer);

    //TODO: Stessa cosa sistemare interfaccia
    void registerColumnObserver(ColumnObserver observer);
    void removeColumnObserver(ColumnObserver observer);

    void registerFinishGameObserver(FinishGameObserver observer);
    void removeFinishGameObserver(FinishGameObserver observer);

    void notifyGridObserver();
    void notifyColumnObserver(final int column);
    void notifyWin(String playerName);
    void notifyGridFull();
}
