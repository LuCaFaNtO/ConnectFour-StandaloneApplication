package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.application.ObserverControllerInterface;
import ch.supsi.connectfour.backend.application.observer.FinishGameObserver;
import ch.supsi.connectfour.backend.application.observer.ObserverController;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.frontend.controller.InfoBarModelInterface;
import ch.supsi.connectfour.frontend.controller.statusGame.StatusGameModelInterface;
import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import ch.supsi.connectfour.frontend.model.statusGame.StatusGame;
import ch.supsi.connectfour.frontend.model.statusGame.StatusGameModel;
import ch.supsi.connectfour.frontend.view.InfoBarView;

public class InfoBarModel implements InfoBarModelInterface, FinishGameObserver {
    private static InfoBarModel instance = null;
    private InfoBarView infoBar;
    private final ObserverControllerInterface observerController;
    private final StatusGameModelInterface statusGameModel;

    private InfoBarModel() {
        this.statusGameModel = StatusGameModel.getInstance();
        this.observerController = ObserverController.getInstance();

        this.observerController.registerFinishGameObserver(this);
    }

    public static InfoBarModel getInstance() {
        return instance == null ? instance = new InfoBarModel() : instance;
    }

    @Override
    public void addInfoBar(InfoBarView infoBar) {
        this.infoBar = infoBar;
    }

    @Override
    public void win(String playerName, String playerSymbol) {
        infoBar.win(playerName, playerSymbol);
        setEndStatus();
    }

    @Override
    public void gridFull() {
        infoBar.gridFull();
        setEndStatus();
    }

    private void setEndStatus(){
        statusGameModel.setStatusGame(StatusGame.END);
    }
}
