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
public class Literal implements Node {

    private int literal;


    public Literal(int literal) {
        this.literal = literal;
    }


    public int getLiteral() {
        return literal;
    }


    public String render() {
        return Integer.toString(literal);
    }


    public int eval() {
        return literal;
    }


    public void accept(NodeVisitor visitor) {
        visitor.visitLiteral(this);
    }
}
