package ch.supsi.connectfour.frontend.dispatcher.edit.preferences;

import java.util.Set;

public interface PreferencesControllerInterface {
    Set<String> getSupportedColors();
    Set<String> getSupportedSymbols();
}
