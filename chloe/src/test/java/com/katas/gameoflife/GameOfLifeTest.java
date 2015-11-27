package com.katas.gameoflife;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author : Chloé Mahalin
 */
public class GameOfLifeTest {


    @Test
    public void test_cell_count_no_living_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(0, lifeGame.countNeighbours(0,0,Universe.createUniverse(
                "*..",
                "...",
                "...")));
    }

    @Test
     public void test_cell_count_two_living_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(2, lifeGame.countNeighbours(0,0,Universe.createUniverse(
                "**.",
                ".*.",
                "...")));
    }

    @Test
    public void test_cell_count_three_living_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(3, lifeGame.countNeighbours(1,1,Universe.createUniverse(
                ".*.",
                ".**",
                "*..")));
    }

    @Test
    public void test_cell_count_three_living_neighbours_edge() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(3, lifeGame.countNeighbours(2,2,Universe.createUniverse(
                ".*.",
                ".**",
                "***")));
    }


    @Test
    public void test_cell_is_alive_two_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(CellState.ALIVE, lifeGame.calculateCellState(CellState.ALIVE, 2));
    }

    @Test
    public void test_cell_is_alive_three_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(CellState.ALIVE, lifeGame.calculateCellState(CellState.ALIVE, 3));
    }

    @Test
    public void test_cell_is_alive_one_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.ALIVE, 1));
    }

    @Test
    public void test_cell_is_alive_more_than_three_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.ALIVE, 4));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.ALIVE, 5));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.ALIVE, 6));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.ALIVE, 7));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.ALIVE, 8));
    }

    @Test
    public void test_cell_is_dead_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.DEAD, 0));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.DEAD, 1));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.DEAD, 2));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.DEAD, 4));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.DEAD, 5));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.DEAD, 6));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.DEAD, 7));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(CellState.DEAD, 8));
    }

    @Test
    public void test_cell_is_dead_three_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(CellState.ALIVE, lifeGame.calculateCellState(CellState.DEAD, 3));
    }

}
