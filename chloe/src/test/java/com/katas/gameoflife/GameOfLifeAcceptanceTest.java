package com.katas.gameoflife;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cmahalin111813 on 24/11/2015.
 */
public class GameOfLifeAcceptanceTest {

    @Test
    public void test_init_case() {
        GameOfLife instanceToTest = new GameOfLife();
        Universe result = instanceToTest.live(0, Universe.createNewUniverse(
                "...",
                "...",
                "..."));

        Universe expected = Universe.createNewUniverse(
                "...",
                "...",
                "..."
        );

        checkResult(expected, result);
    }

    @Test
    public void test_cell_with_no_neighbour() {
        GameOfLife instanceToTest = new GameOfLife();
        Universe result = instanceToTest.live(1, Universe.createNewUniverse(
                "*..",
                "...",
                "..."));

        Universe expected = Universe.createNewUniverse(
                "...",
                "...",
                "..."
        );

        checkResult(expected, result);
    }

    @Test
    public void test_cell_with_three_neighbour() {
        GameOfLife instanceToTest = new GameOfLife();
        Universe result = instanceToTest.live(1, Universe.createNewUniverse(
                "..*",
                "..*",
                "*.."));

        Universe expected = Universe.createNewUniverse(
                "...",
                ".*.",
                "..."
        );

        checkResult(expected, result);
    }

    public static void checkResult(Universe expected, Universe result) {
        Assert.assertEquals(expected, result);
    }


}
