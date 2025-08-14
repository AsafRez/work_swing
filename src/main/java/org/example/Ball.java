package org.example;

import java.awt.*;

public class Ball implements isAlive {
    private static final int BALL_SIZE = 10;

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

    public void paint (Graphics graphics){
        graphics.setColor(Color.RED);
        graphics.fillOval(Main.WINDOW_HEIGHT/2,Main.WINDOW_WITH/2,BALL_SIZE,BALL_SIZE);
        }
    }

