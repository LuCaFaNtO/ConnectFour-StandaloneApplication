package ch.supsi.connectfour.backend.business.language;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public interface TranslationsDataAccessInterface {
    String getTagFromKeyLanguage(String languageKey);
    ResourceBundle getTranslations(Locale locale);
}
