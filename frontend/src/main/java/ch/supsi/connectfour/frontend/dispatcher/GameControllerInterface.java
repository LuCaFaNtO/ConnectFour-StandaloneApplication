package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.view.BoardView;

public interface GameControllerInterface {
    void insertPiece(final int column);
    void addUpdaterGrid(BoardView boardView);

}
