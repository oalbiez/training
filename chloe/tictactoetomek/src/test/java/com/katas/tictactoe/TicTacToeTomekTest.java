package com.katas.tictactoe;

import org.junit.Assert;
import org.junit.Test;

public class TicTacToeTomekTest {

    @Test
    public void empty_square() {
        checkAreaState("Game has not completed", "...", "...", "...");
    }

    /**
     * =====================================METHODES=====================================*
     */
    public void checkAreaState(String expected, String... lines) {
        TicTacToeTomek game = new TicTacToeTomek();
        Assert.assertEquals(expected, game.checkstate(lines));
    }

}
