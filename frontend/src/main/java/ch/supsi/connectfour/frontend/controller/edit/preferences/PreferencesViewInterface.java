package ch.supsi.connectfour.frontend.controller.edit.preferences;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.List;
import java.util.Set;

public interface PreferencesViewInterface {
    void fillRectangles(List<Rectangle> rectangles, List<String> supportedColors);
    void writeSymbols(List<AnchorPane> anchorPanes, List<String> supportedSymbols);
    void showPreferencesPage();
    void exit(Button closeButton);
}
