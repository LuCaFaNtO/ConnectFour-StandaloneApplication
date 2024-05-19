package ch.supsi.connectfour.frontend.dispatcher;

import java.io.File;

public interface SavingGameControllerInterface {
    void saveGame();
    void setNewSavingGameFile(File file);
    boolean savingGameFileExists();
}
