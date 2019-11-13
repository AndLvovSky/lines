package com.andlvovsky.lines.analyzer;

import com.andlvovsky.lines.board.Board;
import com.andlvovsky.lines.game.Move;

import static com.andlvovsky.lines.meta.GameConstants.SIZE;

public class BoardAnalyzer {
    private BoardAnalyzer() {
    }

    public static boolean isValidMove(Board board, Move move) {
        return board.isEmpty(move.iFrom(), move.jFrom()) &&
                board.isEmpty(move.iTo(), move.jTo()) &&
                BoardGraphAlgorithms.doesPathExist(board, move);
    }

    public static int removeBallsAndCalculateAddingScore() {
        throw new UnsupportedOperationException();
    }

    public static int countEmptyCells(Board board) {
        int c = 0;
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board.isEmpty(i, j))
                    c++;
        return c;
    }
}
