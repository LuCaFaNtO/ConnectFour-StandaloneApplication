package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.model.statusGame.StatusGame;
import ch.supsi.connectfour.frontend.model.statusGame.UpdateStatusViewInterface;

public interface StatusGameControllerInterface {
    void setStatusToPreStart();
    void setStatusToGame();
    void setStatusToEnd();
    boolean isInStateGame();
    void addUpdateViewByStatus(UpdateStatusViewInterface updaterViewByStatus);
    void removeUpdateViewByStatus(UpdateStatusViewInterface updaterViewByStatus);
}
