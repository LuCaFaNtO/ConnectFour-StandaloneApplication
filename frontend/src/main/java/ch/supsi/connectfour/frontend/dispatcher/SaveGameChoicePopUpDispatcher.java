package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.controller.savingGame.SavingGameController;

public class SaveGameChoicePopUpDispatcher {
    private final SavingGameControllerInterface savingGameController;

    public SaveGameChoicePopUpDispatcher() {
        this.savingGameController = SavingGameController.getInstance();
    }

    public void handleYesButton() {
        savingGameController.saveGame();
        savingGameController.closeSaveGamePopUp();
    }

    public void handleNoButton() {
        savingGameController.closeSaveGamePopUp();
    }
}
