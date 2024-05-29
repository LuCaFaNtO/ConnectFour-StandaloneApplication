package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.business.domain.Cell;

public interface UpdateGridInterface {
    void updateGrid(Cell cell);

    void updateGridWithNewPreferences(Cell[][] grid);

    void updateEmptyGrid(int col, int row);
}
