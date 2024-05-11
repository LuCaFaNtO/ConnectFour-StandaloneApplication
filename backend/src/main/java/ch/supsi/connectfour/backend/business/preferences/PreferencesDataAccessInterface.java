package ch.supsi.connectfour.backend.business.preferences;

import java.util.Set;

public interface PreferencesDataAccessInterface {
    Set<String> getSupportedColorsValues();
    Set<String> getSupportedSymbols();
}
