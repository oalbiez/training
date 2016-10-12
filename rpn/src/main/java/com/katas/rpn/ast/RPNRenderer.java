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
public class RPNRenderer implements NodeVisitor {

    StringBuilder builder = new StringBuilder();


    public String render() {
        return builder.toString();
    }


    public void visitBinaryOperation(BinaryOperation node) {
        node.getLeft().accept(this);
        builder.append(" ");
        node.getRight().accept(this);
        builder.append(" ");
        builder.append(node.getOperator().getSymbol());
    }


    public void visitLiteral(Literal node) {
        builder.append(Integer.toString(node.getLiteral()));
    }


    public void visitUnaryOperation(UnaryOperation node) {
        node.getChild().accept(this);
        builder.append(" ");
        builder.append(node.getOperator().getSymbol());
    }


}
