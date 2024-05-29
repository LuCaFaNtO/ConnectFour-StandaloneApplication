package ch.supsi.connectfour.backend.application.language;

import java.util.ResourceBundle;
import java.util.Set;

public interface TranslationsBusinessInterface {
    ResourceBundle changeLanguage(String languageTag);

    String getTagFromKeyLanguage(String languageKey);

    Set<String> getSupportedLanguages();
}
