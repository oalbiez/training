package kata.conway;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cmahalin111813 on 24/11/2015.
 */
public class LifeGameTest {

    @Test
    public void test_univers_is_not_empty() {
        LifeGame instanceToTest = new LifeGame();
        boolean[][] result = instanceToTest.process(0, new boolean[0][0]);

        boolean[][] expected = new boolean[0][0];

        checkResult(expected, result);
    }

    @Test
    public void test_cell_count_no_living_neighbours() {
        LifeGame lifeGame = new LifeGame();
        Assert.assertEquals(0, lifeGame.countNeighbours(0,0,new boolean[][]{
                {true, false, false},
                {false, false, false},
                {false, false, false}
        }));
    }

    @Test
     public void test_cell_count_two_living_neighbours() {
        LifeGame lifeGame = new LifeGame();
        Assert.assertEquals(2, lifeGame.countNeighbours(0,0,new boolean[][]{
                {true, true, false},
                {false, true, false},
                {false, false, false}
        }));
    }

    @Test
    public void test_cell_count_three_living_neighbours() {
        LifeGame lifeGame = new LifeGame();
        Assert.assertEquals(3, lifeGame.countNeighbours(1,1,new boolean[][]{
                {false, true, false},
                {false, true, true},
                {true, false, false}
        }));
    }

    @Test
    public void test_cell_count_three_living_neighbours_edge() {
        LifeGame lifeGame = new LifeGame();
        Assert.assertEquals(3, lifeGame.countNeighbours(2,2,new boolean[][]{
                {false, true, false},
                {false, true, true},
                {true, true, true}
        }));
    }

    @Test
    public void test_cell_is_alive_two_neighbours() {
        LifeGame lifeGame = new LifeGame();
        Assert.assertEquals(true, lifeGame.isCellAlive(true, 2));
    }

    @Test
    public void test_cell_is_alive_three_neighbours() {
        LifeGame lifeGame = new LifeGame();
        Assert.assertEquals(true, lifeGame.isCellAlive(true, 3));
    }

    @Test
    public void test_cell_is_alive_one_neighbours() {
        LifeGame lifeGame = new LifeGame();
        Assert.assertEquals(false, lifeGame.isCellAlive(true,1));
    }

    @Test
    public void test_cell_is_alive_more_than_three_neighbours() {
        LifeGame lifeGame = new LifeGame();
        Assert.assertEquals(false, lifeGame.isCellAlive(true,0));
        Assert.assertEquals(false, lifeGame.isCellAlive(true,4));
        Assert.assertEquals(false, lifeGame.isCellAlive(true,5));
        Assert.assertEquals(false, lifeGame.isCellAlive(true,6));
        Assert.assertEquals(false, lifeGame.isCellAlive(true,7));
        Assert.assertEquals(false, lifeGame.isCellAlive(true,8));
    }

    @Test
    public void test_cell_is_dead_neighbours() {
        LifeGame lifeGame = new LifeGame();
        Assert.assertEquals(false, lifeGame.isCellAlive(false,0));
        Assert.assertEquals(false, lifeGame.isCellAlive(false,1));
        Assert.assertEquals(false, lifeGame.isCellAlive(false,2));
        Assert.assertEquals(false, lifeGame.isCellAlive(false,4));
        Assert.assertEquals(false, lifeGame.isCellAlive(false,5));
        Assert.assertEquals(false, lifeGame.isCellAlive(false,6));
        Assert.assertEquals(false, lifeGame.isCellAlive(false,7));
        Assert.assertEquals(false, lifeGame.isCellAlive(false,8));
    }

    @Test
    public void test_cell_is_dead_three_neighbours() {
        LifeGame lifeGame = new LifeGame();
        Assert.assertEquals(true, lifeGame.isCellAlive(false,3));
    }

    public void checkResult(boolean[][] expected, boolean[][] result) {
        for (int i = 0; i<result.length;i++) {
            Assert.assertArrayEquals(expected[i], result[i]);
        }
    }
}
