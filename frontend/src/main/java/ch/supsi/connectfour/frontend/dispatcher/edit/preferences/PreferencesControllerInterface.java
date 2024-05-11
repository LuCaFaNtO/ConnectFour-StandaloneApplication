package ch.supsi.connectfour.frontend.dispatcher.edit.preferences;

import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;

import java.util.List;
import java.util.Set;

public interface PreferencesControllerInterface {
    Set<String> getSupportedColors();
    Set<String> getSupportedSymbols();
    List<Player> getPlayers();
    void setNewPreferences(List<Piece> pieces);
}
