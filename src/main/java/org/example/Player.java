package org.example;

import java.awt.*;

public class Player implements isAlive {
    private static final int PLAYER_WIDTH =350 ;
    private static final int PLAYER_HEIGHT= 25;
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
    public void move_Left() {
        if (locationX > 0) {
            this.locationX -= 5;
        }
    }
    public void move_Right(){
        if(locationX<MainGameView.WIDTH-PLAYER_WIDTH) {
            this.locationX += 5;
        }
    }
    public Boolean isAlive(){
        return isAlive;
    }
    public void paint (Graphics graphics){
        graphics.setColor(Color.GREEN);
        graphics.fillRect(locationX,locationY,PLAYER_WIDTH,PLAYER_HEIGHT);
    }
}

