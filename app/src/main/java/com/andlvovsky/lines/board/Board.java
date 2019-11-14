package com.andlvovsky.lines.board;

import com.andlvovsky.lines.game.Move;

import static com.andlvovsky.lines.meta.GameConstants.SIZE;

public class Board implements Cloneable {
    private Cell[][] cells;

    public Board() {
        cells = new Cell[SIZE][SIZE];
        for (int i  = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                cells[i][j] = new Cell();
    }

    public boolean isEmpty(int i, int j) {
        return cells[i][j].isEmpty();
    }

    public void clearCell(int i, int j) {
        Cell cell = cells[i][j];
        cell.setEmpty(true);
    }

    public int getColor(int i, int j) {
        return cells[i][j].getBallColor();
    }

    public void addBall(int i, int j, int color) {
        Cell cell = cells[i][j];
        cell.setEmpty(false);
        cell.setBallColor(color);
    }

    public void moveBall(Move move) {
        addBall(move.iTo(), move.jTo(), getColor(move.iFrom(), move.jFrom()));
        clearCell(move.iFrom(), move.jFrom());
    }

    @Override
    public Object clone() {
        Board board = new Board();
        for (int i  = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (!isEmpty(i, j))
                    board.addBall(i, j, getColor(i, j));
        return board;
    }
}
