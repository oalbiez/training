package com.katas.minesweeper;

import java.util.Arrays;

/**
 * @author Chloé Mahalin
 */
public class Area {

    private MineState[][] board;
    private int xSize;
    private int ySize;

    public Area(int x, int y) {
        board = new MineState[x][y];
        this.xSize = x;
        this.ySize = y;
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public void setValue(int xCoordinate, int yCoordinate, MineState value) {
        this.board[xCoordinate][yCoordinate] = value;
    }

    public boolean isAMine(int xCoordinate, int yCoordinate) {
        if (xCoordinate >= 0 && yCoordinate >= 0 && xCoordinate < this.getXSize() && yCoordinate < this.getYSize()) {
            return this.board[xCoordinate][yCoordinate] == MineState.MINE;
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();

        for (int x = 0; x < this.getXSize(); x++) {
            for (int y = 0; y < this.getYSize(); y++) {
                toReturn.append(this.board[x][y].getSymbol());
            }
            toReturn.append("\n");
        }

        return toReturn.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area area = (Area) o;

        return Arrays.deepEquals(board, area.board);

    }

    @Override
    public int hashCode() {
        return board != null ? Arrays.deepHashCode(board) : 0;
    }


    public static Area generate(String... rows) {
        Area constructedBoard = new Area(0, 0);
        if (rows.length > 0 && !rows[0].isEmpty()) {
            constructedBoard = new Area(rows.length, rows[0].length());

            for (int i = 0; i < rows.length; i++) {
                for (int j = 0; j < rows[i].length(); j++) {
                    constructedBoard.setValue(i, j, MineState.getStateFromSymbol(rows[i].charAt(j)));
                }
            }
        }
        return constructedBoard;
    }
}
