package ch.supsi.connectfour.frontend.model.edit.language;

import ch.supsi.connectfour.backend.application.language.TranslationsController;
import ch.supsi.connectfour.backend.application.language.TranslationsControllerInterface;
import ch.supsi.connectfour.frontend.controller.edit.language.LanguageModelInterface;

import java.util.ResourceBundle;
import java.util.Set;

public class LanguageModel implements LanguageModelInterface {
    private static LanguageModelInterface instance = null;
    private final TranslationsControllerInterface translationsController;
    private ResourceBundle currentResourceBundle;

    private LanguageModel() {
        this.translationsController = TranslationsController.getInstance();
    }

    public static LanguageModelInterface getInstance() {
        return instance == null ? instance = new LanguageModel() : instance;
    }

    @Override
    public void changeLanguage(final String language) {
        currentResourceBundle = translationsController.changeLanguage(language);
    }

    @Override
    public ResourceBundle getCurrentResourceBundle() {
        return currentResourceBundle;
    }

    @Override
    public Set<String> getSupportedLanguages() {
        return translationsController.getSupportedLanguages();
    }
}
