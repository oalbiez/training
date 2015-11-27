package com.katas.gameoflife;

import java.util.Arrays;

/**
 * Created by cmahalin111813 on 26/11/2015.
 */
public class Universe {

    CellState[][] universe;

    public Universe(int xSize, int ySize) {
        this.universe = new CellState[xSize][ySize];
    }

    public int getXSize() {
        return universe.length;
    }

    public int getYSize() {
        if (universe.length > 0) {
            return universe[0].length;
        }
        return 0;
    }

    public void changeCellState(int xCoordinate, int yCoordinate, CellState newState) {
        this.universe[xCoordinate][yCoordinate] = newState;
    }

    public boolean isCellAlive(int xCoordinate, int yCoordinate) {
        if (xCoordinate >= 0 && yCoordinate >= 0 && xCoordinate < this.getXSize() && yCoordinate < this.getYSize()) {
            return this.universe[xCoordinate][yCoordinate] == CellState.ALIVE;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Universe universe1 = (Universe) o;
        return Arrays.deepEquals(universe, universe1.universe);

    }

    @Override
    public int hashCode() {
        return universe != null ? Arrays.deepHashCode(universe) : 0;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();

        for (int x = 0; x < this.getXSize(); x++) {
            for (int y = 0; y < this.getYSize(); y++) {
                toReturn.append(this.universe[x][y].getSymbol());
            }
            toReturn.append("\n");
        }

        return toReturn.toString();
    }

    public static Universe createNewUniverse(String... universeLines) {
        Universe constructedUniverse = new Universe(0, 0);
        if (universeLines.length > 0 && !universeLines[0].isEmpty()) {
            constructedUniverse = new Universe(universeLines.length, universeLines[0].length());

            for (int i = 0; i < universeLines.length; i++) {
                for (int j = 0; j < universeLines[i].length(); j++) {
                    constructedUniverse.changeCellState(i, j, CellState.getStateFromSymbol(universeLines[i].charAt(j)));
                }
            }
        }
        return constructedUniverse;
    }
}
