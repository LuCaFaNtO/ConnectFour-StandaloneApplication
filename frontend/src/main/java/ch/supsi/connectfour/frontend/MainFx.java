package ch.supsi.connectfour.frontend;


import ch.supsi.connectfour.backend.business.Cell;
import ch.supsi.connectfour.frontend.controller.GameController;
import ch.supsi.connectfour.frontend.controller.edit.LanguageController;
import ch.supsi.connectfour.frontend.model.edit.UpdateLanguageInterface;
import ch.supsi.connectfour.frontend.view.BoardView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFx extends Application {
    public static final String APP_TITLE = "connectfour";
    public static ResourceBundle resourceBundle;
    private static BorderPane mainBorderPane;

    private static UpdateLanguageInterface menuBarDispatcher;
    private UpdateLanguageInterface columnsSelectorDispatcher;
    private BoardView boardView;
    private static UpdateLanguageInterface infoBarView;

    private GameController gameController;
    private LanguageController languageController;

    public MainFx() throws InstantiationException {
        this.languageController = LanguageController.getInstance();
        this.gameController = GameController.getInstance();
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

        // MENU BAR
        MenuBar menuBar;
        try {
            URL fxmlUrl = getClass().getResource("/menubar.fxml");
            if (fxmlUrl == null) {
                return;
            }
            System.out.println();
            FXMLLoader menuBarLoader = new FXMLLoader(fxmlUrl, resourceBundle);
            menuBar = menuBarLoader.load();

            menuBarDispatcher = menuBarLoader.getController();
            this.languageController.addUpdaterLanguageList(menuBarDispatcher);
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // CONNECT-FOUR BOARD
        Parent board;
        try {
            URL fxmlUrl = getClass().getResource("/gameboard.fxml");
            if (fxmlUrl == null) {
                return;
            }

            FXMLLoader boardLoader = new FXMLLoader(fxmlUrl, resourceBundle);
            board = boardLoader.load();
            this.boardView = boardLoader.getController();
            this.gameController.addUpdaterGrid(this.boardView);
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // BORDER PANE
        mainBorderPane = new BorderPane();

        mainBorderPane.setTop(menuBar);

        BorderPane gameBoardBorderPane = new BorderPane();
        gameBoardBorderPane.setTop(columnSelectors);
        gameBoardBorderPane.setCenter(board);
        mainBorderPane.setCenter(gameBoardBorderPane);

        mainBorderPane.setBottom(infoBar);

        // SCENE
        Scene scene = new Scene(mainBorderPane);

        // PRIMARY STAGE
        primaryStage.setTitle(MainFx.APP_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void updateSceneMenuBarWithNewLanguage() {
        try {

            mainBorderPane.setTop(menuBarDispatcher.getFxmlLoader().load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateSceneInfoBarWithNewLanguage() {
        try {
            mainBorderPane.setBottom(infoBarView.getFxmlLoader().load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
