package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.model.InfoBarModel;
import ch.supsi.connectfour.frontend.view.InfoBarView;

public class InfoBarController implements InfoBarControllerInterface{
    private static InfoBarController instance = null;
    private final InfoBarModelInterface infoBarModel;

    private InfoBarController() {
        this.infoBarModel = InfoBarModel.getInstance();
    }

    public static InfoBarController getInstance() {
        return instance == null ? instance = new InfoBarController() : instance;
    }

    @Override
    public void addInfoBar(InfoBarView infoBar) {
        infoBarModel.addInfoBar(infoBar);
    }
}
