package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.application.exceptions.IllegalColumnException;
import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.frontend.dispatcher.ColumnsSelectorDispatcher;
import ch.supsi.connectfour.frontend.model.UpdateGridInterface;

public interface GameModelInterface {
    void insertPiece(final int column) throws InsertPieceException, IllegalColumnException;

    Cell getCell();
}
