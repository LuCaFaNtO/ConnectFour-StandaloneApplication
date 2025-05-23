package ch.supsi.connectfour.backend.application.preferences;

import ch.supsi.connectfour.backend.application.exceptions.IllegalPreferencesException;
import ch.supsi.connectfour.backend.business.domain.Piece;

import java.util.List;
import java.util.Set;

public interface PreferencesBusinessInterface {
    Set<String> getSupportedColors();

    Set<String> getSupportedSymbols();

    List<Piece> getCurrentPieces();

    void setCurrentPieces(List<Piece> currentPieces);

    void checkPiecesAreDifferent(List<Piece> pieces) throws IllegalPreferencesException;
}
