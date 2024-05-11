package ch.supsi.connectfour.backend.application.preferences;

import ch.supsi.connectfour.backend.application.exceptions.IllegalPreferencesException;
import ch.supsi.connectfour.backend.business.domain.Piece;

import java.util.List;
import java.util.Set;

public interface PreferencesControllerInterface {
    Set<String> getSupportedColors();
    Set<String> getSupportedSymbols();
    void setNewPreferences(List<Piece> pieces) throws IllegalPreferencesException;
}
