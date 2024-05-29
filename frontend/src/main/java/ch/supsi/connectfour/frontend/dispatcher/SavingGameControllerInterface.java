package ch.supsi.connectfour.frontend.dispatcher;

public interface SavingGameControllerInterface {
    void saveGame();

    void saveGameAs();

    boolean openGame();

    boolean isAlreadySave();

    void showSaveGamePopUp();

    void closeSaveGamePopUp();

    void resetSavingConditions();
}
