package com.katas.gameoflife;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author : Chloé Mahalin
 */
public class GameOfLifeAcceptanceTest {
    
@Test
    public void test_init_case() {
        String expected = "...\n" +
                "...\n" +
                "...";
        
        checkResult(expected, 1, Universe.createUniverse(
                "...",
                "...",
                "..."));
    }

    @Test
    public void test_cell_with_no_neighbour() {
        String expected = "...\n" +
                "...\n" +
                "...";

        checkResult(expected, 1, Universe.createUniverse(
                "*..",
                "...",
                "..."));
    }

    @Test
    public void test_cell_with_three_neighbour() {
        String expected = "...\n" +
                ".*.\n" +
                "...";

        checkResult(expected, 1, Universe.createUniverse(
                "..*",
                "..*",
                "*.."));
    }

    public static void checkResult(String expected, int numberOfSteps, Universe universe) {
        GameOfLife instanceToTest = new GameOfLife();
        String result = instanceToTest.evolve(numberOfSteps, universe);
        Assert.assertEquals(expected, result);
    }

}
