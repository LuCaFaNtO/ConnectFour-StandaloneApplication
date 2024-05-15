package ch.supsi.connectfour.backend.application.observer;

import ch.supsi.connectfour.backend.business.domain.Player;

public interface FinishGameObserver {
    void win(String playerName, String playerSymbol);
    void gridFull();
}
