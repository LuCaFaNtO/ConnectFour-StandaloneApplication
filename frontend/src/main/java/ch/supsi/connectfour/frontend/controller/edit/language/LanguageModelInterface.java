package ch.supsi.connectfour.frontend.controller.edit.language;

import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;

import java.util.Set;

public interface LanguageModelInterface {
    void addUpdaterLanguageList(UpdateLanguageInterface updaterLanguage);
    void removeUpdaterLanguageList(UpdateLanguageInterface updaterLanguage);
    void changeLanguage(final String language);
    Set<String> getSupportedLanguages();
}
