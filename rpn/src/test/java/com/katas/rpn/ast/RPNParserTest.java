package com.katas.rpn.ast;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Chloé Mahalin
 */
public class RPNParserTest {

    @Test
    public void test_with_one_literal() {
        Assert.assertEquals("4", parse("4"));
    }

    @Test
    public void test_with_addition() {
        Assert.assertEquals("1 1 +", parse("1 1 +"));
    }

    @Test
    public void test_with_deep_addition() {
        Assert.assertEquals("1 1 + 2 +", parse("1 1 + 2 +"));
        Assert.assertEquals("1 1 2 + +", parse("1 1 2 + +"));
    }

    @Test
    public void test_with_subtraction() {
        Assert.assertEquals("1 1 -", parse("1 1 -"));
    }

    @Test
    public void test_with_sqrt() {
        Assert.assertEquals("1 sqrt", parse("1 sqrt"));
    }

    private static String parse(String input) {
        Node ast = RPNParser.parse(input);
        RPNRenderer renderer = new RPNRenderer();
        ast.accept(renderer);
        return renderer.render();
    }
}
