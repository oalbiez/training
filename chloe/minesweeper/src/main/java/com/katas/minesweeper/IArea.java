package com.katas.minesweeper;

/**
 * @author Chloé Mahalin
 */
public interface IArea {

    int getXSize();

    int getYSize();

    boolean isAMine(int xCoordinate, int yCoordinate);

}
