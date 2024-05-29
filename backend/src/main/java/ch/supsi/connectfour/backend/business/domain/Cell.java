package ch.supsi.connectfour.backend.business.domain;

public class Cell {
    private final int row;
    private final int col;
    private boolean fill;
    private Player player;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.fill = false;
        this.player = null;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
