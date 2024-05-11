package ch.supsi.connectfour.backend.application.preferences;

import ch.supsi.connectfour.backend.application.GridBusinessInterface;
import ch.supsi.connectfour.backend.application.exceptions.IllegalPreferencesException;
import ch.supsi.connectfour.backend.business.GridModel;
import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.backend.business.preferences.PreferencesModel;

import java.util.List;
import java.util.Set;

public class PreferencesController implements PreferencesControllerInterface {
    private static PreferencesController instance;
    private final PreferencesBusinessInterface preferencesModel;
    private final GridBusinessInterface gridModel;

    private PreferencesController() {
        this.preferencesModel = PreferencesModel.getInstance();
        this.gridModel = GridModel.getInstance();
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
    public void setNewPreferences(List<Piece> pieces) throws IllegalPreferencesException {
        preferencesModel.checkPiecesAreDifferent(pieces);
        gridModel.setNewPlayerPreferences(pieces);
    }
}
