package ch.supsi.connectfour.backend.application.observer;

import ch.supsi.connectfour.backend.business.domain.Player;

public interface FinishGameObserver {
    void win(Player winPlayer);
    void gridFull();
}
