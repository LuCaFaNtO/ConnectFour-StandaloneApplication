package ch.supsi.connectfour.frontend.controller.statusGame;

import ch.supsi.connectfour.frontend.model.statusGame.StatusGame;

public interface StatusGameModelInterface {
    void setStatusGame(StatusGame statusGame);

    boolean isInStateGame();

    void preStartCondition();

    void gameCondition();
}
