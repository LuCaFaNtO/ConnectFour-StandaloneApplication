package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;

public interface AboutControllerInterface {
    void showAbout();
    void addAboutView(UpdateLanguageInterface aboutView);
}
