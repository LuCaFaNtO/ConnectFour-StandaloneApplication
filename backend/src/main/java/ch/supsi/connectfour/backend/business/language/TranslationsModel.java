package ch.supsi.connectfour.backend.business.language;

import ch.supsi.connectfour.backend.application.language.TranslationsBusinessInterface;
import ch.supsi.connectfour.backend.dataaccess.language.TranslationsPropertiesDataAccess;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class TranslationsModel implements TranslationsBusinessInterface {
    private static TranslationsModel instance = null;
    private final TranslationsDataAccessInterface translationsDataAccess;

    private TranslationsModel() {
        translationsDataAccess = TranslationsPropertiesDataAccess.getInstance();
    }

    public static TranslationsModel getInstance() {
        return instance == null? instance = new TranslationsModel() : instance;
    }

    @Override
    public String getTagFromKeyLanguage(String languageKey) {
        return translationsDataAccess.getTagFromKeyLanguage(languageKey);
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
