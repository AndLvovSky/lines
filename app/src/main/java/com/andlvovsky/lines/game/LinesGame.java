package com.andlvovsky.lines.game;

import com.andlvovsky.lines.analyzer.BoardAnalyzer;
import com.andlvovsky.lines.board.Board;
import com.andlvovsky.lines.exception.GameOverException;
import com.andlvovsky.lines.exception.InvalidMoveException;
import com.andlvovsky.lines.generator.BallsGenerator;

public enum LinesGame {
    INSTANCE;

    private Board board;
    private int score;
    private int[] nextColors;

    public void start() {
        board = new Board();
        score = 0;
        nextColors = BallsGenerator.generateNextColors();
        BallsGenerator.placeFirstBalls(board);
        score += BoardAnalyzer.removeBallsAndCalculateAddingScore(board);
    }

    public int getNextColor(int pos) {
        return nextColors[pos];
    }

    public int getScore() {
        return score;
    }

    public Board getBoard() {
        return (Board)board.clone();
    }

    public void makeMove(Move move) {
        if (!BoardAnalyzer.isValidMove(board, move))
            throw new InvalidMoveException();
        board.moveBall(move);
        score += BoardAnalyzer.removeBallsAndCalculateAddingScore(board);
        BallsGenerator.placeNextBalls(board, nextColors);
        score += BoardAnalyzer.removeBallsAndCalculateAddingScore(board);
        if (BoardAnalyzer.countEmptyCells(board) == 0)
            throw new GameOverException();
        nextColors = BallsGenerator.generateNextColors();
    }
}
