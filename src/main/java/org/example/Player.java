package org.example;

import java.awt.*;

public class Player implements isAlive {

    private int size;
    private int locationX;
    private int locationY;
    private Boolean isAlive = true;
    private final int PLAYER_WIDTH =Main.SCREEN_WIDTH/10 ;
    private final int PLAYER_HEIGHT= Main.SCREEN_HEIGHT/100 ;
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

