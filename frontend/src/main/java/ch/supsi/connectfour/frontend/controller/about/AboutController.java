package ch.supsi.connectfour.frontend.controller.about;

import ch.supsi.connectfour.frontend.dispatcher.AboutControllerInterface;
import ch.supsi.connectfour.frontend.view.about.AboutView;

public class AboutController implements AboutControllerInterface {
    private static AboutController instance = null;
    private final AboutViewInterface aboutView;

    private AboutController() {
        this.aboutView = AboutView.getInstance();
    }

    public static AboutController getInstance() {
        return instance == null ? instance = new AboutController() : instance;
    }

    @Override
    public void showAboutInformation() {
        aboutView.showAboutInformation();
    }
}
