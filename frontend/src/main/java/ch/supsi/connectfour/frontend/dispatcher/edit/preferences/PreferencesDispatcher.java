package ch.supsi.connectfour.frontend.dispatcher.edit.preferences;

import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.controller.edit.preferences.PreferencesController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class PreferencesDispatcher implements Initializable {

    private final PreferencesControllerInterface preferencesController;
    private final String fxmlLocation = "/preferences.fxml";

    @FXML
    public Circle circlePlayer1;

    @FXML
    public Pane symbolContainerPane1;

    @FXML
    public Label symbolPlayer1;

    @FXML
    public Button closeButton;

    @FXML
    private Pane colorsContainerPane1;

    @FXML
    Button exitButton;

    public PreferencesDispatcher() {
        preferencesController = PreferencesController.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addSupportedColors();
        addSupportedSymbols();
    }

    public void savePreferences(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private void addSupportedColors() {
        List<String> supportedColors = preferencesController.getSupportedColors().stream().toList();
        List<Rectangle> rectangles = colorsContainerPane1.getChildren().stream().map(c -> (Rectangle) c).toList();
        for (int i = 0; i < rectangles.size(); i++) {
            rectangles.get(i).setFill(Color.valueOf(supportedColors.get(i)));
        }
    }

    private void addSupportedSymbols() {
        List<String> supportedSymbols = preferencesController.getSupportedSymbols().stream().toList();
        List<AnchorPane> anchorPanes = symbolContainerPane1.getChildren().stream().map(c -> (AnchorPane) c).toList();
        for(int i = 0; i < anchorPanes.size(); i++) {
            Label label = (Label) anchorPanes.get(i).getChildren().get(0);
            label.setText(supportedSymbols.get(i));
        }
    }

    public void changePreviewColor(MouseEvent mouseEvent) {
        Rectangle rectangle = (Rectangle) mouseEvent.getSource();
        Color color = (Color) rectangle.getFill();
        circlePlayer1.setFill(color);

    }

    public void changePreviewSymbol(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        symbolPlayer1.setText(label.getText());
    }

    public void showPreferencesPage(){
        try {
            Parent preferencesDispatcher = new FXMLLoader(getClass().getResource(fxmlLocation)).load();
            Scene scene = new Scene(preferencesDispatcher);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}