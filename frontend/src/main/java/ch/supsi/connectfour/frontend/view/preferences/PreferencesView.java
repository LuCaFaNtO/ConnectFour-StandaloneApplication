package ch.supsi.connectfour.frontend.view.preferences;

import ch.supsi.connectfour.frontend.controller.edit.preferences.PreferencesViewInterface;
import ch.supsi.connectfour.frontend.model.edit.language.UpdaterLanguageInterface;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class PreferencesView implements PreferencesViewInterface, UpdaterLanguageInterface {
    private static PreferencesView instance = null;
    private final String fxmlLocation = "/preferences.fxml";
    Stage currentStage;
    private FXMLLoader preferencesFxmlloader;

    private PreferencesView() {
        this.preferencesFxmlloader = new FXMLLoader(getClass().getResource(fxmlLocation), ResourceBundle.getBundle("i18n.labels"));
        this.currentStage = null;
    }

    public static PreferencesView getInstance() {
        return instance == null ? instance = new PreferencesView() : instance;
    }

    @Override
    public void fillRectangles(List<Rectangle> rectangles, List<String> supportedColors) {
        for (int i = 0; i < rectangles.size(); i++) {
            rectangles.get(i).setFill(Color.valueOf(supportedColors.get(i)));
        }
    }

    @Override
    public void writeSymbols(List<AnchorPane> anchorPanes, List<String> supportedSymbols) {
        for (int i = 0; i < anchorPanes.size(); i++) {
            Label label = (Label) anchorPanes.get(i).getChildren().get(0);
            label.setText(supportedSymbols.get(i));
        }
    }

    @Override
    public void showPreferencesPage() {
        try {
            if (preferencesFxmlloader.getRoot() != null)
                preferencesFxmlloader = new FXMLLoader(getClass().getResource(fxmlLocation), preferencesFxmlloader.getResources());
            Parent preferencesDispatcher = preferencesFxmlloader.load();
            Scene scene = new Scene(preferencesDispatcher);
            currentStage = new Stage();
            currentStage.initModality(Modality.APPLICATION_MODAL);
            currentStage.setScene(scene);
            currentStage.setResizable(false);
            currentStage.initStyle(StageStyle.UNDECORATED);
            currentStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exit(Button closeButton) {
        if (currentStage == null)
            currentStage = (Stage) closeButton.getScene().getWindow();
        currentStage.close();
    }

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        preferencesFxmlloader = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    @Override
    public void changeSceneFx() {
    }
}
