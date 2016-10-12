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
public class UnaryOperation implements Node {
    private Node child;

    private UnaryOperator operator;


    public Node getChild() {
        return child;
    }


    public UnaryOperator getOperator() {
        return operator;
    }


    public UnaryOperation(Node child, UnaryOperator operator) {
        this.child = child;
        this.operator = operator;
    }


    public String render() {
        StringBuilder str = new StringBuilder();
        str.append(this.child.render());
        str.append(" ");
        str.append(operator.getSymbol());
        return str.toString();
    }


    public int eval() {
        return 0;
    }


    public void accept(NodeVisitor visitor) {
        visitor.visitUnaryOperation(this);
    }

}
