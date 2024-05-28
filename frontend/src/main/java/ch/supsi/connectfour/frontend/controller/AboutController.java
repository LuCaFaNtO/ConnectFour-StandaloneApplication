package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.dispatcher.AboutControllerInterface;
import ch.supsi.connectfour.frontend.view.AboutView;

public class AboutController implements AboutControllerInterface {
    private static AboutController instance = null;
    private AboutView aboutView;

    private AboutController() {
    }

    public static AboutController getInstance() {
        return instance == null ? instance = new AboutController() : instance;
    }

    @Override
    public void showAbout() {
        aboutView.showAboutInformation();
    }

    @Override
    public void addAboutView(AboutView aboutView) {
        this.aboutView = aboutView;
    }
}
