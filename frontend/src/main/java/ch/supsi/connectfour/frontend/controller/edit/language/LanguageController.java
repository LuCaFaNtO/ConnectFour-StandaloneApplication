package ch.supsi.connectfour.frontend.controller.edit.language;

import ch.supsi.connectfour.frontend.controller.statusGame.StatusGameModelInterface;
import ch.supsi.connectfour.frontend.dispatcher.edit.language.LanguageControllerInterface;
import ch.supsi.connectfour.frontend.model.edit.language.LanguageModel;
import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;
import ch.supsi.connectfour.frontend.model.statusGame.StatusGameModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LanguageController implements LanguageControllerInterface {
    private static LanguageController instance = null;
    private LanguageModelInterface languageModel;
    private final StatusGameModelInterface statusGameModel;
    private List<UpdateLanguageInterface> updaterLanguageList = new ArrayList<>();

    private LanguageController() {
        this.languageModel = LanguageModel.getInstance();
        this.statusGameModel = StatusGameModel.getInstance();
    }

    public static LanguageController getInstance() {
        return instance == null ? instance = new LanguageController() : instance;
    }

    @Override
    public void addUpdaterLanguageList(UpdateLanguageInterface updaterLanguage) {
        if (updaterLanguage != null && !updaterLanguageList.contains(updaterLanguage))
            updaterLanguageList.add(updaterLanguage);
    }

    @Override
    public void removeUpdaterLanguageList(UpdateLanguageInterface updaterLanguage) {
        if(updaterLanguage != null && updaterLanguageList.contains(updaterLanguage))
            updaterLanguageList.remove(updaterLanguage);
    }

    @Override
    public void changeLanguage(final String language) {
        languageModel.changeLanguage(language);

        for (UpdateLanguageInterface updaterLanguage : updaterLanguageList)
            updaterLanguage.updateFxmlLoaderWithNewLanguage(languageModel.getCurrentResourceBundle());

        for (UpdateLanguageInterface updaterLanguage : updaterLanguageList)
            updaterLanguage.changeSceneFx();

        statusGameModel.onChangeStatusUpdate();
    }

    @Override
    public Set<String> getSupportedLanguages() {
        return languageModel.getSupportedLanguages();
    }
}
