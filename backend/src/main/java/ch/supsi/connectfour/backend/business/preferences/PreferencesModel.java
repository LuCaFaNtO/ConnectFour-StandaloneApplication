package ch.supsi.connectfour.backend.business.preferences;

import ch.supsi.connectfour.backend.application.exceptions.IllegalPreferencesException;
import ch.supsi.connectfour.backend.application.preferences.PreferencesBusinessInterface;
import ch.supsi.connectfour.backend.business.domain.Piece;
import ch.supsi.connectfour.backend.dataaccess.preferences.PreferencesDataAccess;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PreferencesModel implements PreferencesBusinessInterface {
    private static PreferencesModel instance = null;
    private final Set<String> supportedColors;
    private final Set<String> supportedSymbols;
    private List<Piece> currentPreferencesPieces;

    private PreferencesModel() {
        PreferencesDataAccessInterface preferencesDataAccess = PreferencesDataAccess.getInstance();

        this.supportedColors = preferencesDataAccess.getSupportedColorsValues();
        this.supportedSymbols = preferencesDataAccess.getSupportedSymbols();
        this.currentPreferencesPieces = getDefaultPieces();
    }

    public static PreferencesModel getInstance() {
        return instance == null ? instance = new PreferencesModel() : instance;
    }

    @Override
    public Set<String> getSupportedColors() {
        return Collections.unmodifiableSet(supportedColors);
    }

    @Override
    public Set<String> getSupportedSymbols() {
        return Collections.unmodifiableSet(supportedSymbols);
    }


    private List<Piece> getDefaultPieces() {
        Piece pieceDefault1 = new Piece(supportedColors.stream().toList().get(0), supportedSymbols.stream().toList().get(0));
        Piece pieceDefault2 = new Piece(supportedColors.stream().toList().get(1), supportedSymbols.stream().toList().get(1));

        return List.of(pieceDefault1, pieceDefault2);
    }

    @Override
    public List<Piece> getCurrentPieces() {
        return currentPreferencesPieces;
    }

    @Override
    public void setCurrentPieces(List<Piece> currentPieces) {
        this.currentPreferencesPieces = currentPieces;
    }

    @Override
    public void checkPiecesAreDifferent(List<Piece> pieces) throws IllegalPreferencesException {
        String color1 = pieces.get(0).getColor();
        String symbol1 = pieces.get(0).getSymbol();

        String color2 = pieces.get(1).getColor();
        String symbol2 = pieces.get(1).getSymbol();

        if (color1.equals(color2) && symbol1.equals(symbol2))
            throw new IllegalPreferencesException("Pieces cannot be equal. \nChange almost one color or symbol");
    }
}
