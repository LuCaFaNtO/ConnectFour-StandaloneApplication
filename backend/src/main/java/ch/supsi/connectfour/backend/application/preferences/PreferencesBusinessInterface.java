package ch.supsi.connectfour.backend.application.preferences;

import java.util.Set;

public interface PreferencesBusinessInterface {
    Set<String> getSupportedColors();
    Set<String> getSupportedSymbols();
}
