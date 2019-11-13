package com.andlvovsky.lines.analyzer;

import com.andlvovsky.lines.board.Board;
import com.andlvovsky.lines.game.Move;
import com.andlvovsky.lines.meta.Color;
import com.andlvovsky.lines.meta.Score;
import com.andlvovsky.lines.util.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.andlvovsky.lines.meta.GameConstants.SIZE;

public class BoardAnalyzer {
    private static final Coordinates[] transitions = {
            new Coordinates(0, 1),
            new Coordinates(1, 0),
            new Coordinates(1, 1),
            new Coordinates(1, -1)
    };

    private BoardAnalyzer() {
    }

    public static boolean isValidMove(Board board, Move move) {
        return board.isEmpty(move.iFrom(), move.jFrom()) &&
                board.isEmpty(move.iTo(), move.jTo()) &&
                BoardGraphAlgorithms.doesPathExist(board, move);
    }

    public static int removeBallsAndCalculateAddingScore(Board board) {
        List<Coordinates> horizontal = findLongest(board, transitions[0]);
        List<Coordinates> vertical = findLongest(board, transitions[1]);
        List<Coordinates> mainDiagonal = findLongest(board, transitions[2]);
        List<Coordinates> antiDiagonal = findLongest(board, transitions[3]);

        List<Coordinates> allCoords = new ArrayList<>();
        allCoords.addAll(horizontal);
        allCoords.addAll(vertical);
        allCoords.addAll(mainDiagonal);
        allCoords.addAll(antiDiagonal);
        removeBalls(board, allCoords);

        int addToScore = 0;
        addToScore += getAddition(horizontal.size());
        addToScore += getAddition(vertical.size());
        addToScore += getAddition(mainDiagonal.size());
        addToScore += getAddition(antiDiagonal.size());
        return addToScore;
    }

    public static int countEmptyCells(Board board) {
        int c = 0;
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board.isEmpty(i, j))
                    c++;
        return c;
    }

    public static boolean isInBoard(Coordinates coord) {
        return coord.i > 0 && coord.i < SIZE && coord.j > 0 && coord.j < SIZE;
    }

    private static List<Coordinates> findLongest(Board board, Coordinates transition) {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (!board.isEmpty(i, j)) {
                    Color color = board.getColor(i, j);
                    List<Coordinates> coords = new ArrayList<>();
                    int k = i, l = j;
                    do {
                        coords.add(new Coordinates(k, l));
                        k += transition.i;
                        l += transition.j;
                    } while (isInBoard(new Coordinates(k, l)) &&
                            !board.isEmpty(k, l) && board.getColor(k, l) == color);
                    if (coords.size() >= 5)
                        return coords;
                }
        return Collections.emptyList();
    }

    private static void removeBalls(Board board, List<Coordinates> coords) {
        for (Coordinates coord: coords)
            if (!board.isEmpty(coord.i, coord.j))
                board.clearCell(coord.i, coord.j);
    }

    private static int getAddition(int sequent) {
        switch (sequent) {
            case 0 : return Score.ZERO.getAddition();
            case 5 : return Score.FIVE.getAddition();
            case 6 : return Score.SIX.getAddition();
            case 7 : return Score.SEVEN.getAddition();
            case 8 : return Score.EIGHT.getAddition();
            case 9 : return Score.NINE.getAddition();
            default: throw new AssertionError("cannot reach there");
        }
    }
}
