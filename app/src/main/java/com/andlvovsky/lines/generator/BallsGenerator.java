package com.andlvovsky.lines.generator;

import com.andlvovsky.lines.analyzer.BoardAnalyzer;
import com.andlvovsky.lines.board.Board;
import com.andlvovsky.lines.exception.GameOverException;
import com.andlvovsky.lines.meta.Color;
import com.andlvovsky.lines.meta.GameConstants;
import com.andlvovsky.lines.util.Coordinates;

import java.util.Random;

import static com.andlvovsky.lines.meta.GameConstants.FIRST_BALLS_NUMBER;
import static com.andlvovsky.lines.meta.GameConstants.SIZE;

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
        for (int i = 0; i < FIRST_BALLS_NUMBER; i++) {
            Coordinates coord = findEmptyCell(board);
            board.addBall(coord.i, coord.j, randomColor());
        }
    }

    public static void placeNextBalls(Board board, Color[] colors) {
        for (Color color: colors) {
            Coordinates coord = findEmptyCell(board);
            board.addBall(coord.i, coord.j, color);
        }
    }

    private static Color randomColor() {
        return Color.values()[randUnsignedInt(Color.values().length)];
    }

    private static int randUnsignedInt(int max) {
        Random random = new Random();
        return (random.nextInt() % max + max) % max;
    }

    private static Coordinates findEmptyCell(Board board) {
        int emptyCellsCount = BoardAnalyzer.countEmptyCells(board);
        if (emptyCellsCount == 0)
            throw new GameOverException();
        int randPos = randUnsignedInt(emptyCellsCount);
        for (int i = 0, c = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board.isEmpty(i, j)) {
                    if (randPos == c)
                        return new Coordinates(i, j);
                    c++;
                }
        throw new AssertionError("cannot reach there");
    }
}
