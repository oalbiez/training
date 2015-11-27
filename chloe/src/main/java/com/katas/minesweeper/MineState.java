package com.sgcib.katas.minesweeper;

/**
* @author Chloé Mahalin
*/
public enum MineState {
    MINE('*'), NO_MINE('.');

    private char symbol;

    private MineState(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static MineState getStateFromSymbol(char symbol) {
        if (symbol == '*') {
            return MINE;
        } else {
            return NO_MINE;
        }
    }
}
