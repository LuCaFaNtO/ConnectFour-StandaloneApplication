package ch.supsi.connectfour.backend.application.observer;

import ch.supsi.connectfour.backend.application.ObserverControllerInterface;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class ObserverController implements ObserverControllerInterface {
    private static ObserverController instance = null;

    private final List<GridObserver> gridObservers = new ArrayList<>();
    private final List<ColumnObserver> columnObservers = new ArrayList<>();
    private final List<FinishGameObserver> finishGameObservers = new ArrayList<>();
    private final List<UpdatePreferencesObserver> updatePreferencesObservers = new ArrayList<>();

    private ObserverController() {
    }

    public static ObserverController getInstance() {
        return instance == null ? instance = new ObserverController() : instance;
    }

    @Override
    public void registerGridObserver(GridObserver observer) {
        gridObservers.add(observer);
    }

    @Override
    public void removeGridObserver(GridObserver observer) {
        gridObservers.remove(observer);
    }

    @Override
    public void registerColumnObserver(ColumnObserver observer) {
        columnObservers.add(observer);
    }

    @Override
    public void removeColumnObserver(ColumnObserver observer) {
        columnObservers.remove(observer);
    }

    @Override
    public void registerFinishGameObserver(FinishGameObserver observer) {
        finishGameObservers.add(observer);
    }

    @Override
    public void removeFinishGameObserver(FinishGameObserver observer) {
        finishGameObservers.remove(observer);
    }

    @Override
    public void registerUpdaterPreferencesObserver(UpdatePreferencesObserver observer) {
        updatePreferencesObservers.add(observer);
    }

    @Override
    public void removeUpdatePreferencesObserver(UpdatePreferencesObserver observer) {
        updatePreferencesObservers.remove(observer);
    }

    @Override
    public void notifyGridObserver() {
        for (GridObserver observer : gridObservers) {
            observer.onGridUpdate();
        }
    }

    @Override
    public void notifyColumnObserver(final int column) {
        for(ColumnObserver observer : columnObservers)
            observer.disableColumn(column);
    }

    @Override
    public void notifyWin(String playerName, String playerSymbol) {
        for(FinishGameObserver observer : finishGameObservers)
            observer.win(playerName, playerSymbol);
    }

    @Override
    public void notifyGridFull() {
        for(FinishGameObserver observer : finishGameObservers)
            observer.gridFull();
    }

    @Override
    public void notifyUpdatePreferences(Cell[][] grid) {
        for(UpdatePreferencesObserver updatePreferencesObserver : updatePreferencesObservers)
            updatePreferencesObserver.updateGridWithNewPreferences(grid);
    }
}
