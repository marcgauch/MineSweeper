package ch.gauch.marc.minesweeper;

public enum Visibility {
    COVERED(' '), FLAGGED('!'), DISCOVERED('n'), EXPLODED('*');

    private final char visibility;

    Visibility(final char visibility) {
        this.visibility = visibility;
    }

    public char getVisibility() {
        return this.visibility;
    }
}
