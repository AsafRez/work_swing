package org.example;

import java.awt.*;

public class Enemy  implements isAlive {
    private static final int ENEMY_WIDTH = 1;
    private static final int ENEMY_HEIGHT= 1;

    private int width = ENEMY_WIDTH;
    private int height = ENEMY_HEIGHT;
    private int locationX;
    private int locationY;
    private Boolean isAlive = true;


    public Enemy(int x, int y) {
        this.locationX = x;
        this.locationY = y;


    }

    public Boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;

    }
    public void paint (Graphics graphics){
        graphics.setColor(Color.blue);
        graphics.fillRect(locationX,locationY,ENEMY_WIDTH,ENEMY_HEIGHT);
    }
}
