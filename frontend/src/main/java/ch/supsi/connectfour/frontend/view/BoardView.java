package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.business.Cell;
import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.model.edit.UpdateLanguageInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BoardView implements UpdateLanguageInterface, Initializable {
    private final String fxmlLocation = "/gameboard.fxml";
    private FXMLLoader fxmlLoaderBoardView;

    @FXML
    public GridPane grid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoaderBoardView = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {

    }

    @Override
    public void changeSceneFx() {

    }

    @Override
    public FXMLLoader getFxmlLoader() {
        return null;
    }

    public void updateGrid(Cell cell) {
        AnchorPane anchorPane = grid.getChildren().stream()
                .filter(node -> GridPane.getRowIndex(node) == cell.getRow() && GridPane.getColumnIndex(node) == cell.getCol())
                .map(node -> (AnchorPane) node) // Effettua il cast a AnchorPane
                .findFirst() // Trova il primo AnchorPane che soddisfa il filtro
                .orElseThrow();

        Circle circle = (Circle) anchorPane.getChildren().get(0);
        circle.setFill(Paint.valueOf("yellow"));
    }


}
