package ch.supsi.connectfour.backend.dataaccess.language;

import ch.supsi.connectfour.backend.business.language.TranslationsDataAccessInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TranslationsPropertiesDataAccess implements TranslationsDataAccessInterface {
    private static final String translationsResourceBundlePath = "i18n.labels";

    private static final String supportedLanguagesPath = "/supported-languages.properties";

    private static TranslationsPropertiesDataAccess instance = null;

    private final Properties languageProperties;

    private TranslationsPropertiesDataAccess() {
        languageProperties = getLanguageProperties();
    }

    public static TranslationsPropertiesDataAccess getInstance() {
        return instance == null ? instance = new TranslationsPropertiesDataAccess() : instance;
    }

    private Properties getLanguageProperties() {
        Properties languageProperties = new Properties();
        try {
            InputStream supportedLanguageTagsStream = this.getClass().getResourceAsStream(supportedLanguagesPath);
            languageProperties.load(supportedLanguageTagsStream);
        } catch (IOException ignored) {
            System.err.println("ERROR: Unable to load supported languages properties from " + supportedLanguagesPath);
        }
        return languageProperties;
    }

    @Override
    public String getTagFromKeyLanguage(String languageKey) {
        String defaultLanguageTag;
        defaultLanguageTag = languageProperties.getProperty(languageKey);
        return defaultLanguageTag;
    }

    @Override
    public ResourceBundle getTranslations(Locale locale) {
        final Properties translations = new Properties();
        ResourceBundle bundle;

        bundle = ResourceBundle.getBundle(
                translationsResourceBundlePath,
                locale);

        for (String key : bundle.keySet()) {
            translations.put(key, bundle.getString(key));
        }

        return bundle;
    }
}
