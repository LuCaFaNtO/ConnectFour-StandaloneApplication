package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.dispatcher.ColumnsSelectorDispatcher;
import ch.supsi.connectfour.frontend.model.UpdateGridInterface;
import ch.supsi.connectfour.frontend.view.BoardView;
import ch.supsi.connectfour.frontend.view.UpdateViewInterface;

public interface GameModelInterface {
    void insertPiece(final int column);

    void addUpdaterGrid(UpdateGridInterface boardView);

    void addDisableColumn(ColumnsSelectorDispatcher columnsSelectorDispatcher);
}
