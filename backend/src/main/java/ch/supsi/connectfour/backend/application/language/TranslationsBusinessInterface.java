package ch.supsi.connectfour.backend.application.language;

import java.util.Locale;
import java.util.ResourceBundle;

public interface TranslationsBusinessInterface {
    void setLocaleDefault();
    ResourceBundle changeLanguage(String languageTag);
    String getTagFromKeyLanguage(String languageKey);
}
