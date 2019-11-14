package com.andlvovsky.lines.analyzer;

import com.andlvovsky.lines.board.Board;
import com.andlvovsky.lines.game.Move;
import com.andlvovsky.lines.util.Coordinates;

import java.util.ArrayList;
import java.util.List;

import static com.andlvovsky.lines.meta.GameConstants.SIZE;

public class BoardGraphAlgorithms {
    private static Coordinates[] validSteps = {
            new Coordinates(0, -1),
            new Coordinates(1, 0),
            new Coordinates(0, 1),
            new Coordinates(-1, 0)
    };

    private BoardGraphAlgorithms() {
    }

    public static boolean doesPathExist(Board board, Move move) {
        return bfs(board, new Coordinates(move.iFrom(), move.jFrom()),
                new Coordinates(move.iTo(), move.jTo()));
    }

    // checks if cells are in the same component of connectivity, using breadth-first search
    private static boolean bfs(Board board, Coordinates from, Coordinates to) {
        boolean[][] used = new boolean[SIZE][SIZE];
        used[from.i][from.j] = true;
        List<Coordinates> lastVisited = new ArrayList<>();
        lastVisited.add(from);
        List<Coordinates> nextVisited = new ArrayList<>();
        while (!lastVisited.isEmpty()) {
            for (Coordinates coord: lastVisited) {
                if (coord.equals(to))
                    return true;
                nextVisited.addAll(findPossibleNextCells(board, coord, used));
            }
            lastVisited.clear();
            lastVisited.addAll(nextVisited);
            nextVisited.clear();
        }
        return false;
    }

    private static List<Coordinates> findPossibleNextCells(Board board, Coordinates coord, boolean[][] used) {
        List<Coordinates> nextCoords = new ArrayList<>();
        for (Coordinates step: validSteps) {
            Coordinates next = new Coordinates(coord.i + step.i, coord.j + step.j);
            if (isValidCell(board, next, used)) {
                nextCoords.add(next);
                used[next.i][next.j] = true;
            }
        }
        return nextCoords;
    }

    private static boolean isValidCell(Board board, Coordinates coord, boolean[][] used) {
        return BoardAnalyzer.isInBoard(coord) &&
                board.isEmpty(coord.i, coord.j) &&
                !used[coord.i][coord.j];
    }
}
