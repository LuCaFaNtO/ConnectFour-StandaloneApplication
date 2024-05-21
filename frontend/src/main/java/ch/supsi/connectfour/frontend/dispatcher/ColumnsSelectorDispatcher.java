package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.controller.GameController;
import ch.supsi.connectfour.frontend.view.UpdateViewInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class ColumnsSelectorDispatcher implements UpdateViewInterface {
    @FXML
    public GridPane gridPane;

    private final GameControllerInterface gameController = GameController.getInstance();

    public void insertPiece(ActionEvent actionEvent) {
        // get the column number corresponding to this event
        // then delegate the work to the GameController
        Node node = (Node) actionEvent.getSource();
        gameController.insertPiece(GridPane.getColumnIndex(node.getParent()));
    }

    @Override
    public void changeSceneFx() {

    }

    @Override
    public FXMLLoader getFxmlLoader() {
        return null;
    }

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
