package ch.supsi.connectfour.backend.business.language;

import ch.supsi.connectfour.backend.application.language.TranslationsBusinessInterface;
import ch.supsi.connectfour.backend.dataaccess.language.TranslationsPropertiesDataAccess;

import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class TranslationsModel implements TranslationsBusinessInterface {
    private static TranslationsModel instance = null;
    private final TranslationsDataAccessInterface translationsDataAccess;
    private final Set<String> supportedLanguageKeys;

    private TranslationsModel() {
        translationsDataAccess = TranslationsPropertiesDataAccess.getInstance();
        supportedLanguageKeys = translationsDataAccess.getSupportedLanguageKeys();
    }

    public static TranslationsModel getInstance() {
        return instance == null? instance = new TranslationsModel() : instance;
    }

    @Override
    public String getTagFromKeyLanguage(String languageKey) {
        return translationsDataAccess.getTagFromKeyLanguage(languageKey);
    }

    @Override
    public Set<String> getSupportedLanguages() {
        return Collections.unmodifiableSet(supportedLanguageKeys);
    }

    @Override
    public void setLocaleDefault() {
        Locale.setDefault(Locale.US);
    }

    @Override
    public ResourceBundle changeLanguage(String languageTag) {
        return translationsDataAccess.getTranslations(Locale.forLanguageTag(languageTag));
    }
}
