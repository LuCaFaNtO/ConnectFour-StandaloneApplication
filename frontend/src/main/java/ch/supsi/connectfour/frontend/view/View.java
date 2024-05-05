package ch.supsi.connectfour.frontend.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.util.ResourceBundle;

public abstract class View {
    @FXML
    protected FXMLLoader fxmlLoader;
    protected String fxmlLocation;

    protected View(String location, ResourceBundle bundle) {
        fxmlLoader = new FXMLLoader(getClass().getResource(location), bundle);
    }

    protected View(String location) {
        fxmlLoader = new FXMLLoader(getClass().getResource(location));
    }
}
