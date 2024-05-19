package ch.supsi.connectfour.frontend.controller.savingGame;

import ch.supsi.connectfour.frontend.dispatcher.SaveGameChoicePopUpDispatcher;
import ch.supsi.connectfour.frontend.dispatcher.SavingGameControllerInterface;
import ch.supsi.connectfour.frontend.model.savingGame.SavingGameModel;

import java.io.File;

public class SavingGameController implements SavingGameControllerInterface {
    private static SavingGameController instance = null;
    private final SavingGameModelInterface savingGameModel;

    private SavingGameController() {
        this.savingGameModel = SavingGameModel.getInstance();
    }

    public static SavingGameController getInstance() {
        return instance == null ? instance = new SavingGameController() : instance;
    }

    @Override
    public void saveGame() {
        savingGameModel.saveGame();
    }

    @Override
    public void setNewSavingGameFile(File file) {
        savingGameModel.setNewSavingGameFile(file);
    }

    @Override
    public boolean savingGameFileExists() {
        return savingGameModel.savingGameFileExists();
    }

    @Override
    public void addSavingGamePopUp(SaveGameChoicePopUpDispatcher saveGameChoicePopUpDispatcher) {
        savingGameModel.addSavingGamePopUp(saveGameChoicePopUpDispatcher);
    }

    @Override
    public void showSaveGamePopUp() {
        savingGameModel.showSaveGamePopUp();
    }
}
