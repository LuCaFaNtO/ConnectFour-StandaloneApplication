package ch.supsi.connectfour.frontend.controller.savingGame;

import java.io.File;

public interface SavingGameModelInterface {
    void saveGame();
    void setNewSavingGameFile(File file);
    boolean savingGameFileExists();
}
