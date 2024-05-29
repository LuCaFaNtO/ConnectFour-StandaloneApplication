package ch.supsi.connectfour.frontend.controller.edit.language;

import ch.supsi.connectfour.frontend.dispatcher.edit.language.LanguageControllerInterface;
import ch.supsi.connectfour.frontend.model.edit.language.LanguageModel;

import java.util.ArrayList;
import java.util.List;

public class LanguageController implements LanguageControllerInterface {
    private static LanguageController instance = null;
    private final LanguageModelInterface languageModel;
    private final List<UpdaterLanguageInterface> updaterLanguageList = new ArrayList<>();

    private LanguageController() {
        this.languageModel = LanguageModel.getInstance();
    }

    public static LanguageController getInstance() {
        return instance == null ? instance = new LanguageController() : instance;
    }

    @Override
    public void addUpdaterLanguageList(UpdaterLanguageInterface updaterLanguage) {
        if (updaterLanguage != null && !updaterLanguageList.contains(updaterLanguage))
            updaterLanguageList.add(updaterLanguage);
    }

    @Override
    public void changeLanguage(final String language) {
        languageModel.changeLanguage(language);

        for (UpdaterLanguageInterface updaterLanguage : updaterLanguageList)
            updaterLanguage.updateFxmlLoaderWithNewLanguage(languageModel.getCurrentResourceBundle());

        for (UpdaterLanguageInterface updaterLanguage : updaterLanguageList)
            updaterLanguage.changeImmediatelySceneFx();
    }

}
