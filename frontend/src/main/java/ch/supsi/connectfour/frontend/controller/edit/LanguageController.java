package ch.supsi.connectfour.frontend.controller.edit;

import ch.supsi.connectfour.frontend.dispatcher.edit.LanguageControllerInterface;
import ch.supsi.connectfour.frontend.model.edit.LanguageModel;
import ch.supsi.connectfour.frontend.model.edit.UpdateLanguageInterface;

import java.util.Set;

public class LanguageController implements LanguageControllerInterface {
    private static LanguageController instance = null;
    private LanguageModelInterface languageModel;

    private LanguageController() {
        this.languageModel = LanguageModel.getInstance();
    }

    public void addUpdaterLanguageList(UpdateLanguageInterface updaterLanguage){
        languageModel.addUpdaterLanguageList(updaterLanguage);
    }

    public static LanguageController getInstance() {
        return instance == null ? instance = new LanguageController() : instance;
    }

    @Override
    public void changeLanguage(final String language) {
        languageModel.changeLanguage(language);
    }

    @Override
    public Set<String> getSupportedLanguages() {
        return languageModel.getSupportedLanguages();
    }
}
