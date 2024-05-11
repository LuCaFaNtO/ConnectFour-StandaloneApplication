package ch.supsi.connectfour.backend.business.domain;

public class Grid {
    public static final int NUM_ROWS = 6;
    public static final int NUM_COLS = 7;
    private Cell[][] grid;
    private Cell modifiedCell;

    public Grid() {
        this.grid = new Cell[NUM_ROWS][NUM_COLS];
        initializeGrid();
        this.modifiedCell = null;
    }

    private void initializeGrid() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    public void insertPiece(final int row, final int column) {
        grid[row][column].setFill(true);
        modifiedCell = grid[row][column];
    }

    // returns the first free row giving the column
    public int getRowFromColumn(final int column) {
        for (int row = NUM_ROWS-1; row >= 0; row--) {
            if (!grid[row][column].isFill()) {
                return row;
            }
        }
        return -1;
    }

    public Cell getModifiedCell() {
        return modifiedCell;
    }

    public boolean isColumnValid(final int col) {
        return col < NUM_COLS && col >= 0;
    }
}
