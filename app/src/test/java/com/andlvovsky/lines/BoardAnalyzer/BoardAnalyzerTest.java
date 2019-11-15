package com.andlvovsky.lines.BoardAnalyzer;

import com.andlvovsky.lines.analyzer.BoardAnalyzer;
import com.andlvovsky.lines.board.Board;
import com.andlvovsky.lines.game.Move;
import com.andlvovsky.lines.meta.Colors;
import com.andlvovsky.lines.testutil.BoardParser;

import org.junit.Test;

import static com.andlvovsky.lines.analyzer.BoardAnalyzer.isValidMove;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardAnalyzerTest {
    private String nonColorBoardRepresentation =
            "o...oooo." +
            "...o.oo.." +
            "..o...o.." +
            "oo.....o." +
            "...o...o." +
            "..o..o..." +
            "ooo..o..." +
            "..o...o.." +
            "o.o..o...";
    private Board nonColorBoard = BoardParser.fromString(nonColorBoardRepresentation);

    @Test
    public void recognizeValidMoves() {
        assertTrue(isValidMove(nonColorBoard, new Move(0, 0, 0, 1)));
        assertTrue(isValidMove(nonColorBoard, new Move(0, 0, 2, 0)));
        assertTrue(isValidMove(nonColorBoard, new Move(6, 1, 8, 8)));
        assertTrue(isValidMove(nonColorBoard, new Move(8, 5, 0, 8)));
        assertTrue(isValidMove(nonColorBoard, new Move(4, 3, 3, 5)));
    }

    @Test
    public void recognizeInvalidMovesWhenNoPath() {
        assertFalse(isValidMove(nonColorBoard, new Move(0, 0, 3, 4)));
        assertFalse(isValidMove(nonColorBoard, new Move(8, 0, 4, 5)));
        assertFalse(isValidMove(nonColorBoard, new Move(0, 0, 7, 0)));
    }

    @Test
    public void recognizeInvalidMovesOtherCases() {
        assertFalse(isValidMove(nonColorBoard, new Move(0, 0, 0, 4)));
        assertFalse(isValidMove(nonColorBoard, new Move(0, 1, 0, 4)));
        assertFalse(isValidMove(nonColorBoard, new Move(0, 1, 1, 1)));
        assertFalse(isValidMove(nonColorBoard, new Move(0, 1, -5, 1)));
        assertFalse(isValidMove(nonColorBoard, new Move(10, 1, 5, 1)));
    }
    
    @Test
    public void removeBallsHorizontal() {
        Board board = new Board();
        for (int i = 0; i < 6; i++)
            board.addBall(i + 1, 2, Colors.BALLS.get(0));
        BoardAnalyzer.removeBallsAndCalculateAddingScore(board);
        assertEquals(81, BoardAnalyzer.countEmptyCells(board));
    }

    @Test
    public void removeBallsVertical() {
        Board board = new Board();
        for (int i = 0; i < 5; i++)
            board.addBall(1, i + 2, Colors.BALLS.get(0));
        BoardAnalyzer.removeBallsAndCalculateAddingScore(board);
        assertEquals(81, BoardAnalyzer.countEmptyCells(board));
    }

    @Test
    public void removeBallsMainDiagonal() {
        Board board = new Board();
        for (int i = 0; i < 6; i++)
            board.addBall(i + 1, i + 2, Colors.BALLS.get(0));
        BoardAnalyzer.removeBallsAndCalculateAddingScore(board);
        assertEquals(81, BoardAnalyzer.countEmptyCells(board));
    }

    @Test
    public void removeBallsAntiDiagonal() {
        Board board = new Board();
        for (int i = 0; i < 5; i++)
            board.addBall(i + 1, 7 - i, Colors.BALLS.get(0));
        BoardAnalyzer.removeBallsAndCalculateAddingScore(board);
        assertEquals(81, BoardAnalyzer.countEmptyCells(board));
    }
    
    @Test
    public void notRemoveBallsSmallSequence() {
        Board board = new Board();
        for (int i = 0; i < 4; i++)
            board.addBall(i + 2, 2, Colors.BALLS.get(0));
        BoardAnalyzer.removeBallsAndCalculateAddingScore(board);
        assertEquals(77, BoardAnalyzer.countEmptyCells(board));
    }

    @Test
    public void notRemoveBallsNotSequence() {
        Board board = new Board();
        board.addBall(0, 0, Colors.BALLS.get(0));
        board.addBall(4, 5, Colors.BALLS.get(0));
        board.addBall(8, 3, Colors.BALLS.get(0));
        board.addBall(2, 2, Colors.BALLS.get(0));
        board.addBall(7, 1, Colors.BALLS.get(0));
        BoardAnalyzer.removeBallsAndCalculateAddingScore(board);
        assertEquals(76, BoardAnalyzer.countEmptyCells(board));
    }
}