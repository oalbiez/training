package com.katas.minesweeper;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chloé Mahalin
 */
public class MinesweeperTest {

     @Test
    public void test_detect_Mine_no_mine() {
        checkMineDetection(false, Area.generate("...", "...", "..."), 0, 0);
    }

    @Test
    public void test_detect_mine_with_mine() {
        checkMineDetection(true, Area.generate("*..", "...", "..."), 0, 0);
    }

    @Test
    public void test_detect_mine_with_mine_center() {
        checkMineDetection(true, Area.generate("*..", ".*.", "..."), 1, 1);
    }

    @Test
    public void test_detect_mine_with_mine_somewhere_else() {
        checkMineDetection(true, Area.generate("*..", "...", ".*."), 2, 1);
    }

    public void checkMineDetection(boolean expected, Area board, int xCoordinate, int yCoordinate) {
        Assert.assertEquals(expected, board.isAMine(xCoordinate, yCoordinate));
    }

    @Test
    public void test_count_mines_around_coordinates_with_no_mines() {
        CheckMineCountAroundCoordinates(0, Area.generate("*..", "...", ".*."), 2, 1);
    }

    @Test
    public void test_count_mines_around_coordinates_with_two_mines() {
        CheckMineCountAroundCoordinates(2, Area.generate("*..", "...", ".*."), 1, 1);
    }

    public void CheckMineCountAroundCoordinates(int expected, Area board, int xCoordinate, int yCoordinate) {
        Minesweeper game = new Minesweeper();
        Assert.assertEquals(expected, game.countMineAroundCoordinate(xCoordinate, yCoordinate, board));
    }
}
