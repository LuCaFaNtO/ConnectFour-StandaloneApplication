package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.view.BoardView;

public interface GameModelInterface {
    void insertPiece(final int column);

    void addUpdaterGrid(BoardView boardView);
}
