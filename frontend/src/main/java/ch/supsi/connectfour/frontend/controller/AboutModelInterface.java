package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.model.edit.language.UpdateLanguageInterface;

public interface AboutModelInterface {
    void showAbout();
    void addAboutView(UpdateLanguageInterface aboutView);
}
