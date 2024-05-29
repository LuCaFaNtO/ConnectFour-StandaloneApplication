package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.backend.application.ObserverControllerInterface;
import ch.supsi.connectfour.backend.application.observer.FinishGameObserver;
import ch.supsi.connectfour.backend.application.observer.ObserverController;
import ch.supsi.connectfour.backend.application.observer.SavingGameObserver;
import ch.supsi.connectfour.backend.application.observer.TurnChangeObserverInterface;
import ch.supsi.connectfour.frontend.view.InfoBar;

public class InfoBarController implements InfoBarControllerInterface, FinishGameObserver, TurnChangeObserverInterface, SavingGameObserver {
    private static InfoBarController instance = null;
    private InfoBar infoBar;

    private InfoBarController() {
        ObserverControllerInterface observerController = ObserverController.getInstance();

        observerController.registerFinishGameObserver(this);
        observerController.registerTurnChangeObserver(this);
        observerController.registerSavingGameObserver(this);
    }

    public static InfoBarController getInstance() {
        return instance == null ? instance = new InfoBarController() : instance;
    }

    @Override
    public void addInfoBar(InfoBar infoBar) {
        this.infoBar = infoBar;
    }

    @Override
    public void win(String playerName, String playerSymbol) {
        infoBar.win(playerName, playerSymbol);
    }

    @Override
    public void gridFull() {
        infoBar.gridFull();
    }


    @Override
    public void changeTurn(String playerName, String playerSymbol) {
        infoBar.showTurn(playerName, playerSymbol);
    }

    @Override
    public void onGameSaved() {
        infoBar.showSaveGame();
    }
}
