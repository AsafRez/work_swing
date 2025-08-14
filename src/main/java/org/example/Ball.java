package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Objects;

public class Ball{
    public static final int BALL_SIZE = Main.SCREEN_WIDTH / 60;

    private int size;
    private int locationX;
    private int locationY;
    private Boolean isAlive = true;
    private Image image;


    public Ball(int locationX, int locationY) {
        this.size = BALL_SIZE;
        this.locationX = locationX;
        this.locationY = locationY;
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/Basic_Ball.png"))).getImage();
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

    public Image getImage() {
        return image;
    }

    public void setImage(String path) {
        this.image = image;
    }

    public Boolean isAlive() {
        return isAlive;
    }

    public void move_Left_Up() {
        this.locationX--;
        this.locationY++;
    }

    public void move_Right(int input) {
        this.locationX+=input;
    }

    public void move_Up() {
        this.locationY--;

    }

    public void move_Down(int input) {
        this.locationY+=input;
    }
    //לבדוק אם צריך
//    public void paint (Graphics graphics){
//        graphics.setColor(Color.WHITE);
//        graphics.fillOval(this.locationX,this.locationY,BALL_SIZE,BALL_SIZE);
//        }

    public void paint(Graphics graphics) {
        graphics.drawImage(this.getImage(), this.locationX, this.locationY, BALL_SIZE, BALL_SIZE, null);

    }
}

