package ch.supsi.connectfour.frontend.dispatcher.edit.preferences;

import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.controller.edit.preferences.PreferencesController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class PreferencesDispatcher implements Initializable {

    private final PreferencesControllerInterface preferencesController;

    @FXML
    public Pane colorsContainerPane;

    @FXML
    Button exitButton;

    public PreferencesDispatcher() {
        preferencesController = PreferencesController.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addSupportedColors();
    }

    public void savePreferences(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
        MainFx.showGameBoard();
    }

    private void addSupportedColors() {
        List<String> supportedColors = preferencesController.getSupportedColors().stream().toList();
        List<Rectangle> rectangles = colorsContainerPane.getChildren().stream().map(c -> (Rectangle) c).toList();
        for (int i = 0; i < rectangles.size(); i++) {
            rectangles.get(i).setFill(Color.valueOf(supportedColors.get(i)));
        }
    }

}