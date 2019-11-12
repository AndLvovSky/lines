package com.andlvovsky.lines.board;

import com.andlvovsky.lines.meta.Color;

public class Board {
    private final static int SIZE = 9;

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
        cell.setBallColor(Color.NONE);
    }

    public Color getColor(int i, int j) {
        return cells[i][j].getBallColor();
    }

    public void addBall(int i, int j, Color color) {
        Cell cell = cells[i][j];
        cell.setEmpty(false);
        cell.setBallColor(color);
    }
}
