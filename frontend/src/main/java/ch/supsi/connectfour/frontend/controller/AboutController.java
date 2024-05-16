package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.dispatcher.AboutControllerInterface;
import ch.supsi.connectfour.frontend.model.AboutModel;
import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;

public class AboutController implements AboutControllerInterface {

    private static AboutController instance = null;
    private final AboutModelInterface aboutModel;

    private AboutController() {
        aboutModel = AboutModel.getInstance();
    }

    public static AboutController getInstance() {
        return instance == null ? instance = new AboutController() : instance;
    }

    @Override
    public void showAbout() {
        aboutModel.showAbout();
    }

    @Override
    public void addAboutView(UpdateLanguageInterface aboutView) {
        aboutModel.addAboutView(aboutView);
    }
}
