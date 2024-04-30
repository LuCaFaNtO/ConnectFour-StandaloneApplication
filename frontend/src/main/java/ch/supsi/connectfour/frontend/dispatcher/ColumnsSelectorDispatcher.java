package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.controller.GameController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class ColumnsSelectorDispatcher {
    private final GameControllerInterface gameController = GameController.getInstance();

    public void playerMove(ActionEvent actionEvent) {
        // get the column number corresponding to this event
        // then delegate the work to the GameController
        Node node = (Node) actionEvent.getSource();
        gameController.insertPiece(GridPane.getColumnIndex(node.getParent()));
    }

}
