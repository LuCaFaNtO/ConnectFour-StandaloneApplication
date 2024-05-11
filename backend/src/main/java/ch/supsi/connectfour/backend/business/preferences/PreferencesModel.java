package ch.supsi.connectfour.backend.business.preferences;

import ch.supsi.connectfour.backend.application.preferences.PreferencesBusinessInterface;
import ch.supsi.connectfour.backend.dataaccess.preferences.PreferencesDataAccess;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PreferencesModel implements PreferencesBusinessInterface {
    private static PreferencesModel instance = null;
    private final PreferencesDataAccessInterface preferencesDataAccess;
    private Set<String> supportedColors;
    private Set<String> supportedSymbols;

    private PreferencesModel() {
        this.preferencesDataAccess = PreferencesDataAccess.getInstance();
        this.supportedColors = preferencesDataAccess.getSupportedColorsValues();
        this.supportedSymbols = preferencesDataAccess.getSupportedSymbols();
    }

    public static PreferencesModel getInstance() {
        return instance == null ? instance = new PreferencesModel() : instance;
    }

    @Override
    public Set<String> getSupportedColors() {
        return Collections.unmodifiableSet(supportedColors);
    }

    @Override
    public Set<String> getSupportedSymbols() {
        return Collections.unmodifiableSet(supportedSymbols);
    }


}
