package com.katas.gameoflife;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author : Chloé Mahalin
 */
public class GameOfLifeAcceptanceTest {

    @Test
    public void test_init_case() {
        GameOfLife instanceToTest = new GameOfLife();
        String result = instanceToTest.live(0, Universe.createNewUniverse(
                "...",
                "...",
                "..."));

        String expected = "...\n"+
                "...\n"+
                "...";

        checkResult(expected, result);
    }

    @Test
    public void test_cell_with_no_neighbour() {
        GameOfLife instanceToTest = new GameOfLife();
        String result = instanceToTest.live(1, Universe.createNewUniverse(
                "*..",
                "...",
                "..."));

        String expected = "...\n"+
                "...\n"+
                "...";

        checkResult(expected, result);
    }

    @Test
    public void test_cell_with_three_neighbour() {
        GameOfLife instanceToTest = new GameOfLife();
        String result = instanceToTest.live(1, Universe.createNewUniverse(
                "..*",
                "..*",
                "*.."));

        String expected = "...\n"+
                ".*.\n"+
                "...";

        checkResult(expected, result);
    }

    public static void checkResult(String expected, String result) {
        Assert.assertEquals(expected, result);
    }


}
