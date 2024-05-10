package ch.supsi.connectfour.frontend.model.edit.preferences;

import ch.supsi.connectfour.backend.application.preferences.PreferencesController;
import ch.supsi.connectfour.backend.application.preferences.PreferencesControllerInterface;
import ch.supsi.connectfour.frontend.controller.edit.preferences.PreferencesModelInterface;

import java.util.Set;

public class PreferencesModel implements PreferencesModelInterface {
    private static PreferencesModel instance = null;
    private final PreferencesControllerInterface preferencesController;

    private PreferencesModel() {
        this.preferencesController = PreferencesController.getInstance();
    }

    public static PreferencesModel getInstance() {
        return instance == null ? instance = new PreferencesModel() : instance;
    }

    @Override
    public Set<String> getSupportedColors() {
        return preferencesController.getSupportedColors();
    }
}
