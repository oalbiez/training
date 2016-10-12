package com.katas.minesweeper;

/**
 * @author Chloé Mahalin
 */
public class Minesweeper {

   public String process(IArea board) {
        StringBuilder result = new StringBuilder();
        for (int x = 0; x < board.getXSize(); x++) {
            for (int y = 0; y < board.getYSize(); y++) {
                if (board.isAMine(x, y)) {
                    result.append(MineState.MINE.getSymbol());
                } else {
                    result.append(this.countMineAroundCoordinate(x, y, board));
                }
            }
            if (x != board.getXSize() - 1) {
                result.append('\n');
            }
        }

        return result.toString();
    }


    public int countMineAroundCoordinate(int xCoordinate, int yCoordinate, IArea board) {
        int counter = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (!isCurrentCell(dx, dy) && board.isAMine(xCoordinate + dx, yCoordinate + dy)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private boolean isCurrentCell(int dx, int dy) {
        return dx == 0 && dy == 0;
    }
}
