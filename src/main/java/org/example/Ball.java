package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Ball{
    public static final int BALL_SIZE = Main.SCREEN_WIDTH / 60;

    private int size;
    private int locationX;
    private int locationY;
    private Boolean isAlive = true;
    private Image image;
    public static int Y_MOVEMENT=-2;
    public static int X_MOVEMENT=2;
    private Rectangle rect_ball;


    public Ball(int locationX, int locationY) {
        this.size = BALL_SIZE;
        this.locationX = locationX;
        this.locationY = locationY;
        this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/Balls/Basic_Ball.png"))).getImage();
        this.rect_ball = new Rectangle(locationX, locationY, 10, 10);
    }

    public Rectangle getRect_ball() {
        return rect_ball;
    }
    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
        this.rect_ball.setLocation(locationX, locationY);
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
        this.rect_ball.setLocation(locationX, locationY);

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

    public void move_X() {
        this.locationX+=X_MOVEMENT;
    }

    public void move_Y() {
        this.locationY-=Y_MOVEMENT;

    }

    public void paint(Graphics graphics) {
        graphics.drawImage(this.getImage(), this.locationX, this.locationY, BALL_SIZE, BALL_SIZE, null);
        graphics.drawRect(this.locationX, this.locationY, BALL_SIZE, BALL_SIZE);

    }
}

