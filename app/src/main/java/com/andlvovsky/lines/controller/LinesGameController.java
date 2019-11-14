package com.andlvovsky.lines.controller;

import com.andlvovsky.lines.game.LinesGame;
import com.andlvovsky.lines.game.Move;
import com.andlvovsky.lines.util.Coordinates;

import java.util.ArrayList;
import java.util.List;

public enum LinesGameController {
    INSTANCE;

    private LinesGame game = LinesGame.INSTANCE;

    private List<Coordinates> chosenCells = new ArrayList<>();

    public void startGame() {
        game.start();
    }

    public void chooseCell(int i, int j) {
        chosenCells.add(new Coordinates(i, j));
        if (chosenCells.size() == 2) {
            game.makeMove(new Move(chosenCells.get(0).i, chosenCells.get(0).j,
                    chosenCells.get(1).i, chosenCells.get(1).j));
            chosenCells.clear();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Coordinates> getChosenCells() {
        return (List<Coordinates>)((ArrayList<?>) chosenCells).clone();
    }
}
