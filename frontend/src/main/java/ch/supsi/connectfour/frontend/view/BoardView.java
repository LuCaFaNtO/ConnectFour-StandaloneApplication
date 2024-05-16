package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.frontend.model.UpdateGridInterface;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardView implements UpdateGridInterface, Initializable {
    private final String fxmlLocation = "/gameboard.fxml";
    private FXMLLoader fxmlLoaderBoardView;

    @FXML
    public GridPane grid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoaderBoardView = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    @Override
    public void changeSceneFx() {

    }

    @Override
    public FXMLLoader getFxmlLoader() {
        return null;
    }

    @Override
    public void updateGrid(Cell cell) {
        updateCell(cell);
    }

    @Override
    public void updateGridWithNewPreferences(Cell[][] grid) {
        for (Cell[] cells : grid)
            for (Cell cell : cells)
                if (cell.isFill())
                    updateCell(cell);
    }

    private void updateCell(Cell cell) {
        AnchorPane anchorPane = grid.getChildren().stream()
                .filter(node -> GridPane.getRowIndex(node) == cell.getRow() && GridPane.getColumnIndex(node) == cell.getCol())
                .map(node -> (AnchorPane) node)
                .findFirst()
                .orElseThrow();

        Circle circle = (Circle) anchorPane.getChildren().get(0);
        circle.setFill(Color.valueOf(cell.getPlayer().getPiece().getColor()));
        Label label = (Label) anchorPane.getChildren().get(1);
        label.setText(cell.getPlayer().getPiece().getSymbol());
    }
}
