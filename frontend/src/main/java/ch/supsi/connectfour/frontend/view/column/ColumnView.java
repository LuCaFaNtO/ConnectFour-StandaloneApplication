package ch.supsi.connectfour.frontend.view.column;

import ch.supsi.connectfour.frontend.controller.column.ColumnViewInterface;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class ColumnView implements ColumnViewInterface {
    private static ColumnView instance = null;
    public GridPane gridPane;

    private ColumnView() {}

    public static ColumnView getInstance() {
        return instance == null ? instance = new ColumnView() : instance;
    }

    @Override
    public void addColumnButtonGridPane(GridPane buttons) {
        this.gridPane = buttons;
    }

    @Override
    public void disableColumnButton(final int column) {
        AnchorPane anchorPane = gridPane.getChildren().stream()
                .filter(node -> GridPane.getColumnIndex(node) == column)
                .map(node -> (AnchorPane) node)
                .findFirst()
                .orElseThrow();

        Button button = (Button) anchorPane.getChildren().get(0);
        button.setDisable(true);
        button.setText("✖");
    }

    @Override
    public void enableColumnButton(final int column) {
        AnchorPane anchorPane = gridPane.getChildren().stream()
                .filter(node -> GridPane.getColumnIndex(node) == column)
                .map(node -> (AnchorPane) node)
                .findFirst()
                .orElseThrow();

        Button button = (Button) anchorPane.getChildren().get(0);
        button.setDisable(false);
        button.setText("▼");
    }
}
