package com.katas.minesweeper;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author Chloé Mahalin
 */
public class MatrixMinesweeperAcceptanceTest {

    /**=====================================TESTS====================================**/

    @Test
    public void test_no_mine() {
        String expected =
                "000\n" +
                "000\n" +
                "000";

        checkResult(expected, AreaFactory.createNewArea("square",
                "...",
                "...",
                "..."
        ));
    }

    @Test
    public void test_one_mine() {
        String expected =
                "*10\n" +
                "110\n" +
                "000";

        checkResult(expected, AreaFactory.createNewArea("square",
                "*..",
                "...",
                "..."
        ));
    }


    @Test
    public void test_two_mines() {
        String expected =
                "*2*\n" +
                "121\n" +
                "000";

        checkResult(expected, AreaFactory.createNewArea("square",
                "*.*",
                "...",
                "..."
        ));
    }

    @Test
    public void test_three_mines() {
        String expected =
                "*3*\n" +
                "2*2\n" +
                "111";

        checkResult(expected, AreaFactory.createNewArea("square",
                "*.*",
                ".*.",
                "..."
        ));
    }

    /**=====================================METHODES====================================**/

    public void checkResult(String expected, IArea board) {
        Minesweeper game = new Minesweeper();
        String result = game.process(board);
        Assert.assertEquals(expected, result);
    }
}
