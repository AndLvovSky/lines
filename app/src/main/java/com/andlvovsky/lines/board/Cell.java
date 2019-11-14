package com.andlvovsky.lines.board;


public class Cell {
    private boolean empty;
    private int ballColor;

    public Cell() {
        empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public int getBallColor() {
        return ballColor;
    }

    public void setBallColor(int ballColor) {
        this.ballColor = ballColor;
    }
}
