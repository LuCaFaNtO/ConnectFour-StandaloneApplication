package ch.supsi.connectfour.frontend.view;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class ErrorView implements ErrorViewInterface {
    private static ErrorView instance = null;

    private ErrorView() {
    }

    public static ErrorView getInstance() {
        return instance == null ? instance = new ErrorView() : instance;
    }

    @Override
    public void showPopUpError(String exceptionName, String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/connect-four.png"))));
        alert.setTitle("Connect4 - " + exceptionName);
        alert.setHeaderText("Connect4 Preferences ERROR!");
        alert.setContentText(error);
        alert.showAndWait();
    }
}
