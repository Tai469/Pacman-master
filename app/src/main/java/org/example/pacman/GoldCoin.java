package org.example.pacman;

import java.util.Random;

/**
 * This class should contain information about a single GoldCoin.
 * such as x and y coordinates (int) and whether or not the goldcoin
 * has been taken (boolean)
 */

public class GoldCoin {

    private int height, width;
    private boolean isTaken;
    private Random random = new Random();

    public GoldCoin(int height, int width, int padding)
    {
        this.height = random.nextInt(height - padding * 2);
        this.width = random.nextInt(width - padding * 2);
        this.isTaken = false;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean getIsTaken() {
        return isTaken;
    }
}


