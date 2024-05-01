package ch.supsi.connectfour.frontend.model.edit;

import ch.supsi.connectfour.backend.application.language.TranslationsControllerInterface;
import ch.supsi.connectfour.backend.application.language.TranslationsController;
import ch.supsi.connectfour.frontend.controller.edit.LanguageModelInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LanguageModel implements LanguageModelInterface {
    private static LanguageModelInterface instance = null;
    private final TranslationsControllerInterface translationsController;
    private List<UpdateLanguageInterface> updaterLanguageList = new ArrayList<>();

    private LanguageModel() {
        translationsController = TranslationsController.getInstance();
    }

    public static LanguageModelInterface getInstance() {
        return instance == null? instance = new LanguageModel() : instance;
    }

    @Override
    public void setUpdaterLanguageList(UpdateLanguageInterface updaterLanguage){
        updaterLanguageList.add(updaterLanguage);
    }

    @Override
    public void changeLanguage(final String language) {
        ResourceBundle boundle = translationsController.changeLanguage(language);

        for (UpdateLanguageInterface updaterLanguage : updaterLanguageList)
            updaterLanguage.updateLanguage(boundle);
    }
}
