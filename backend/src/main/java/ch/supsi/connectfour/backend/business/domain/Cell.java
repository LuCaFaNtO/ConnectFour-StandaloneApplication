package ch.supsi.connectfour.backend.business.domain;

public class Cell {
    private int row;
    private int col;
    private boolean fill;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.fill = false;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public boolean isFill() {
        return fill;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
