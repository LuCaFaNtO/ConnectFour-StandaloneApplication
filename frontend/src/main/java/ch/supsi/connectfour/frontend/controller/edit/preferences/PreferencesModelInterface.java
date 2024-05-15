package ch.supsi.connectfour.frontend.controller.edit.preferences;

import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.frontend.dispatcher.edit.preferences.PreferencesDispatcher;
import ch.supsi.connectfour.frontend.model.UpdateGridInterface;

import java.util.List;
import java.util.Set;

public interface PreferencesModelInterface {
    Set<String> getSupportedColors();
    Set<String> getSupportedSymbols();
    List<Player> getPlayers();
    void setNewPreferences(List<Piece> pieces);
    void addUpdaterGrid(UpdateGridInterface updateGrid);
    void addPreferencesDispatcher(PreferencesDispatcher preferencesDispatcher);
    void showPreferencesPage();
}
