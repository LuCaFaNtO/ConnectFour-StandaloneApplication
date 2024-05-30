package ch.supsi.connectfour.frontend.view.about;

import ch.supsi.connectfour.frontend.controller.about.AboutViewInterface;
import ch.supsi.connectfour.frontend.controller.edit.language.UpdateLanguageAbstract;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;

public class AboutView extends UpdateLanguageAbstract implements AboutViewInterface {
    private static AboutView instance = null;
    private ResourceBundle resourceBundle;
    private final Properties buildProperties;

    private AboutView() {
        this.resourceBundle = ResourceBundle.getBundle("i18n.labels");

        this.buildProperties = loadFileProperties();
    }

    public static AboutView getInstance() {
        return instance == null? instance = new AboutView():instance;
    }

    private Properties loadFileProperties() {
        Properties properties = new Properties();
        try {
            InputStream p = this.getClass().getResourceAsStream("/pom.properties");
            properties.load(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

    @Override
    public void showAboutInformation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/connect-four.png"))));
        alert.setTitle("Connect4 - About");
        alert.setHeaderText(resourceBundle.getString("AboutWindow.info"));
        alert.setContentText(getVersion() + "\n" + getProjectName() + "\n" + getBuiltDate() + "\n" + getDevelopersName());
        alert.showAndWait();
    }

    private String getVersion() {
        return resourceBundle.getString("AboutWindow.version") + ": " + buildProperties.getProperty("build.version");
    }

    private String getProjectName() {
        return resourceBundle.getString("AboutWindow.name") + ": " + buildProperties.getProperty("build.name");
    }

    private String getDevelopersName() {
        return resourceBundle.getString("AboutWindow.developers") + ": " + buildProperties.getProperty("build.devs");

    }

    private String getBuiltDate() {
        return resourceBundle.getString("AboutWindow.builtDate") + ": " + buildProperties.getProperty("build.timestamp");
    }

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
}
