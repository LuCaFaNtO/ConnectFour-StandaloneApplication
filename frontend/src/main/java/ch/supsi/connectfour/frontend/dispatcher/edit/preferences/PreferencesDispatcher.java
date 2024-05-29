package ch.supsi.connectfour.frontend.dispatcher.edit.preferences;

import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.frontend.controller.edit.preferences.PreferencesController;
import ch.supsi.connectfour.frontend.model.edit.language.UpdaterLanguageInterface;
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

public class PreferencesDispatcher implements Initializable {

    private final PreferencesControllerInterface preferencesController;
    private final String fxmlLocation = "/preferences.fxml";

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
    }

    public void savePreferences() {
        Piece newPiece1 = new Piece(circlePlayer1.getFill().toString(), symbolPlayer1.getText());
        Piece newPiece2 = new Piece(circlePlayer2.getFill().toString(), symbolPlayer2.getText());

        preferencesController.setNewPreferences(List.of(newPiece1, newPiece2));
    }

    public void exit() {
        preferencesController.exit(closeButton);
    }

    private void addSupportedColors() {
        List<Rectangle> rectangles = colorsContainerPane1.getChildren().stream().map(c -> (Rectangle) c).toList();
        preferencesController.fillRectangles(rectangles);

        rectangles = colorsContainerPane2.getChildren().stream().map(c -> (Rectangle) c).toList();
        preferencesController.fillRectangles(rectangles);
    }

    private void addSupportedSymbols() {
        List<AnchorPane> anchorPanes = symbolContainerPane1.getChildren().stream().map(c -> (AnchorPane) c).toList();
        preferencesController.writeSymbols(anchorPanes);

        anchorPanes = symbolContainerPane2.getChildren().stream().map(c -> (AnchorPane) c).toList();
        preferencesController.writeSymbols(anchorPanes);
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
}