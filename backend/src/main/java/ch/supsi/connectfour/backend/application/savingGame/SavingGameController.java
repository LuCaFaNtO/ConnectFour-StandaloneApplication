package ch.supsi.connectfour.backend.application.savingGame;

import ch.supsi.connectfour.backend.application.GridBusinessInterface;
import ch.supsi.connectfour.backend.application.exceptions.IllegalFIleException;
import ch.supsi.connectfour.backend.business.GridModel;
import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.backend.business.domain.Grid;
import ch.supsi.connectfour.backend.business.savingGame.SavingGameModel;

import java.io.File;
import java.io.IOException;

public class SavingGameController implements SavingGameControllerInterface{
    private static SavingGameController instance = null;
    private final SavingGameBusinessInterface savingGameModel;
    private final GridBusinessInterface gridModel;

    private SavingGameController() {
        this.savingGameModel = SavingGameModel.getInstance();
        this.gridModel = GridModel.getInstance();
    }

    public static SavingGameController getInstance() {
        return instance == null ? instance = new SavingGameController() : instance;
    }

    @Override
    public void saveGame(File file) throws IllegalFIleException, IOException {
        Cell[][] grid = gridModel.getGrid();
        boolean currentTurn = gridModel.getTurn();

        savingGameModel.saveGame(file, grid, currentTurn);
    }
}
