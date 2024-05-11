package ch.supsi.connectfour.backend.business.domain;

public class Player {
    private final String name;
    private Piece piece;

    public Player(String name, Piece piece){
        this.name = name;
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public String getName() {
        return name;
    }
}
