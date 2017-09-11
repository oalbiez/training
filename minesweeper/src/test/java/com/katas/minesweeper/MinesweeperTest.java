package com.katas.minesweeper;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chloé Mahalin
 */
public class MinesweeperTest {

    /**=====================================TESTS====================================**/


    @Test
    public void test_detect_Mine_no_mine() {
        checkMineDetection(false, AreaFactory.createNewArea("square", "...", "...", "..."),0,0);
    }

    //@Test
    public void test_detect_mine_with_mine() {
        checkMineDetection(true, AreaFactory.createNewArea("square", "*..", "...", "..."), 0, 0);
    }

    //@Test
    public void test_detect_mine_with_mine_center() {
        checkMineDetection(true, AreaFactory.createNewArea("square", "*..", ".*.", "..."), 1, 1);
    }

    //@Test
    public void test_detect_mine_with_mine_somewhere_else() {
        checkMineDetection(true, AreaFactory.createNewArea("square", "*..", "...", ".*."), 2, 1);
    }

    //@Test
    public void test_count_mines_around_coordinates_with_no_mines() {
        checkMineCountAroundCoordinates(0, AreaFactory.createNewArea("square", "*..", "...", ".*."), 2, 1);
    }

    //@Test
    public void test_count_mines_around_coordinates_with_two_mines() {
        checkMineCountAroundCoordinates(2, AreaFactory.createNewArea("square", "*..", "...", ".*."), 1, 1);
    }

    /**=====================================METHODES=====================================**/

    public void checkMineDetection(boolean expected, IArea board, int xCoordinate, int yCoordinate) {
        Assert.assertEquals(expected, board.isAMine(xCoordinate, yCoordinate));
    }

    public void checkMineCountAroundCoordinates(int expected, IArea board, int xCoordinate, int yCoordinate) {
        Minesweeper game = new Minesweeper();
        Assert.assertEquals(expected, game.countMineAroundCoordinate(xCoordinate, yCoordinate, board));
    }

}
