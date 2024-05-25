package ch.supsi.connectfour.frontend.dispatcher.edit.preferences;

import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.frontend.controller.edit.preferences.PreferencesController;
import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import java.util.List;
import java.util.ResourceBundle;

public class PreferencesDispatcher implements Initializable, UpdateLanguageInterface {

    private final PreferencesControllerInterface preferencesController;
    private final String fxmlLocation = "/preferences.fxml";
    private FXMLLoader preferencesFxmlloader;

    Stage currentStage;

    @FXML
    public Circle circlePlayer1;
    @FXML
    private Pane colorsContainerPane1;
    @FXML
    public Pane symbolContainerPane1;
    @FXML
    public Label symbolPlayer1;

    @FXML
    public Circle circlePlayer2;
    @FXML
    private Pane colorsContainerPane2;
    @FXML
    public Pane symbolContainerPane2;
    @FXML
    public Label symbolPlayer2;

    @FXML
    public Button closeButton;

    public PreferencesDispatcher() {
        preferencesController = PreferencesController.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPreviewOfPlayers();
        addSupportedColors();
        addSupportedSymbols();
        preferencesFxmlloader = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
        this.currentStage = null;
    }

    public void savePreferences() {
        Piece newPiece1 = new Piece(circlePlayer1.getFill().toString(), symbolPlayer1.getText());
        Piece newPiece2 = new Piece(circlePlayer2.getFill().toString(), symbolPlayer2.getText());

        preferencesController.setNewPreferences(List.of(newPiece1, newPiece2));
    }

    public void exit() {
        if(currentStage == null)
            currentStage = (Stage) closeButton.getScene().getWindow();
        currentStage.close();
    }

    private void addSupportedColors() {
        List<Rectangle> rectangles = colorsContainerPane1.getChildren().stream().map(c -> (Rectangle) c).toList();
        fillRectangles(rectangles);

        rectangles = colorsContainerPane2.getChildren().stream().map(c -> (Rectangle) c).toList();
        fillRectangles(rectangles);
    }

    private void fillRectangles(List<Rectangle> rectangles) {
        List<String> supportedColors = preferencesController.getSupportedColors().stream().toList();
        for (int i = 0; i < rectangles.size(); i++) {
            rectangles.get(i).setFill(Color.valueOf(supportedColors.get(i)));
        }
    }

    private void addSupportedSymbols() {
        List<AnchorPane> anchorPanes = symbolContainerPane1.getChildren().stream().map(c -> (AnchorPane) c).toList();
        writeSymbols(anchorPanes);

        anchorPanes = symbolContainerPane2.getChildren().stream().map(c -> (AnchorPane) c).toList();
        writeSymbols(anchorPanes);
    }

    private void writeSymbols(List<AnchorPane> anchorPanes) {
        List<String> supportedSymbols = preferencesController.getSupportedSymbols().stream().toList();
        for(int i = 0; i < anchorPanes.size(); i++) {
            Label label = (Label) anchorPanes.get(i).getChildren().get(0);
            label.setText(supportedSymbols.get(i));
        }
    }

    private void setPreviewOfPlayers() {
        List<Player> players = preferencesController.getPlayers();

        Player player1 = players.get(0);
        Player player2 = players.get(1);

        circlePlayer1.setFill(Color.valueOf(player1.getPiece().getColor()));
        symbolPlayer1.setText(player1.getPiece().getSymbol());

        circlePlayer2.setFill(Color.valueOf(player2.getPiece().getColor()));
        symbolPlayer2.setText(player2.getPiece().getSymbol());
    }

    public void changePreviewColor(MouseEvent mouseEvent) {
        Rectangle rectangle = (Rectangle) mouseEvent.getSource();
        Color color = (Color) rectangle.getFill();
        String idColorsPane = rectangle.getParent().getId();

        if(idColorsPane.charAt(idColorsPane.length() - 1) == '1')
            circlePlayer1.setFill(color);
        else
            circlePlayer2.setFill(color);
    }

    public void changePreviewSymbol(MouseEvent mouseEvent) {
        Label label = (Label) mouseEvent.getSource();
        String idSymbolPane = label.getParent().getParent().getId();

        if(idSymbolPane.charAt(idSymbolPane.length() - 1) == '1')
            symbolPlayer1.setText(label.getText());
        else
            symbolPlayer2.setText(label.getText());
    }

    public void showPreferencesPage(){
        try {
            if(preferencesFxmlloader.getRoot() != null)
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
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        preferencesFxmlloader = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    @Override
    public void changeSceneFx() {}
}