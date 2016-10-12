/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.katas.rpn.ast;

/**
 *
 * @author Chloé Mahalin
 */
public class BinaryOperation implements Node {
    private Node left;
    private Node right;

    private BinaryOperator operator;


    public BinaryOperation(Node left, Node right, BinaryOperator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }


    public String render() {
        StringBuilder str = new StringBuilder();
        str.append(this.left.render());
        str.append(" ");
        str.append(this.right.render());
        str.append(" ");
        str.append(operator.getSymbol());
        return str.toString();
    }


    public int eval() {
        switch (operator) {
            case MINUS:
                return left.eval() - right.eval();
            case PLUS:
                return left.eval() + right.eval();
        }
        return 0;
    }


    public void accept(NodeVisitor visitor) {
        visitor.visitBinaryOperation(this);
    }


    public Node getLeft() {
        return left;
    }


    public Node getRight() {
        return right;
    }


    public BinaryOperator getOperator() {
        return operator;
    }


}
