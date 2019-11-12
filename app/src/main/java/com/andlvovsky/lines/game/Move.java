package com.andlvovsky.lines.game;

public class Move {
    private final int iFrom;
    private final int jFrom;
    private final int iTo;
    private final int jTo;

    public Move(int iFrom, int jFrom, int iTo, int jTo) {
        this.iFrom = iFrom;
        this.jFrom = jFrom;
        this.iTo = iTo;
        this.jTo = jTo;
    }

    public int iFrom() {
        return iFrom;
    }

    public int jFrom() {
        return jFrom;
    }

    public int iTo() {
        return iTo;
    }

    public int jTo() {
        return jTo;
    }
}
