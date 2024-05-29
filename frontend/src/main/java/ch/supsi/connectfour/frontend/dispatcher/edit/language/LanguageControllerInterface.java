package ch.supsi.connectfour.frontend.dispatcher.edit.language;

import ch.supsi.connectfour.frontend.controller.edit.language.UpdaterLanguageInterface;

public interface LanguageControllerInterface {
    void addUpdaterLanguageList(UpdaterLanguageInterface updaterLanguage);

    void changeLanguage(final String language);
}
