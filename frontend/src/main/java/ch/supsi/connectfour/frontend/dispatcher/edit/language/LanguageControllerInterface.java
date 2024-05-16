package ch.supsi.connectfour.frontend.dispatcher.edit.language;

import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;

import java.util.Set;

public interface LanguageControllerInterface {
    void addUpdaterLanguageList(UpdateLanguageInterface updaterLanguage);
    void removeUpdaterLanguageList(UpdateLanguageInterface updaterLanguage);
    void changeLanguage(final String language);
    Set<String> getSupportedLanguages();
}
