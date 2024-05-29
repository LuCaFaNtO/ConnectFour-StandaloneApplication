package ch.supsi.connectfour.backend.application.observer;

import ch.supsi.connectfour.backend.business.domain.Cell;

public interface UpdatePreferencesObserver {
    void updateGridWithNewPreferences(Cell[][] grid);

    void updateEmptyGrid(int col, int row);
}
