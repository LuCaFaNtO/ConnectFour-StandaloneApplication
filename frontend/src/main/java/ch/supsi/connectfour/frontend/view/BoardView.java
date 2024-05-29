package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.business.domain.Cell;
import ch.supsi.connectfour.frontend.model.UpdateGridInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BoardView implements UpdateGridInterface {
    @FXML
    public GridPane grid;

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

    @Override
    public void updateEmptyGrid(int col, int row) {
        for (int i = 0; i < col * row; i++) {
            Circle circle = (Circle) grid.getChildren().stream().map(node -> (AnchorPane) node).toList().get(i).getChildren().get(0);
            circle.setFill(Color.valueOf("1f64ff"));
            Label label = (Label) grid.getChildren().stream().map(node -> (AnchorPane) node).toList().get(i).getChildren().get(1);
            label.setText("");
        }
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
