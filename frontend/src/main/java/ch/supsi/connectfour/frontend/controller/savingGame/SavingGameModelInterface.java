package ch.supsi.connectfour.frontend.controller.savingGame;

import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;

import java.io.File;
import java.io.IOException;

public interface SavingGameModelInterface {
    void saveGame() throws IllegalFIleException, IOException;

    void loadGame() throws IllegalFIleException;

    void setNewSavingGameFile(File file);

    boolean savingGameFileExists();

    boolean isAlreadySave();

    void setAlreadySaved(boolean alreadySaved);
}
