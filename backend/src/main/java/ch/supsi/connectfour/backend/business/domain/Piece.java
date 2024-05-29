package ch.supsi.connectfour.backend.business.domain;

public class Piece {
    private String color;
    private String symbol;

    public Piece(String color, String symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
