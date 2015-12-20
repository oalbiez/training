package com.katas.rpn.stack;


import org.junit.Assert;
import org.junit.Test;


/**
 *
 * @author Chloé Mahalin
 */
public class RpnCalculatorTest {

    @Test
    public void test_with_literal() throws CalculationException {
        testCase("4", 4);
    }


    @Test
    public void test_with_divided() throws CalculationException {
        testCase("20 5 /", 4);
        testCase("3 3 /", 1);
    }


    @Test
    public void test_with_plus() throws CalculationException {
        testCase("20 5 +", 25);
    }


    @Test
    public void test_with_minus() throws CalculationException {
        testCase("20 5 -", 15);
    }


    @Test
    public void test_with_minus_and_plus() throws CalculationException {
        testCase("4 2 + 3 -", 3);
        testCase("4 2 + 3 - 3 +", 6);
        testCase("20 10 - 5 -", 5);
        testCase("20 5 + 6 +", 31);
    }


    @Test
    public void test_with_successives_operators() throws CalculationException {
        testCase("1 2 3 + +", 6);
        testCase("1 2 3 4 5 + + + +", 15);
    }


    @Test
    public void test_with_sqrt() throws CalculationException {
        testCase("4 sqrt", 2);
        testCase("9 sqrt", 3);
        testCase("5 sqrt", 2);
        testCase("1 4 + sqrt", 2);
    }


    @Test
    public void test_with_max() throws CalculationException {
        testCase("1 2 3 4 5 max", 5);
        testCase("6 1 2 3 4 5 max", 6);
        testCase("6 1 7 3 4 5 max", 7);
        testCase("3 2 - 5 max", 5);
    }


    //@Test
    public void test_with_min() throws CalculationException {
        testCase("1 2 3 4 5 min", 1);
        testCase("6 3 4 5 min", 3);
        testCase("6 7 4 2 4 5 min", 2);
    }


    @Test
    public void test_with_squared() throws CalculationException {
        testCase("2 squared", 4);
        testCase("3 squared", 9);
    }


    //@Test
    public void test_with_squared_and_sqrt() throws CalculationException {
        testCase("2 squared sqrt", 2);
        testCase("3 squared sqrt", 3);
    }


    private static void testCase(String input, int expectd) throws CalculationException {
        Assert.assertEquals("Check result  : " + input, expectd, new RpnCalculatorWithRecursivity().calculate(input));
    }
}
