package ch.supsi.connectfour.frontend.view;

import javafx.scene.control.Alert;

public class ErrorView implements ErrorViewInterface{
    private static ErrorView instance = null;

    private ErrorView() {}

    public static ErrorView getInstance() {
        return instance == null? instance = new ErrorView() : instance;
    }

    @Override
    public void showPopUpError(String exceptionName,String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(exceptionName);
        alert.setContentText(error);

        // Show the Alert
        alert.show();
    }
}
