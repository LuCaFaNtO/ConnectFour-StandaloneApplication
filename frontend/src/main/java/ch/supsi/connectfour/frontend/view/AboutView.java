package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutView implements Initializable, UpdateLanguageInterface {
    private final String fxmlLocation = "/aboutWindow.fxml";
    private FXMLLoader fxmlLoaderAboutView;
    @FXML
    public Label version;
    @FXML
    public Label artifactId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fxmlLoaderAboutView = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);

        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model;
            if ((new File("frontend/pom.xml")).exists())
                model = reader.read(new FileReader("frontend/pom.xml"));
            else
                model = reader.read(new InputStreamReader(getClass().getResourceAsStream("/META-INF/maven/ch.supsi.connectfour/frontend/pom.xml")));
            version.setText(model.getVersion());
            artifactId.setText(model.getArtifactId());
        } catch (IOException | XmlPullParserException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeSceneFx() {
    }

    public void showAboutInformation() {
        try {
            Parent root = new FXMLLoader(getFxmlLoader().getLocation(), getFxmlLoader().getResources()).load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/connect-four.png")));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FXMLLoader getFxmlLoader() {
        return fxmlLoaderAboutView;
    }

    @Override
    public void updateFxmlLoaderWithNewLanguage(ResourceBundle resourceBundle) {
        fxmlLoaderAboutView = new FXMLLoader(getClass().getResource(fxmlLocation), resourceBundle);
    }
}
