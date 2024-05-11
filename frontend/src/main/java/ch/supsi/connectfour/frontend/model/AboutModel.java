package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.frontend.controller.AboutModelInterface;
import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import ch.supsi.connectfour.frontend.view.AboutView;

public class AboutModel implements AboutModelInterface {
    private static AboutModel instance = null;
    private AboutView aboutView;

    private AboutModel() {}

    public static AboutModel getInstance() {
        return instance == null ? instance = new AboutModel() : instance;
    }

    @Override
    public void showAbout() {
        aboutView.showAboutInformation();
    }

    @Override
    public void addAboutView(UpdateLanguageInterface aboutView) {
        this.aboutView = (AboutView) aboutView;
    }
}
