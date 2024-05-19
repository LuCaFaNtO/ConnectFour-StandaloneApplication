package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.controller.savingGame.SavingGameController;
import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SaveGameChoicePopUpDispatcher implements Initializable, UpdateLanguageInterface {
    private final String fxmlLocation = "/saveGamePopUp.fxml";
    private FXMLLoader fxmlLoaderSaveGameChoice;
    private static MenuBarDispatcher menuBarDispatcher;

    @FXML
    public VBox containerPopUpSave;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoaderSaveGameChoice = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    public void handleYesButton() {
        menuBarDispatcher.saveGame();
        closeStage();
    }

    public void handleNoButton() {
        closeStage();
    }

    public void showSaveChoicePopUp(){
        try {
            Parent root = new FXMLLoader(getFxmlLoader().getLocation(), getFxmlLoader().getResources()).load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/connect-four.png")));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeStage() {
        containerPopUpSave.getScene().getWindow().hide();
    }

    public void addMenuBarDispatcher(MenuBarDispatcher menuBarDispatcher) {
        if(menuBarDispatcher != null)
            this.menuBarDispatcher = menuBarDispatcher;
    }

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        fxmlLoaderSaveGameChoice = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    @Override
    public void changeSceneFx() {}

    @Override
    public FXMLLoader getFxmlLoader() {
        return fxmlLoaderSaveGameChoice;
    }
}
