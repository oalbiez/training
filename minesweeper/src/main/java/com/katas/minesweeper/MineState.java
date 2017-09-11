package com.katas.minesweeper;

/**
* @author Chloé Mahalin
*/
public enum MineState {
    MINE('*'), NO_MINE('.');

    private final char symbol;

    MineState(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static MineState getStateFromSymbol(char symbol) {
        return (symbol == '*') ? MINE : NO_MINE;

    }
}
