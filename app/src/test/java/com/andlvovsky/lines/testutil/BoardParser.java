package com.andlvovsky.lines.testutil;

import com.andlvovsky.lines.board.Board;
import com.andlvovsky.lines.meta.Colors;

public class BoardParser {
    public static Board fromString(String bs) {
        Board board = new Board();
        for (int i = 0; i < bs.length(); i++)
            if (bs.charAt(i) == 'o')
                board.addBall(i / 9, i % 9, Colors.BALLS.get(0));
        return board;
    }
}
