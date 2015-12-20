package com.katas.minesweeper;

/**
 * @author : Chloé Mahalin
 * L'encapsulation permet d'anticiper l'évolution.
 */
public class Square {

    private MineState state;

    public MineState getState() {
        return state;
    }

    public void setState(MineState state) {
        this.state = state;
    }
}
