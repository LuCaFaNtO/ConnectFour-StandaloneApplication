package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.model.statusGame.UpdateStatusInterface;

public interface StatusGameControllerInterface {
    void setStatusToPreStart();

    void setStatusToGame();

    void setStatusToEnd();

    boolean isInStateGame();

    void addUpdateViewByStatus(UpdateStatusInterface updaterViewByStatus);

}
