package ch.supsi.connectfour.backend.dataaccess.preferences;

import ch.supsi.connectfour.backend.business.preferences.PreferencesDataAccessInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class PreferencesDataAccess implements PreferencesDataAccessInterface {
    private static final String supportedColorsPath = "/supported-colors.properties";
    private static final String supportedSymbolsPath = "/supported-symbols.properties";
    private static PreferencesDataAccess instance = null;
    private final Properties colorsProperties;
    private final Properties symbolsProperties;

    private PreferencesDataAccess() {
        this.colorsProperties = getColorsProperties();
        this.symbolsProperties = getSymbolsProperties();
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
            System.err.println("ERROR: Unable to load supported colors properties from " + supportedColorsPath);
        }
        return colorsProperties;
    }

    private Properties getSymbolsProperties() {
        Properties symbolsProperties = new Properties();
        try {
            InputStream supportedSymbolTagsStream = this.getClass().getResourceAsStream(supportedSymbolsPath);
            symbolsProperties.load(supportedSymbolTagsStream);
        } catch (IOException ignored) {
            System.err.println("ERROR: Unable to load supported symbols properties from " + supportedSymbolsPath);
        }
        return symbolsProperties;
    }

    @Override
    public Set<String> getSupportedColorsValues() {
        return colorsProperties.values().stream().map(String::valueOf).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSupportedSymbols() {
        return symbolsProperties.values().stream().map(String::valueOf).collect(Collectors.toSet());
    }
}