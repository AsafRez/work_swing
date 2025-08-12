package org.example;

import java.awt.*;

public class Player implements isAlive {

    private int size;
    private int locationX;
    private int locationY;
    private Boolean isAlive = true;

    public Player (int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
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

    public Boolean isAlive(){
        return isAlive;
    }

    public void paint (Graphics graphics){
        graphics.setColor(Color.black);
        graphics.fillRect(locationX,locationY,Main.PLAYER_WIDTH,Main.PLAYER_HEIGHT);
    }
}

