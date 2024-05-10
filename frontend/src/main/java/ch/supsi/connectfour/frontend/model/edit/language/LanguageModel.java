package ch.supsi.connectfour.frontend.model.edit.language;

import ch.supsi.connectfour.backend.application.language.TranslationsControllerInterface;
import ch.supsi.connectfour.backend.application.language.TranslationsController;
import ch.supsi.connectfour.frontend.controller.edit.language.LanguageModelInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class LanguageModel implements LanguageModelInterface {
    private static LanguageModelInterface instance = null;
    private final TranslationsControllerInterface translationsController;
    private List<UpdateLanguageInterface> updaterLanguageList = new ArrayList<>();

    private LanguageModel() {
        translationsController = TranslationsController.getInstance();
    }

    public static LanguageModelInterface getInstance() {
        return instance == null ? instance = new LanguageModel() : instance;
    }

    @Override
    public void addUpdaterLanguageList(UpdateLanguageInterface updaterLanguage) {
        if (updaterLanguage != null && !updaterLanguageList.contains(updaterLanguage))
            updaterLanguageList.add(updaterLanguage);
    }

    @Override
    public void changeLanguage(final String language) {
        ResourceBundle resourceBundle = translationsController.changeLanguage(language);

        for (UpdateLanguageInterface updaterLanguage : updaterLanguageList)
            updaterLanguage.updateFxmlLoaderWithNewLanguage(resourceBundle);

        for (UpdateLanguageInterface updaterLanguage : updaterLanguageList)
            updaterLanguage.changeSceneFx();
    }

    @Override
    public Set<String> getSupportedLanguages() {
        return translationsController.getSupportedLanguages();
    }
}
