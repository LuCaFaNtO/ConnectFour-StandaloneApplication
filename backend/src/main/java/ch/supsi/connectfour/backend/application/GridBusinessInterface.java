package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.application.exceptions.InsertPieceException;
import ch.supsi.connectfour.backend.business.Cell;

public interface GridBusinessInterface {
    void insertPiece(final int column) throws InsertPieceException;
    Cell getCell();
}
