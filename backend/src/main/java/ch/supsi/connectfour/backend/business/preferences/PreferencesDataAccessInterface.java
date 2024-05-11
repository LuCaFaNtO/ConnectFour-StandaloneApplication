package ch.supsi.connectfour.backend.business.preferences;

import ch.supsi.connectfour.backend.business.domain.Piece;

import java.util.List;
import java.util.Set;

public interface PreferencesDataAccessInterface {
    Set<String> getSupportedColorsValues();
    Set<String> getSupportedSymbols();
}
