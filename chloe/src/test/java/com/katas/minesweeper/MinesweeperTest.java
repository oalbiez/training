package com.katas.minesweeper;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Chloé Mahalin
 */
public class MinesweeperTest {

    @Test
    public void test_detect_Mine_no_mine() {
        Area board = Area.generate(
                "...",
                "...",
                "...");
        Assert.assertEquals(false, board.isAMine(0, 0));
    }

    @Test
     public void test_detect_mine_with_mine() {
        Area board = Area.generate(
                "*..",
                "...",
                "...");
        Assert.assertEquals(true, board.isAMine(0, 0));
    }

    @Test
    public void test_detect_mine_with_mine_center() {
        Area board = Area.generate(
                "*..",
                ".*.",
                "...");
        Assert.assertEquals(true, board.isAMine(1, 1));
    }

    @Test
    public void test_detect_mine_with_mine_somewhere_else() {
        Area board = Area.generate(
                "*..",
                "...",
                ".*.");
        Assert.assertEquals(true, board.isAMine(2, 1));
    }

    @Test
    public void test_count_mines_around_coordinates_with_no_mines() {
        Minesweeper game = new Minesweeper();
        Area board = Area.generate(
                "*..",
                "...",
                ".*.");
        Assert.assertEquals(0, game.countMine(2, 1, board));
    }

    @Test
    public void test_count_mines_around_coordinates_with_two_mines() {
        Minesweeper game = new Minesweeper();
        Area board = Area.generate(
                "*..",
                "...",
                ".*.");
        Assert.assertEquals(2, game.countMine(1, 1, board));
    }


    public static void checkResult(Area expected, Area result) {
        Assert.assertEquals(expected, result);
    }

    public static void checkResult(String expected, String result) {
        Assert.assertEquals(expected, result);
    }
}
