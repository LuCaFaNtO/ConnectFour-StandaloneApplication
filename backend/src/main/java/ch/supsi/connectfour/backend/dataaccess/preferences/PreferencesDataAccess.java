package ch.supsi.connectfour.backend.dataaccess.preferences;

import ch.supsi.connectfour.backend.business.preferences.PreferencesDataAccessInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class PreferencesDataAccess implements PreferencesDataAccessInterface {
    private static PreferencesDataAccess instance = null;
    private final Properties colorsProperties;
    private static final String supportedColorsPath = "/supported-colors.properties";

    private PreferencesDataAccess() {
        this.colorsProperties = getColorsProperties();
    }

    public static PreferencesDataAccess getInstance() {
        return instance == null ? instance = new PreferencesDataAccess() : instance;
    }

    private Properties getColorsProperties() {
        Properties colorsProperties = new Properties();
        try {
            InputStream supportedLanguageTagsStream = this.getClass().getResourceAsStream(supportedColorsPath);
            colorsProperties.load(supportedLanguageTagsStream);
        } catch (IOException ignored) {
            System.err.println("ERROR: Unable to load supported languages properties from " + supportedColorsPath);
        }
        return colorsProperties;
    }

    @Override
    public Set<String> getSupportedColorsValues() {
        Set<String> supportedColorsValues = new HashSet<>();
        supportedColorsValues = colorsProperties.values().stream().map(String::valueOf).collect(Collectors.toSet());
        return supportedColorsValues;
    }
}
