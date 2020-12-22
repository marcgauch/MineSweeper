package ch.gauch.marc.minesweeper;

public class Main {
    public static final int nRows = 5;
    public static final int nCols = 5;
    public static final int nMines = 1;

    public static void main(String[] args) {
        MineSweeper game = new MineSweeper(nRows, nCols, nMines);
        GuessHandler guessHandler = new GuessHandler(nRows, nCols);

        game.drawGame(); // draw empty grid
        Guess g = guessHandler.getGuess();    // get first guess

        game.prepareFields(g.row, g.col); // now that we know where the player starts we create the grid. (Note: Player will never hit a mine on first click)

        game.drawGame(); // ans now show de game again
        // Game-Loop
        while (game.isAlive() && game.countCoveredNeutral() > 0) {
            g = guessHandler.getGuess();
            if (g.hardtest) { // hardtest: Testing if theres a mine with stepping on it...
                game.guess(g.row, g.col);
            } else {
                game.mark(g.row, g.col);
            }
            game.drawGame();
        }
        // END OF GAME
        if (game.isAlive()) {
            System.out.println("NICE!");
        } else {
            System.out.println("STEPPED ON A MINE");
        }
    }
}
