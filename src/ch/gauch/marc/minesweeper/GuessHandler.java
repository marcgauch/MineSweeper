package ch.gauch.marc.minesweeper;

import java.util.Scanner;

public class GuessHandler {

    private final int nRows, nCols;

    public GuessHandler(int nRows, int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
    }

    /**
     * Asking the player where to open next.
     * Handles wrong inputs. Will only return a valid
     *
     * @return a valid guess (may be a playe where the user already checked. doesn't matter for the game)
     */
    public Guess getGuess() {
        boolean ok;
        boolean hardtest = true;
        int row = 0;
        int col = 0;
        do {
            ok = true;
            System.out.println("__________________________________________________________");
            System.out.println("Geben Sie ein Kommando ein:");
            System.out.println("T x y (z.B. T 2 C testet Feld Zeile 2, Spalte C auf Mine)");
            System.out.println("M x y (z.B. M 6 A kehrt Markierung Feld Zeile 6, Spalte A)");

            Scanner scanner = new Scanner(System.in);
            final String[] commands = scanner.nextLine().trim().toUpperCase().split(" ");
            if (commands.length != 3) {
                ok = false;
                System.out.println("Ungenaue Angabe");
                continue;
            }
            if (!commands[0].equals("T") && !commands[0].equals("M")) {
                ok = false;
                System.out.println("Befehl muss 'T' oder 'M' sein");
                continue;
            }
            hardtest = commands[0].equals("T");
            final String a = commands[1];
            final String b = commands[2];

            try {
                row = Integer.parseInt(a) - 1;
                col = (int) b.charAt(0) - 65;
            } catch (NumberFormatException e) {
                try {
                    row = Integer.parseInt(b) - 1;
                    col = (int) a.charAt(0) - 65;
                } catch (Exception ex) {
                    ok = false;
                    System.out.println("Zwei mal Spalte angegeben");
                    continue;
                    // space for error messages
                }
            }
            if (row < 0 || row > nRows || col < 0 || col > nCols) {

                System.out.println("Feld nicht auffindbar");
                ok = false;
                continue;
            }
        } while (!ok);
        return new Guess(hardtest, row, col);
    }
}
