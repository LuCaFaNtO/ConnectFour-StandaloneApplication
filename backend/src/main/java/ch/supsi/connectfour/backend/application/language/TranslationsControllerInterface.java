package ch.supsi.connectfour.backend.application.language;

import java.util.ResourceBundle;

public interface TranslationsControllerInterface {
    ResourceBundle changeLanguage(String languageKey);
}
