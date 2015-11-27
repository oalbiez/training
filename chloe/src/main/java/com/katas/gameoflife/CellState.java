package com.katas.gameoflife;

/**
 * Created by cmahalin111813 on 26/11/2015.
 */
public enum CellState {
    ALIVE('*'), DEAD('.');

    private char symbol;

    private CellState(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static CellState getStateFromSymbol(char symbol) {
        if (symbol == '*') {
            return ALIVE;
        } else {
            return DEAD;
        }
    }
}
