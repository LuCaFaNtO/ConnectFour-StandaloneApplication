package ch.supsi.connectfour.frontend.dispatcher.edit;

import ch.supsi.connectfour.frontend.model.edit.UpdateLanguageInterface;

public interface LanguageControllerInterface {
    void setUpdaterLanguageList(UpdateLanguageInterface updaterLanguage);
    void changeLanguage(final String language);
}
