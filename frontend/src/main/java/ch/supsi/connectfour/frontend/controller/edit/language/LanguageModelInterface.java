package ch.supsi.connectfour.frontend.controller.edit.language;

import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;

import java.util.ResourceBundle;
import java.util.Set;

public interface LanguageModelInterface {
    void changeLanguage(final String language);
    ResourceBundle getCurrentResourceBundle();
    Set<String> getSupportedLanguages();
}
