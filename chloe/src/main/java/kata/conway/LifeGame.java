package kata.conway;

/**
 * Created by cmahalin111813 on 24/11/2015.
 */
public class LifeGame {


    public boolean[][] process(int numberOfSteps, boolean[][] universe) {
        boolean[][] toReturn = copyMatrix(universe);

        if (universe != null && universe.length > 0 && universe[0].length > 0) {
            for (int step = 0; step < numberOfSteps; step++) {
                for (int x = 0; x < universe.length; x++) {
                    for (int y = 0; y < universe[x].length; y++) {
                        toReturn[x][y] = isCellAlive(universe[x][y], countNeighbours(x,y,universe));
                    }
                }
                universe = copyMatrix(toReturn);
            }
        }

        return toReturn;
    }

    public int countNeighbours(int xCoordinate, int yCoordinate, boolean[][] universe) {
        int counter = 0;
        for (int i = xCoordinate - 1; i <= xCoordinate + 1; i++) {
            for (int j = yCoordinate - 1; j <= yCoordinate + 1; j++) {
                if (isInBound(i, j, universe)) {
                    if (!isCurrentCell(xCoordinate, yCoordinate, i, j) && isCellAlive(i, j, universe)) {
                        counter++;
                    }
                }
            }
        }

        return counter;
    }

    private boolean isCellAlive(int xCoordinate, int yCoordinate, boolean[][] universe) {
        return universe[xCoordinate][yCoordinate];
    }

    private boolean isInBound(int xCoordinate, int yCoordinate, boolean[][] universe) {
        return xCoordinate >= 0 && yCoordinate >= 0 && xCoordinate < universe.length && yCoordinate < universe[xCoordinate].length;
    }

    private boolean isCurrentCell(int xCoordinate, int yCoordinate, int currentXCoordinate, int currentYCoordinate) {
        return xCoordinate == currentXCoordinate && yCoordinate == currentYCoordinate;
    }

    public boolean isCellAlive(boolean cellIsAlive, int numberOfNeighbours) {
        return doesCellIsAliveWithTwoOrThreeNeighbours(cellIsAlive, numberOfNeighbours)
                || doesCellIsDeadWithThreeNeighbours(cellIsAlive, numberOfNeighbours);
    }

    public boolean doesCellIsAliveWithTwoOrThreeNeighbours(boolean cellIsAlive, int numberOfNeighbours) {
        return cellIsAlive && (numberOfNeighbours == 2 || numberOfNeighbours == 3);
    }

    public boolean doesCellIsDeadWithThreeNeighbours(boolean cellIsAlive, int numberOfNeighbours) {
        return !cellIsAlive && numberOfNeighbours == 3;
    }

    private boolean[][] copyMatrix(boolean[][] input) {
        if (input == null)
            return null;
        boolean[][] result = new boolean[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }
}
