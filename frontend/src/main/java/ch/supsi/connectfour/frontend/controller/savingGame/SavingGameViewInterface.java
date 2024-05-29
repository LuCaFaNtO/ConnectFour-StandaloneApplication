package ch.supsi.connectfour.frontend.controller.savingGame;

import javafx.scene.layout.BorderPane;

import java.io.File;

public interface SavingGameViewInterface {
    void showSaveChoicePopUp();

    void closeStage();

    File getSaveFile();

    File getOpenFile();

    void addMainBorderPain(BorderPane borderPane);
}
