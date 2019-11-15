package com.andlvovsky.lines.controller;

import com.andlvovsky.lines.board.Board;
import com.andlvovsky.lines.game.LinesGame;
import com.andlvovsky.lines.meta.Colors;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class LinesGameControllerTest {
    private LinesGameController controller = LinesGameController.INSTANCE;
    private LinesGame game = LinesGame.INSTANCE;

    @Before
    public void setBalls() throws IllegalAccessException, NoSuchFieldException {
        controller.startGame();
        Field boardField = LinesGame.class.getDeclaredField("board");
        boardField.setAccessible(true);
        Board board = (Board)boardField.get(game);
        for (int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++)
                board.clearCell(i, j);
        board.addBall(1, 1, Colors.BALLS.get(0));
        board.addBall(5, 7, Colors.BALLS.get(0));
    }

    @Test
    public void returnZeroAsNumberOfChosenCellsAfterStart() {
        assertEquals(0, controller.getChosenCells().size());
    }

    @Test
    public void returnOneAsNumberOfChosenCellsAfterChoosingCell() {
        controller.chooseCell(1, 1);
        assertEquals(1, controller.getChosenCells().size());
    }

    @Test
    public void returnZeroAsNumberOfChosenCellsAfterChoosingTwoCell() {
        controller.chooseCell(1, 1);
        controller.chooseCell(4, 2);
        assertEquals(0, controller.getChosenCells().size());
    }
}
