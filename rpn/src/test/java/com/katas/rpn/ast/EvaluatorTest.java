/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.katas.rpn.ast;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Chloé Mahalin
 */
public class EvaluatorTest {

    @Test
    public void test_with_one_literal() {
        Assert.assertEquals(4, literal(4).eval());
    }


    @Test
    public void test_with_addition() {
        Assert.assertEquals(2, add(literal(1), literal(1)).eval());
    }


    @Test
    public void test_with_deep_addition() {
        Assert.assertEquals(4, add(add(literal(1), literal(1)), literal(2)).eval());
        Assert.assertEquals(4, add(literal(1), add(literal(1), literal(2))).eval());
    }


    @Test
    public void test_with_subtraction() {
        Assert.assertEquals(0, sub(literal(1), literal(1)).eval());
    }


    //@Test
    public void test_with_sqrt() {
        Assert.assertEquals("1 sqrt", sqrt(literal(1)).render());
    }


    //@Test
    public void test_with_complex() {
        Assert.assertEquals("1 sqrt 1 +", add(sqrt(literal(1)), literal(1)).render());
    }


    private static Node literal(int value) {
        return new Literal(value);
    }


    private static Node add(Node left, Node right) {
        return new BinaryOperation(left, right, BinaryOperator.PLUS);
    }


    private static Node sub(Node left, Node right) {
        return new BinaryOperation(left, right, BinaryOperator.MINUS);
    }


    private static Node sqrt(Node left) {
        return new UnaryOperation(left, UnaryOperator.SQRT);
    }

}
