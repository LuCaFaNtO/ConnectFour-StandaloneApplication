package ch.supsi.connectfour.frontend.controller.edit;

import ch.supsi.connectfour.frontend.model.edit.UpdateLanguageInterface;

import java.util.Set;

public interface LanguageModelInterface {
    void addUpdaterLanguageList(UpdateLanguageInterface updaterLanguage);
    void changeLanguage(final String language);
    Set<String> getSupportedLanguages();
}
