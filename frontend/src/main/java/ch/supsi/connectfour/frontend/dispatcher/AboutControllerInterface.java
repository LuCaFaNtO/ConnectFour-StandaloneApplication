package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import ch.supsi.connectfour.frontend.view.UpdateViewInterface;

public interface AboutControllerInterface {
    void showAbout();
    void addAboutView(UpdateLanguageInterface aboutView);
}
