package ch.supsi.connectfour.frontend.controller.savingGame;

import ch.supsi.connectfour.frontend.dispatcher.SaveGameChoicePopUpDispatcher;

import java.io.File;

public interface SavingGameModelInterface {
    void saveGame();
    boolean loadGame();
    void setNewSavingGameFile(File file);
    boolean savingGameFileExists();
    boolean isAlreadySave();
    void addSavingGamePopUp(SaveGameChoicePopUpDispatcher saveGameChoicePopUpDispatcher);
    void showSaveGamePopUp();
}
