package com.andlvovsky.lines.meta;

public enum Score {
    ZERO(0),
    FIVE(10),
    SIX(25),
    SEVEN(100),
    EIGHT(500),
    NINE(2500);

    private final int addition;

    Score(int addition) {
        this.addition = addition;
    }

    public int getAddition() {
        return addition;
    }
}
