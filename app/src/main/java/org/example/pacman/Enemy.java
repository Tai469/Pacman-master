package org.example.pacman;

import android.graphics.Bitmap;

public class Enemy
{
    int posH, posW;
    boolean isActive;
    Bitmap bitmap;
    int direction = -1;

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

    public void setHeight(int height)
    {
        this.posH = height;
    }

    public void setWidth(int width)
    {
        this.posW = width;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}