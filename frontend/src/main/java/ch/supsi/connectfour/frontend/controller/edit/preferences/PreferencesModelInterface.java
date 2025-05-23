package ch.supsi.connectfour.frontend.controller.edit.preferences;

import ch.supsi.connectfour.backend.application.exceptions.IllegalPreferencesException;
import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;

import java.util.List;
import java.util.Set;

public interface PreferencesModelInterface {
    Set<String> getSupportedColors();

    Set<String> getSupportedSymbols();

    List<Player> getPlayers();

    void setNewPreferences(List<Piece> pieces) throws IllegalPreferencesException;
}
