package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.model.edit.language.UpdaterLanguageInterface;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.maven.model.Developer;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.ResourceBundle;

public class AboutView implements Initializable, UpdaterLanguageInterface {
    private final String fxmlLocation = "/aboutWindow.fxml";
    private FXMLLoader fxmlLoaderAboutView;
    private Model model;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoaderAboutView = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            if ((new File("frontend/pom.xml")).exists())
                model = reader.read(new FileReader("frontend/pom.xml"));
            else
                model = reader.read(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/META-INF/maven/ch.supsi.connectfour/frontend/pom.xml"))));
        } catch (XmlPullParserException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeSceneFx() {
    }

    public void showAboutInformation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/connect-four.png"))));
        alert.setTitle("Connect4 - About");
        alert.setHeaderText(fxmlLoaderAboutView.getResources().getString("AboutWindow.info"));
        alert.setContentText(getVersion() + "\n" + getArtifactId() + "\n" + getBuiltDate() + "\n" + getDevelopersName());
        alert.showAndWait();
    }

    private String getVersion() {
        return fxmlLoaderAboutView.getResources().getString("AboutWindow.version") + ": " + model.getVersion();
    }

    private String getArtifactId() {
        return fxmlLoaderAboutView.getResources().getString("AboutWindow.artifactID") + ": " + model.getArtifactId();
    }

    private String getDevelopersName() {
        StringBuilder sb = new StringBuilder();
        for (Developer developer : model.getDevelopers())
            sb.append(developer.getName()).append(" - ");
        sb.replace(sb.length() - 3, sb.length(), "");
        return fxmlLoaderAboutView.getResources().getString("AboutWindow.developers") + ": " + sb;
    }

    private String getBuiltDate() {
        try {
            File p = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            BasicFileAttributes attributes = Files.readAttributes(p.toPath(), BasicFileAttributes.class);
            return fxmlLoaderAboutView.getResources().getString("AboutWindow.builtDate") + " " + simpleDateFormat.format(attributes.creationTime().toMillis());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        fxmlLoaderAboutView = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }
}
