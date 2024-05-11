package ch.supsi.connectfour.backend.application.preferences;

import ch.supsi.connectfour.backend.business.preferences.PreferencesModel;

import java.util.Set;

public class PreferencesController implements PreferencesControllerInterface {
    private static PreferencesController instance;
    private final PreferencesBusinessInterface preferencesModel;

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


}
