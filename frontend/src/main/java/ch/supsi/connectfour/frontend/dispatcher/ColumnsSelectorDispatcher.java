package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.controller.GameController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


public class ColumnsSelectorDispatcher {
    private final GameControllerInterface gameController = GameController.getInstance();

    public void insertPiece(ActionEvent actionEvent) {
        Node node = (Node) actionEvent.getSource();
        gameController.insertPiece(GridPane.getColumnIndex(node.getParent()));
    }
}
