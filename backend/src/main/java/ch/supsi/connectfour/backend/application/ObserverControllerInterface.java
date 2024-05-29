package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.application.observer.*;
import ch.supsi.connectfour.backend.business.domain.Cell;

public interface ObserverControllerInterface {
    void registerGridObserver(GridObserver observer);

    void removeGridObserver(GridObserver observer);

    void registerColumnObserver(ColumnObserver observer);

    void removeColumnObserver(ColumnObserver observer);

    void registerFinishGameObserver(FinishGameObserver observer);

    void removeFinishGameObserver(FinishGameObserver observer);

    void registerUpdaterPreferencesObserver(UpdatePreferencesObserver observer);

    void removeUpdatePreferencesObserver(UpdatePreferencesObserver observer);

    void registerTurnChangeObserver(TurnChangeObserverInterface observer);

    void removeTurnChangeObserver(TurnChangeObserverInterface observer);

    void registerSavingGameObserver(SavingGameObserver observer);

    void removeSavingGameObserver(SavingGameObserver observer);

    void notifyOnGridObserver();

    void notifyDisableColumnObserver(final int column);

    void notifyEnableColumnObserver(final int column);

    void notifyWin(String playerName, String playerSymbol);

    void notifyGridFull();

    void notifyAllGridUpdate(Cell[][] grid);

    void notifyChangeTurn(String playerName, String playerSymbol);

    void notifyEmptyGrid(int col, int row);

    void notifySaveGame();
}
