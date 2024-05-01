package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.controller.GameController;
import ch.supsi.connectfour.frontend.model.edit.UpdateLanguageInterface;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.ResourceBundle;

public class ColumnsSelectorDispatcher implements UpdateLanguageInterface {
    private final GameControllerInterface gameController = GameController.getInstance();

    public void playerMove(ActionEvent actionEvent) {
        // get the column number corresponding to this event
        // then delegate the work to the GameController
        Node node = (Node) actionEvent.getSource();
        gameController.insertPiece(GridPane.getColumnIndex(node.getParent()));
    }

    @Override
    public void updateLanguage(ResourceBundle resourceBundle) {

    }
}
