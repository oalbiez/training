package com.sgcib.katas.gameoflife;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cmahalin111813 on 24/11/2015.
 */
public class GameOfLifeTest {


    @Test
    public void test_cell_count_no_living_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(0, lifeGame.countNeighbours(0,0,Universe.createNewUniverse(
                "*..",
                "...",
                "...")));
    }

    @Test
     public void test_cell_count_two_living_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(2, lifeGame.countNeighbours(0,0,Universe.createNewUniverse(
                "**.",
                ".*.",
                "...")));
    }

    @Test
    public void test_cell_count_three_living_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(3, lifeGame.countNeighbours(1,1,Universe.createNewUniverse(
                ".*.",
                ".**",
                "*..")));
    }

    @Test
    public void test_cell_count_three_living_neighbours_edge() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(3, lifeGame.countNeighbours(2,2,Universe.createNewUniverse(
                ".*.",
                ".**",
                "***")));
    }

    @Test
    public void test_cell_is_alive_two_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(CellState.ALIVE, lifeGame.calculateCellState(true, 2));
    }

    @Test
    public void test_cell_is_alive_three_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(CellState.ALIVE, lifeGame.calculateCellState(true, 3));
    }

    @Test
    public void test_cell_is_alive_one_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(true, 1));
    }

    @Test
    public void test_cell_is_alive_more_than_three_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(true, 4));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(true, 5));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(true, 6));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(true, 7));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(true, 8));
    }

    @Test
    public void test_cell_is_dead_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(false, 0));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(false, 1));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(false, 2));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(false, 4));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(false, 5));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(false, 6));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(false, 7));
        Assert.assertEquals(CellState.DEAD, lifeGame.calculateCellState(false, 8));
    }

    @Test
    public void test_cell_is_dead_three_neighbours() {
        GameOfLife lifeGame = new GameOfLife();
        Assert.assertEquals(CellState.ALIVE, lifeGame.calculateCellState(false, 3));
    }

}
