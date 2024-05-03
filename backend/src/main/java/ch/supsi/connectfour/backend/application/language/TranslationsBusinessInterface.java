package ch.supsi.connectfour.backend.application.language;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public interface TranslationsBusinessInterface {
    void setLocaleDefault();
    ResourceBundle changeLanguage(String languageTag);
    String getTagFromKeyLanguage(String languageKey);
    Set<String> getSupportedLanguages();
}
