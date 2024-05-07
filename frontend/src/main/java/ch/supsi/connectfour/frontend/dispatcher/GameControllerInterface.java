package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.model.UpdateGridInterface;
import ch.supsi.connectfour.frontend.view.BoardView;
import ch.supsi.connectfour.frontend.view.UpdateViewInterface;

public interface GameControllerInterface {
    void insertPiece(final int column);
    void addUpdaterGrid(UpdateGridInterface boardView);
}
