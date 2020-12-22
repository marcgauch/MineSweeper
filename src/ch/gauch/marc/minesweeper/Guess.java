package ch.gauch.marc.minesweeper;

public class Guess {
    public final boolean hardtest;
    public final int row;
    public final int col;

    public Guess(boolean hardtest, int row, int col) {
        this.hardtest = hardtest;
        this.row = row;
        this.col = col;
    }
}