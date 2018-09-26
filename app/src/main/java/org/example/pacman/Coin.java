package org.example.pacman;

public class Coin{

    private int heigh, width;
    private boolean isTaken;

    public Coin(int height, int width)
    {
        this.heigh = height;
        this.width = width;
        this.isTaken = false;
    }

    public int getHeigth() {
        return heigh;
    }
    public void setHeigt( int heigh) {
        this.heigh = heigh;
    }

    public int getWidtg() {
        return width;
    }
    public void setWidth( int width) {
        this.width = width;
    }

    public boolean getIsTaken() {
        return isTaken;
    }
    public void setIsTaken( boolean taken) {
        this.isTaken = taken;
    }
}