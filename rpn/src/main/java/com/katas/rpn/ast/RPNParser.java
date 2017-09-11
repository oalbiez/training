package com.katas.rpn.ast;

import java.util.Stack;

import static com.katas.rpn.ast.NodeFactory.*;


/**
 *
 * @author Chloé Mahalin
 */
public class RPNParser {

    private enum Operator {
        PLUS("+") {
            public void process(Stack<Node> stack) {
                Node right = stack.pop();
                Node left = stack.pop();
                stack.push(add(left, right));
            }
        }, MINUS("-") {
            public void process(Stack<Node> stack) {
                Node right = stack.pop();
                Node left = stack.pop();
                stack.push(sub(left, right));
            }
        }, SQRT("sqrt") {
            public void process(Stack<Node> stack) {
                stack.push(sqrt(stack.pop()));
            }
        };

        private final String symbol;

        Operator(String symbol) {
            this.symbol = symbol;
        }

        public static Operator find(String token) {
            for (Operator operator : Operator.values()) {
                if(token.equals(operator.symbol)) {
                    return operator;
                }
            }
            return null;
        }

        public abstract void process(Stack<Node> stack);
    }


    public static Node parse(String input) {
        Stack<Node> stack = new Stack<Node>();
        for (String token : input.split(" ")) {
            Operator operator = Operator.find(token);
            if (operator != null) {
                operator.process(stack);
            } else {
                stack.push(literal(Integer.parseInt(token)));
            }
        }
        return stack.pop();
    }
}
