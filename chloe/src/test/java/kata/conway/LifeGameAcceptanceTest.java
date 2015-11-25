package kata.conway;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by cmahalin111813 on 24/11/2015.
 */
public class LifeGameAcceptanceTest {

    @Test
    public void test_init_case() {
        LifeGame instanceToTest = new LifeGame();
        boolean[][] result = instanceToTest.process(0, new boolean[][]{
                {false, false, false},
                {false, false, false},
                {false, false, false}
        });

        boolean[][] expected = {
                {false,false,false},
                {false,false,false},
                {false,false,false}};

       checkResult(expected,result);
    }

    @Test
    public void test_cell_with_no_neighbour() {
        LifeGame instanceToTest = new LifeGame();
        boolean[][] result = instanceToTest.process(1, new boolean[][]{
                {true, false, false},
                {false, false, false},
                {false, false, false}
        });

        boolean[][] expected = {
                {false,false,false},
                {false,false,false},
                {false,false,false}};

        checkResult(expected,result);
    }

    @Test
    public void test_cell_with_three_neighbour() {
        LifeGame instanceToTest = new LifeGame();
        boolean[][] result = instanceToTest.process(1, new boolean[][]{
                {false, false, true},
                {false, false, true},
                {true, false, false}
        });

        boolean[][] expected = {
                {false,false,false},
                {false,true,false},
                {false,false,false}};

        checkResult(expected,result);
    }

    public void checkResult(boolean[][] expected, boolean[][] result) {
        for (int i = 0; i<result.length;i++) {
            Assert.assertArrayEquals(expected[i], result[i]);
        }
    }
}
