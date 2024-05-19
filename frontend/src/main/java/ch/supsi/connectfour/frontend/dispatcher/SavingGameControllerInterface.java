package ch.supsi.connectfour.frontend.dispatcher;

import java.io.File;

public interface SavingGameControllerInterface {
    void saveGame();
    void loadGame();
    void setNewSavingGameFile(File file);
    boolean savingGameFileExists();
    boolean isAlreadySave();
    void addSavingGamePopUp(SaveGameChoicePopUpDispatcher saveGameChoicePopUpDispatcher);
    void showSaveGamePopUp();
}
