package ch.gauch.marc.minesweeper;

import java.util.Random;

public class Util {
    public static int getRandom(int lowerBound, int upperBound) {
        return new Random().nextInt(upperBound - lowerBound) + lowerBound;
    }
}
