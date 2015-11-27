package com.katas.minesweeper;

import org.junit.Test;

import static com.sgcib.katas.minesweeper.MinesweeperTest.checkResult;


/**
 * @author Chloé Mahalin
 */
public class MinesweeperAcceptanceTest {

    @Test
    public void test_no_mine() {
        String expected =
                "000\n" +
                "000\n" +
                "000";

        checkResult(expected,Area.generate(
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

        checkResult(expected,Area.generate(
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

        checkResult(expected,Area.generate(
                "*.*",
                "...",
                "..."
        ));
    }

    public void checkResult(String expected, Area board) {
        Minesweeper game = new Minesweeper();
        String result = game.process(board);
        Assert.assertEquals(expected, result);
    }
}
