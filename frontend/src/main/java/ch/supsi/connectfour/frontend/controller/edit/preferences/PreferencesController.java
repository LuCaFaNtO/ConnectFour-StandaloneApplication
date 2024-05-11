package ch.supsi.connectfour.frontend.controller.edit.preferences;

import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.frontend.dispatcher.edit.preferences.PreferencesControllerInterface;
import ch.supsi.connectfour.frontend.model.edit.preferences.PreferencesModel;

import java.util.List;
import java.util.Set;

public class PreferencesController implements PreferencesControllerInterface {
    private static PreferencesController instance = null;
    private final PreferencesModelInterface preferencesModel;

    private PreferencesController() {
        this.preferencesModel = PreferencesModel.getInstance();
    }

    public static PreferencesController getInstance() {
        return instance == null ? instance = new PreferencesController() : instance;
    }

    @Override
    public Set<String> getSupportedColors() {
        return preferencesModel.getSupportedColors();
    }

    @Override
    public Set<String> getSupportedSymbols() {
        return preferencesModel.getSupportedSymbols();
    }

    @Override
    public List<Player> getPlayers() {
        return preferencesModel.getPlayers();
    }

    @Override
    public void setNewPreferences(List<Piece> pieces) {
        preferencesModel.setNewPreferences(pieces);
    }
}
