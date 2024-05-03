package ch.supsi.connectfour.frontend.dispatcher.edit;

import ch.supsi.connectfour.frontend.model.edit.UpdateLanguageInterface;

import java.util.Set;

public interface LanguageControllerInterface {
    void addUpdaterLanguageList(UpdateLanguageInterface updaterLanguage);
    void changeLanguage(final String language);
    Set<String> getSupportedLanguages();
}
