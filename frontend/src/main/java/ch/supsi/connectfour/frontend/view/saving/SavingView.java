package ch.supsi.connectfour.frontend.view.saving;

import ch.supsi.connectfour.frontend.controller.savingGame.SavingGameViewInterface;
import ch.supsi.connectfour.frontend.model.edit.language.UpdaterLanguageInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;

public class SavingView implements SavingGameViewInterface, UpdaterLanguageInterface {
    private static SavingView instance = null;
    private final String fxmlLocation = "/saveGamePopUp.fxml";
    private FXMLLoader fxmlLoaderSaveGameChoice;

    private VBox containerPopUpSave;
    private BorderPane mainBorderPain;

    private SavingView() {
        fxmlLoaderSaveGameChoice = new FXMLLoader(getClass().getResource(fxmlLocation), ResourceBundle.getBundle("i18n.labels"));
    }

    public static SavingView getInstance() {
        return instance == null ? instance = new SavingView() : instance;
    }

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        fxmlLoaderSaveGameChoice = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    @Override
    public void changeSceneFx() {
    }

    @Override
    public void showSaveChoicePopUp() {
        try {
            containerPopUpSave = new FXMLLoader(fxmlLoaderSaveGameChoice.getLocation(), fxmlLoaderSaveGameChoice.getResources()).load();
            Scene scene = new Scene(containerPopUpSave);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/connect-four.png"))));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeStage() {
        containerPopUpSave.getScene().getWindow().hide();
    }

    @Override
    public File getSaveFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showSaveDialog(mainBorderPain.getScene().getWindow());
    }

    @Override
    public File getOpenFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showOpenDialog(mainBorderPain.getScene().getWindow());
    }

    @Override
    public void addMainBorderPain(BorderPane borderPane) {
        this.mainBorderPain = borderPane;
    }
}
