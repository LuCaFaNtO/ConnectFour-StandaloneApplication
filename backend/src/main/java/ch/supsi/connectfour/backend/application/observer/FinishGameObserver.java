package ch.supsi.connectfour.backend.application.observer;

public interface FinishGameObserver {
    void win(String playerName, String playerSymbol);

    void gridFull();
}
