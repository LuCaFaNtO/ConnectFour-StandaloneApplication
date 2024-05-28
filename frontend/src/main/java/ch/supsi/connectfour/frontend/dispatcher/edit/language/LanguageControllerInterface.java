package ch.supsi.connectfour.frontend.dispatcher.edit.language;

import ch.supsi.connectfour.frontend.model.edit.language.UpdaterLanguageInterface;

import java.util.Set;

public interface LanguageControllerInterface {
    void addUpdaterLanguageList(UpdaterLanguageInterface updaterLanguage);
    void removeUpdaterLanguageList(UpdaterLanguageInterface updaterLanguage);
    void changeLanguage(final String language);
    Set<String> getSupportedLanguages();
}
