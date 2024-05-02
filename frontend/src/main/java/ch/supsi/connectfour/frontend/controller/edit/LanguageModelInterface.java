package ch.supsi.connectfour.frontend.controller.edit;

import ch.supsi.connectfour.frontend.model.edit.UpdateLanguageInterface;

public interface LanguageModelInterface {
    void addUpdaterLanguageList(UpdateLanguageInterface updaterLanguage);
    void changeLanguage(final String language);
}
