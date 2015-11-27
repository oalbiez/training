/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rpn.rpnwork.ast;

/**
 *
 * @author cmahalin111813
 */
public enum BinaryOperator {
    PLUS("+"), MINUS("-");

    private final String symbol;


    private BinaryOperator(String symbol) {
        this.symbol = symbol;
    }


    public String getSymbol() {
        return symbol;
    }
}
