package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.controller.GameController;
import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import ch.supsi.connectfour.frontend.view.UpdateViewInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ColumnsSelectorDispatcher implements Initializable, UpdateViewInterface {
    private final String fxmlLocation = "/columnselector.fxml";

    @FXML
    public GridPane gridPane;

    @FXML
    private FXMLLoader fxmlLoader;

    private final GameControllerInterface gameController = GameController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoader = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    public void playerMove(ActionEvent actionEvent) {
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
}
