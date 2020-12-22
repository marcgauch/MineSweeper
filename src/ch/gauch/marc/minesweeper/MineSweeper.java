package ch.gauch.marc.minesweeper;

public class MineSweeper {
    private final int nRows;
    private final int nCols;
    private final int nMines;
    private final Field[][] fields;
    private boolean alive;

    public MineSweeper(int nRows, int nCols, int nMines) {
        if (nRows * nCols < nMines) throw new RuntimeException("Field to small for all those mines.");
        this.nRows = nRows;
        this.nCols = nCols;
        this.nMines = nMines;
        this.alive = true;
        fields = new Field[nRows][nCols];
        for (int r = 0; r < nRows; ++r) {
            for (int c = 0; c < nCols; ++c) {
                fields[r][c] = new Field();
            }
        }
    }

    public void mark(int row, int col) {
        this.fields[row][col].toggleMark();
    }

    public void guess(int row, int col) {
        final char response = this.fields[row][col].uncover();
        if (response == '*') {
            this.alive = false;
        } else if (response == '0') {
            // uncover all neighbors
            // up
            if (row > 0 && this.fields[row - 1][col].getVisibility() == Visibility.COVERED) {
                this.guess(row - 1, col);
            }
            // right
            if (col < this.nCols - 1 && this.fields[row][col + 1].getVisibility() == Visibility.COVERED) {
                this.guess(row, col + 1);
            }
            // down
            if (row < this.nRows - 1 && this.fields[row + 1][col].getVisibility() == Visibility.COVERED) {
                this.guess(row + 1, col);
            }
            // left
            if (col > 0 && this.fields[row][col - 1].getVisibility() == Visibility.COVERED) {
                this.guess(row, col - 1);
            }
        }
    }

    public void prepareFields(int firstRow, int firstCol) {
        // Place bombs
        while (this.countCoveredMines() < this.nMines) {
            final int row = Util.getRandom(0, this.nRows);
            final int col = Util.getRandom(0, this.nCols);
            if (firstCol != col || firstRow != row) {
                this.fields[row][col].setBomb();
            }
        }
        // update values of all other fields
        for (int r = 0; r < nRows; ++r) {
            for (int c = 0; c < nCols; ++c) {
                if (fields[r][c].isBomb()) {
                    // update all neighbors
                    for (int r_ = -1; r_ <= 1; ++r_) {
                        for (int c_ = -1; c_ <= 1; ++c_) {
                            try {
                                fields[r + r_][c + c_].incrementValue();
                            } catch (Exception e) {
                                // WE DONT CARE. THIS FIELD JUST DOES NOT EXIST ¯\_(ツ)_/¯
                            }
                        }
                    }
                }

            }
        }
        this.guess(firstRow, firstCol);
    }

    public boolean isAlive() {
        return this.alive;
    }

    public int countCoveredMines() {
        int count = 0;
        for (int r = 0; r < nRows; ++r) {
            for (int c = 0; c < nCols; ++c) {
                final Field f = fields[r][c];
                if (f.isBomb() && f.getVisibility() == Visibility.COVERED) ++count;
            }
        }
        return count;
    }

    public int countCoveredNeutral() {
        int count = this.nRows * this.nCols;
        for (int r = 0; r < nRows; ++r) {
            for (int c = 0; c < nCols; ++c) {
                final Field f = fields[r][c];
                final Visibility v = f.getVisibility();
                if (f.isBomb() || v == Visibility.DISCOVERED) --count;
            }
        }
        return count;
    }

    public void drawGame() {
        System.out.print("  |");
        StringBuilder sb = new StringBuilder("--+");
        for (int i = 0; i < this.nCols; ++i) {
            System.out.print(" " + Character.toString((char) 65 + i) + " ");
            sb.append("---");
        }
        System.out.println();
        System.out.println(sb);
        for (int i = 1; i <= this.nRows; ++i) {
            this.printLine(i);
        }
    }

    private void printLine(int lineNumber) {
        if (lineNumber < 10) {
            System.out.print(" ");
        }
        System.out.print(lineNumber + "|");
        for (int i = 0; i < this.nCols; ++i) {
            System.out.print(" " + this.fields[lineNumber - 1][i].getLabel() + " ");

        }
        System.out.println();
    }
}
