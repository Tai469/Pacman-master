package org.example.pacman;

import android.graphics.Bitmap;

public class Enemy
{
    int posH, posW;
    boolean isActive;
    Bitmap bitmap;

    public Enemy(int screenH, int screenW, Bitmap bitmap)
    {
        this.posH = screenH / 2;
        this.posW = screenW / 2;
        this.isActive = true;
        this.bitmap = bitmap;
    }

    public int getHeight() {
        return this.posH;
    }

    public int getWidth() {
        return this.posW;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public Bitmap getBitmap()
    {
        return this.bitmap;
    }
}