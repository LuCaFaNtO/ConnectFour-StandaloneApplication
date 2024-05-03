package ch.supsi.connectfour.backend.application.language;

import ch.supsi.connectfour.backend.business.language.TranslationsModel;

import java.util.ResourceBundle;
import java.util.Set;

public class TranslationsController implements TranslationsControllerInterface {
    private static TranslationsController instance = null;
    private final TranslationsBusinessInterface translationsModel;

    private TranslationsController() {
        translationsModel = TranslationsModel.getInstance();

        translationsModel.setLocaleDefault();
    }

    public static TranslationsController getInstance() {
        return instance == null? instance = new TranslationsController() : instance;
    }

    public ResourceBundle changeLanguage(String languageKey) {
        String languageTag = translationsModel.getTagFromKeyLanguage(languageKey);
        return translationsModel.changeLanguage(languageTag);
    }

    @Override
    public Set<String> getSupportedLanguages() {
        return translationsModel.getSupportedLanguages();
    }
}
