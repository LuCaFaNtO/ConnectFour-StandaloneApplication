package ch.supsi.connectfour.backend.application.language;

import java.util.ResourceBundle;
import java.util.Set;

public interface TranslationsControllerInterface {
    ResourceBundle changeLanguage(String languageKey);
    Set<String> getSupportedLanguages();
}
