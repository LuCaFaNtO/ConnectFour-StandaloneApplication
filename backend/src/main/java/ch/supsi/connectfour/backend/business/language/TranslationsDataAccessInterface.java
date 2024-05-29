package ch.supsi.connectfour.backend.business.language;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public interface TranslationsDataAccessInterface {
    String getTagFromKeyLanguage(String languageKey);

    ResourceBundle getTranslations(Locale locale);

    Set<String> getSupportedLanguageKeys();
}
