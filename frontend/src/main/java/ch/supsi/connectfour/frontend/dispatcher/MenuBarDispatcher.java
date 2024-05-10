package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.MainFx;
import ch.supsi.connectfour.frontend.controller.edit.language.LanguageController;
import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import ch.supsi.connectfour.frontend.dispatcher.edit.language.LanguageControllerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuBarDispatcher implements UpdateLanguageInterface, Initializable {
    private final LanguageControllerInterface languageController;

    private final String fxmlLocation = "/menuBar.fxml";

    @FXML
    public Menu languagesMenu;

    @FXML
    private Stage primaryStage;

    private FXMLLoader fxmlLoaderMenuBar;

    public MenuBarDispatcher() {
        languageController = LanguageController.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoaderMenuBar = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
        addSupportedLanguages();
    }

    public void newGame(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    public void openGame(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    public void saveGame(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    public void saveGameAs(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    public void quit(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    public void editPreferences(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/preferences.fxml"));
        Parent preferencesDispatcher = loader.load();

        AnchorPane.setTopAnchor(preferencesDispatcher, 0.0);
        AnchorPane.setRightAnchor(preferencesDispatcher, 0.0);
        AnchorPane.setBottomAnchor(preferencesDispatcher, 0.0);
        AnchorPane.setLeftAnchor(preferencesDispatcher, 0.0);
        MainFx.mainBorderPane.setCenter(preferencesDispatcher);


    }

    public void showAbout(ActionEvent actionEvent) {
        FXMLLoader about = new FXMLLoader(getClass().getResource("/aboutWindow.fxml"));
        try {
            Parent root = about.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showHelp(ActionEvent actionEvent) {
        // decode this event
        // delegate it to a suitable controller
    }

    private void addSupportedLanguages() {
        Set<String> availableLanguagesSet = languageController.getSupportedLanguages().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));

        for(String languageName : availableLanguagesSet) {
            MenuItem menuItem = new MenuItem(languageName);
            menuItem.setId(languageName);
            menuItem.setMnemonicParsing(false);
            menuItem.setOnAction(this::changeLanguage);
            menuItem.setText(fxmlLoaderMenuBar.getResources().getString("MenuBar." + languageName));
            languagesMenu.getItems().add(menuItem);
        }
    }

    public void changeLanguage(ActionEvent actionEvent) {
        String idLanguage = ((MenuItem) actionEvent.getSource()).getId();
        languageController.changeLanguage(idLanguage);
    }

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        fxmlLoaderMenuBar = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }

    @Override
    public void changeSceneFx() {
        MainFx.updateSceneMenuBarWithNewLanguage();
    }

    @Override
    public FXMLLoader getFxmlLoader() {
        return fxmlLoaderMenuBar;
    }
}
