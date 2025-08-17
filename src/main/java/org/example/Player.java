package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Player{

    private int size;
    private Block[] bar;
    private int locationX;
    private int locationY;
    private Boolean isAlive = true;
    public static final int PLAYER_WIDTH =Main.SCREEN_WIDTH/30 ;
    public static final int PLAYER_HEIGHT= Main.SCREEN_HEIGHT/45 ;
    public Player (int locationX, int locationY) {
        this.bar = new Block[3];
        for (int i = 0; i < 3; i++) {
            bar[i] = new Block();
            bar[i].setY(locationY);
            bar[i].setX(locationX+i*PLAYER_WIDTH);

        }
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

    public Block[] getBar() {
        return bar;
    }

    public void move_Left() {
        if (this.bar[0].getX() > 0) {
            this.locationX -= 5;
            this.bar[0].setX(this.bar[0].getX() - 30);
            this.bar[1].setX(this.bar[1].getX()-30);
            this.bar[2].setX(this.bar[2].getX()-30);
        }
    }
    public void move_Right(){
        if(this.bar[2].getX()<MainGameView.WIDTH-PLAYER_WIDTH) {
            this.bar[0].setX(this.bar[0].getX() + 30);
            this.bar[1].setX(this.bar[1].getX()+30);
            this.bar[2].setX(this.bar[2].getX()+30);
        }
    }
    public Boolean isAlive(){
        return isAlive;
    }
    public void paint (Graphics graphics){
        Image image=ImageEditor.image_P;

        graphics.drawImage(image,bar[0].getX(),bar[0].getY(),PLAYER_WIDTH*3,PLAYER_HEIGHT,null);


    }

}

