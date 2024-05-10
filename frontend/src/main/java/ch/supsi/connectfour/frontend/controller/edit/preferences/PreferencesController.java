package ch.supsi.connectfour.frontend.controller.edit.preferences;

import ch.supsi.connectfour.frontend.dispatcher.edit.preferences.PreferencesControllerInterface;
import ch.supsi.connectfour.frontend.model.edit.preferences.PreferencesModel;

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
}
