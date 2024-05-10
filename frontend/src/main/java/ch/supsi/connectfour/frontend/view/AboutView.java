package ch.supsi.connectfour.frontend.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutView implements Initializable {
    private final String fxmlLocation = "/aboutWindow.fxml";
    private FXMLLoader fxmlLoaderBoardView;
    @FXML
    public Label version;
    @FXML
    public Label artifactId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        version.setText(getClass().getPackage().getImplementationVersion());
        artifactId.setText(getClass().getPackage().getName().split("\\.")[3]);
    }
}
