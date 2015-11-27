package com.katas.gameoflife;

import java.util.Arrays;

/**
 * Created by Chloé Mahalin
 */
public class Universe {

    CellState[][] universe;
    private int xSize;
    private int ySize;

    private Universe(int xSize, int ySize) {
        this.universe = new CellState[xSize][ySize];
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public int getXSize() {
        return this.xSize;
    }

    public int getYSize() {
        return this.ySize;
    }

    public void setCellState(int xCoordinate, int yCoordinate, CellState newState) {
        this.universe[xCoordinate][yCoordinate] = newState;
    }

    public boolean isCellAlive(int xCoordinate, int yCoordinate) {
        if (xCoordinate >= 0 && yCoordinate >= 0 && xCoordinate < this.getXSize() && yCoordinate < this.getYSize()) {
            return this.universe[xCoordinate][yCoordinate] == CellState.ALIVE;
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();

        for (int x = 0; x < this.getXSize(); x++) {
            for (int y = 0; y < this.getYSize(); y++) {
                toReturn.append(this.universe[x][y].getSymbol());
            }
            if(x < this.getXSize() -1) {
                toReturn.append("\n");
            }
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
    
    public static Universe createNewUniverse(int xSize, int ySize) {
        return new Universe(xSize,ySize);
    }
}
