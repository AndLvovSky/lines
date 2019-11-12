package com.andlvovsky.lines.generator;

import com.andlvovsky.lines.board.Board;
import com.andlvovsky.lines.meta.Color;
import com.andlvovsky.lines.meta.GameConstants;

import java.util.Random;

public class BallsGenerator {
    private BallsGenerator() {
    }

    public static Color[] generateNextColors() {
        Color[] nextColors = new Color[GameConstants.COLORS_PREDICTED];
        for (int i = 0; i < GameConstants.COLORS_PREDICTED; i++) {
            nextColors[i] = randomColor();
        }
        return nextColors;
    }

    public static void placeFirstBalls(Board board) {
        throw new UnsupportedOperationException();
    }

    public static void placeNextBalls(Board board, Color[] colors) {
        throw new UnsupportedOperationException();
    }

    private static Color randomColor() {
        Random random = new Random();
        int randIndex = random.nextInt() % Color.values().length;
        return Color.values()[randIndex];
    }
}
