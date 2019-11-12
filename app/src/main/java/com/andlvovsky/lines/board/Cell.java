package com.andlvovsky.lines.board;

import com.andlvovsky.lines.meta.Color;


public class Cell {
    private boolean empty;
    private Color ballColor;

    public Cell() {
        empty = true;
        ballColor = Color.NONE;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public Color getBallColor() {
        return ballColor;
    }

    public void setBallColor(Color ballColor) {
        this.ballColor = ballColor;
    }
}
