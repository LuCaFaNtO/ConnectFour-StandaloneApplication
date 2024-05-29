package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.model.UpdateGridInterface;

public interface GameControllerInterface {
    void insertPiece(final int column);

    void addUpdaterGrid(UpdateGridInterface boardView);
}
