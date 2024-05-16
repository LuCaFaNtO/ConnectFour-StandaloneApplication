package ch.supsi.connectfour.frontend.controller.statusGame;

import ch.supsi.connectfour.frontend.model.statusGame.StatusGame;
import ch.supsi.connectfour.frontend.model.statusGame.UpdateStatusViewInterface;

public interface StatusGameModelInterface {
    void setStatusGame(StatusGame statusGame);
    boolean isInStateGame();
    void addUpdateViewByStatus(UpdateStatusViewInterface updaterViewByStatus);
    void removeUpdateViewByStatus(UpdateStatusViewInterface updaterViewByStatus);
    void onChangeStatusUpdate();
}
