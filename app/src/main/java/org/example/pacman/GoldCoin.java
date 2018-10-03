package org.example.pacman;

import android.graphics.Bitmap;
import java.util.Random;

/**
 * This class should contain information about a single GoldCoin.
 * such as x and y coordinates (int) and whether or not the goldcoin
 * has been taken (boolean)
 */

public class GoldCoin {

    private int height, width;
    private boolean isTaken;
    private Bitmap bitmap;
    private Random random = new Random();

    public GoldCoin(int height, int width, int padding, Bitmap bitmap)
    {
        this.height = random.nextInt(height - padding * 2);
        this.width = random.nextInt(width - padding * 2);
        this.isTaken = false;
        this.bitmap = bitmap;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isTaken() {
        return this.isTaken;
    }

    public void taken(){
        this.isTaken = true;
    }

    public Bitmap getBitmap()
    {
        return this.bitmap;
    }
}


