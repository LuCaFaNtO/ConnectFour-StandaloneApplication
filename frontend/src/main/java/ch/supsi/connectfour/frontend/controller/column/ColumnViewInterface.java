package ch.supsi.connectfour.frontend.controller.column;

import javafx.scene.layout.GridPane;

public interface ColumnViewInterface {
    void addColumnButtonGridPane(GridPane buttons);
    void enableColumnButton(final int column);
    void disableColumnButton(final int column);
}
