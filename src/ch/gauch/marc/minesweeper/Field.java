package ch.gauch.marc.minesweeper;

public class Field {
    private int value = 0;
    private Visibility visibility = Visibility.COVERED;
    private boolean bomb = false;

    public void toggleMark() {
        if (this.visibility == Visibility.COVERED) this.visibility = Visibility.FLAGGED;
        else if (this.visibility == Visibility.FLAGGED) this.visibility = Visibility.COVERED;
    }

    public char uncover() {
        if (this.bomb) {
            this.visibility = Visibility.EXPLODED;
        } else {
            this.visibility = Visibility.DISCOVERED;
        }
        return this.getLabel();
    }

    public char getLabel() {
        if (this.visibility == Visibility.DISCOVERED) {
            return (char) (48 + this.value);
        }
        return this.visibility.getVisibility();
    }

    public void setBomb() {
        this.bomb = true;
    }

    public void incrementValue() {
        ++this.value;
    }

    public boolean isBomb() {
        return this.bomb;
    }

    public Visibility getVisibility() {
        return this.visibility;
    }
}
