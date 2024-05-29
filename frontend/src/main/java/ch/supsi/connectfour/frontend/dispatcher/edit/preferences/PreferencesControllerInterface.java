package ch.supsi.connectfour.frontend.dispatcher.edit.preferences;

import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.business.domain.Player;
import ch.supsi.connectfour.frontend.controller.edit.preferences.PreferencesViewInterface;
import ch.supsi.connectfour.frontend.model.UpdateGridInterface;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.List;

public interface PreferencesControllerInterface {
    List<Player> getPlayers();
    void setNewPreferences(List<Piece> pieces);
    void addUpdaterGrid(UpdateGridInterface updateGrid);
    void addPreferencesView(PreferencesViewInterface preferencesView);
    void showPreferencesPage();

    void fillRectangles(List<Rectangle> rectangles);
    void writeSymbols(List<AnchorPane> anchorPanes);
    void exit(Button closeButton);
}
