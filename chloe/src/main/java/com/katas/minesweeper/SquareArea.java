package com.katas.minesweeper;

import com.sgcib.katas.minesweeper.MineState;
import com.sgcib.katas.minesweeper.squares.Square;

/**
 * @author Chloé Mahalin
 */
public class SquareArea implements IArea {

    protected final Square[][] board;
    protected final int xSize;
    protected final int ySize;

    public SquareArea(String... rows) {
        this.xSize = rows.length;
        this.ySize = rows[0].length();
        this.board = new Square[xSize][ySize];
        if (rows.length > 0 && !rows[0].isEmpty()) {
            for (int x = 0; x < this.xSize; x++) {
                for (int y = 0; y < this.ySize; y++) {
                    this.board[x][y] = new Square();
                    this.board[x][y].setState(MineState.getStateFromSymbol(rows[x].charAt(y)));
                }
            }
        }
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }


    public boolean isAMine(int xCoordinate, int yCoordinate) {
        if (isBetween(xCoordinate, this.xSize) && this.isBetween(yCoordinate, this.ySize)) {
            return this.board[xCoordinate][yCoordinate].getState() == MineState.MINE;
        }
        return false;
    }

    private boolean isBetween(int value, int max) {
        return 0 <= value && value < max;
    }


}
