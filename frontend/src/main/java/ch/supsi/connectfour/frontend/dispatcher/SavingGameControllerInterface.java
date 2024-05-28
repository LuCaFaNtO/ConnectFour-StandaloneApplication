package ch.supsi.connectfour.frontend.dispatcher;

import java.io.File;

public interface SavingGameControllerInterface {
    void saveGame();
    void saveGameAs();
    boolean loadGame();
    void setNewSavingGameFile(File file);
    boolean isAlreadySave();
    void showSaveGamePopUp();
    void closeSaveGamePopUp();
    void resetSavingConditions();
}
