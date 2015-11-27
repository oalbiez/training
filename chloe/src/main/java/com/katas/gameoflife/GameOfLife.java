package com.katas.gameoflife;

/**
 * Created by cmahalin111813 on 24/11/2015.
 */
public class GameOfLife {


    public Universe live(int numberOfSteps, Universe universe) {
        if (universe != null && universe.getXSize() > 0 && universe.getYSize() > 0) {
            for (int step = 0; step < numberOfSteps; step++) {
                universe = evolve(universe);
            }
        }

        return universe;
    }

    public Universe evolve(Universe universe) {
        Universe toReturn = Universe.createNewUniverse(universe.getXSize(), universe.getYSize());
        for (int x = 0; x < universe.getXSize(); x++) {
            for (int y = 0; y < universe.getYSize(); y++) {
                toReturn.setCellState(x, y, calculateCellState(universe.isCellAlive(x, y), countNeighbours(x, y, universe)));
            }
        }
        return toReturn;
    }

    public int countNeighbours(int xCoordinate, int yCoordinate, Universe universe) {
        int counter = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if ((dx != 0 || dy != 0) && universe.isCellAlive(xCoordinate + dx, yCoordinate + dy)) {
                    counter++;
                }
            }
        }
        return counter;
    }


    public CellState calculateCellState(boolean cellIsAlive, int numberOfNeighbours) {
        if (doesCellIsAliveWithTwoOrThreeNeighbours(cellIsAlive, numberOfNeighbours)
                || doesCellIsDeadWithThreeNeighbours(cellIsAlive, numberOfNeighbours)) {
            return CellState.ALIVE;
        }
        return CellState.DEAD;
    }

    public boolean doesCellIsAliveWithTwoOrThreeNeighbours(boolean cellIsAlive, int numberOfNeighbours) {
        return cellIsAlive && (numberOfNeighbours == 2 || numberOfNeighbours == 3);
    }

    public boolean doesCellIsDeadWithThreeNeighbours(boolean cellIsAlive, int numberOfNeighbours) {
        return !cellIsAlive && numberOfNeighbours == 3;
    }

}
