package ch.supsi.connectfour.frontend;

import ch.supsi.connectfour.frontend.controller.AboutController;
import ch.supsi.connectfour.frontend.controller.GameController;
import ch.supsi.connectfour.frontend.controller.InfoBarController;
import ch.supsi.connectfour.frontend.controller.InfoBarControllerInterface;
import ch.supsi.connectfour.frontend.controller.column.ColumnController;
import ch.supsi.connectfour.frontend.controller.column.ColumnControllerInterface;
import ch.supsi.connectfour.frontend.controller.column.ColumnViewInterface;
import ch.supsi.connectfour.frontend.controller.edit.language.LanguageController;
import ch.supsi.connectfour.frontend.controller.edit.preferences.PreferencesController;
import ch.supsi.connectfour.frontend.controller.savingGame.SavingGameController;
import ch.supsi.connectfour.frontend.controller.statusGame.StatusGameController;
import ch.supsi.connectfour.frontend.dispatcher.*;
import ch.supsi.connectfour.frontend.dispatcher.edit.language.LanguageControllerInterface;
import ch.supsi.connectfour.frontend.dispatcher.edit.preferences.PreferencesControllerInterface;
import ch.supsi.connectfour.frontend.dispatcher.edit.preferences.PreferencesDispatcher;
import ch.supsi.connectfour.frontend.model.UpdateGridInterface;
import ch.supsi.connectfour.frontend.model.edit.language.UpdaterLanguageInterface;
import ch.supsi.connectfour.frontend.model.statusGame.UpdateStatusViewInterface;
import ch.supsi.connectfour.frontend.view.AboutView;
import ch.supsi.connectfour.frontend.view.InfoBarView;
import ch.supsi.connectfour.frontend.view.column.ColumnView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFx extends Application {
    public static final String APP_TITLE = "Connect4";
    public static ResourceBundle resourceBundle;
    public static BorderPane mainBorderPane;
    public static Parent board;
    public static BorderPane gameBoardBorderPane = new BorderPane();

    private static UpdaterLanguageInterface menuBarDispatcher;
    private ColumnsSelectorDispatcher columnsSelectorDispatcher;
    public static UpdateGridInterface boardView;
    private static UpdaterLanguageInterface infoBarView;
    private UpdaterLanguageInterface preferencesDispatcher;
    private static PreStartDispatcher preStartDispatcher;
    private UpdaterLanguageInterface saveChoicePopUp;

    private final GameControllerInterface gameController;
    private static LanguageControllerInterface languageController;
    private final AboutControllerInterface aboutController;
    private final PreferencesControllerInterface preferencesController;
    private final InfoBarControllerInterface infoBarController;
    private static StatusGameControllerInterface statusGameController;
    private SavingGameControllerInterface savingGameController;
    private final ColumnControllerInterface columnController;

    private final ColumnViewInterface columnView;


    public MainFx() throws InstantiationException {
        languageController = LanguageController.getInstance();
        this.gameController = GameController.getInstance();
        this.aboutController = AboutController.getInstance();
        this.preferencesController = PreferencesController.getInstance();
        this.infoBarController = InfoBarController.getInstance();
        this.savingGameController = SavingGameController.getInstance();
        statusGameController = StatusGameController.getInstance();
        this.columnController = ColumnController.getInstance();
        this.columnView = ColumnView.getInstance();
        resourceBundle = ResourceBundle.getBundle("i18n.labels");
    }

    @Override
    public void start(Stage primaryStage) {
        // handle the main window close request
        // in real life, this event should not be dealt with here!
        // it should actually be delegated to a suitable ExitController!
        primaryStage.setOnCloseRequest(
                windowEvent -> {
                    // consume the window event (the main window would be closed otherwise no matter what)
                    windowEvent.consume();

                    // hard close the primary stage
                    // javafx guarantees the clean exit of the javafx platform, when the last application stage is closed
                    primaryStage.close();
                }
        );

        //PREFERENCES DISPATCHER
        try {
            URL fxmlPreferencesDispatcher = getClass().getResource("/preferences.fxml");
            if (fxmlPreferencesDispatcher == null) {
                return;
            }
            FXMLLoader preferencesLoader = new FXMLLoader(fxmlPreferencesDispatcher, resourceBundle);
            preferencesLoader.load();
            preferencesDispatcher = preferencesLoader.getController();
            this.preferencesController.addPreferencesView((PreferencesDispatcher) preferencesDispatcher);
            this.languageController.addUpdaterLanguageList(preferencesDispatcher);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //PRESTART DISPATCHER
        try {
            URL fxmlPreStartDispatcher = getClass().getResource("/prestart.fxml");
            if (fxmlPreStartDispatcher == null) {
                return;
            }
            FXMLLoader preStartLoader = new FXMLLoader(fxmlPreStartDispatcher, resourceBundle);
            preStartLoader.load();
            preStartDispatcher = preStartLoader.getController();
            statusGameController.addUpdateViewByStatus(preStartDispatcher);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // ABOUT
        try {
            URL fxmlAboutUrl = getClass().getResource("/aboutWindow.fxml");
            if (fxmlAboutUrl == null) {
                return;
            }
            FXMLLoader aboutLoader = new FXMLLoader(fxmlAboutUrl, resourceBundle);
            aboutLoader.load();
            UpdaterLanguageInterface aboutView = aboutLoader.getController();
            this.aboutController.addAboutView((AboutView) aboutView);
            languageController.addUpdaterLanguageList(aboutView);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // SAVEPOPUPCHOICE
        try {
            URL fxmlSaveChoiceUrl = getClass().getResource("/saveGamePopUp.fxml");
            if (fxmlSaveChoiceUrl == null) {
                return;
            }
            FXMLLoader saveChoiceLoader = new FXMLLoader(fxmlSaveChoiceUrl, resourceBundle);
            saveChoiceLoader.load();
            this.saveChoicePopUp = saveChoiceLoader.getController();
            this.savingGameController.addSavingGamePopUp((SaveGameChoicePopUpDispatcher) saveChoicePopUp);
            languageController.addUpdaterLanguageList(saveChoicePopUp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // MENU BAR
        MenuBar menuBar;
        try {
            URL fxmlUrl = getClass().getResource("/menubar.fxml");
            if (fxmlUrl == null) {
                return;
            }
            FXMLLoader menuBarLoader = new FXMLLoader(fxmlUrl, resourceBundle);
            menuBar = menuBarLoader.load();

            menuBarDispatcher = menuBarLoader.getController();
            languageController.addUpdaterLanguageList(menuBarDispatcher);
            statusGameController.addUpdateViewByStatus((UpdateStatusViewInterface) menuBarDispatcher);
            ((SaveGameChoicePopUpDispatcher) saveChoicePopUp).addMenuBarDispatcher((MenuBarDispatcher) menuBarDispatcher);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // CONNECT-FOUR COLUMN SELECTORS
        Parent columnSelectors;
        try {
            URL fxmlUrl = getClass().getResource("/columnselectors.fxml");
            if (fxmlUrl == null) {
                return;
            }

            FXMLLoader columnSelectorsLoader = new FXMLLoader(fxmlUrl, resourceBundle);
            columnSelectors = columnSelectorsLoader.load();
            this.columnsSelectorDispatcher = columnSelectorsLoader.getController();
            this.columnController.addColumnView(columnView);
            this.columnView.addColumnButtonGridPane((GridPane) columnSelectors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // CONNECT-FOUR BOARD
        try {
            URL fxmlUrl = getClass().getResource("/gameboard.fxml");
            if (fxmlUrl == null) {
                return;
            }

            FXMLLoader boardLoader = new FXMLLoader(fxmlUrl, resourceBundle);
            board = boardLoader.load();
            boardView = boardLoader.getController();
            this.gameController.addUpdaterGrid(boardView);
            this.preferencesController.addUpdaterGrid(boardView);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // INFO BAR
        Parent infoBar;
        try {
            URL fxmlUrl = getClass().getResource("/infobar.fxml");
            if (fxmlUrl == null) {
                // resource file not found
                return;
            }

            FXMLLoader infoBarLoader = new FXMLLoader(fxmlUrl, resourceBundle);
            infoBar = infoBarLoader.load();
            this.infoBarView = infoBarLoader.getController();
            this.languageController.addUpdaterLanguageList(infoBarView);
            this.infoBarController.addInfoBar((InfoBarView) infoBarView);
            statusGameController.addUpdateViewByStatus((UpdateStatusViewInterface) infoBarView);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // BORDER PANE
        mainBorderPane = new BorderPane();

        mainBorderPane.setTop(menuBar);
        gameBoardBorderPane.setTop(columnSelectors);
        gameBoardBorderPane.setCenter(board);

        statusGameController.setStatusToPreStart();

        mainBorderPane.setBottom(infoBar);

        // SCENE
        Scene scene = new Scene(mainBorderPane);

        // PRIMARY STAGE
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/connect-four.png")));
        primaryStage.setTitle(MainFx.APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void showGameBoard() {
        mainBorderPane.setCenter(gameBoardBorderPane);
    }

    public static void showPreStartPage(AnchorPane preStartPage){
        mainBorderPane.setCenter(preStartPage);
    }

    public static void quit(){
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
