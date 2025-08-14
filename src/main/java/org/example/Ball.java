package org.example;

import java.awt.*;

public class Ball implements isAlive {
    private static final int BALL_SIZE = Main.SCREEN_WIDTH/95;

    private int size;
    private int locationX;
    private int locationY;
    private Boolean isAlive = true;


    public Ball( int locationX, int locationY) {
        this.size =BALL_SIZE;
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

    public Boolean isAlive() {
        return isAlive;
    }
    public void move_Left(){
        this.locationX--;
    }
    public void move_Right(){
        this.locationX++;
    }
    public void move_Up(){
        this.locationY--;

    }
    public void move_Down(){
        this.locationY++;
    }
    public void paint (Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillOval(this.locationX,this.locationY,BALL_SIZE,BALL_SIZE);
        }
    }

