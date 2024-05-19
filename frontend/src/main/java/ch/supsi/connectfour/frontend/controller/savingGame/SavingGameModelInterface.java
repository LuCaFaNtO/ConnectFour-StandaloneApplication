package ch.supsi.connectfour.frontend.controller.savingGame;

import ch.supsi.connectfour.frontend.dispatcher.SaveGameChoicePopUpDispatcher;

import java.io.File;

public interface SavingGameModelInterface {
    void saveGame();
    void setNewSavingGameFile(File file);
    boolean savingGameFileExists();
    void addSavingGamePopUp(SaveGameChoicePopUpDispatcher saveGameChoicePopUpDispatcher);
    void showSaveGamePopUp();
}
