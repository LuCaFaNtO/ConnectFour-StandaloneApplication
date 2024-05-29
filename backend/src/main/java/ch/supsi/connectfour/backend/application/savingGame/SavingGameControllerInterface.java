package ch.supsi.connectfour.backend.application.savingGame;

import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;

import java.io.File;
import java.io.IOException;

public interface SavingGameControllerInterface {
    void saveGame(File file) throws IllegalFIleException, IOException;

    void loadGame(File file) throws IllegalFIleException;
}
