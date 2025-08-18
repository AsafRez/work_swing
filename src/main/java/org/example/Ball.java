package org.example;

import java.awt.*;

//מחלקת כדור האחראית על יצירת אובייקט מסוג כדור שפוגע בבלוקים
public class Ball{
    public static final int BALL_SIZE = Main.SCREEN_WIDTH / 65;

    private int locationX;
    private int locationY;
    private Image image;
    public static int Y_MOVEMENT=-2;
    public static int X_MOVEMENT=2;
    private Rectangle rect_ball;


    public Ball(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.image = ImageEditor.image_BA;
        this.rect_ball = new Rectangle(locationX, locationY, 10, 10);
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
    public Image getImage() {
        return image;
    }

    public void move_X() {
        this.locationX+=X_MOVEMENT;
    }

    public void move_Y() {
        this.locationY-=Y_MOVEMENT;

    }
    public void paint(Graphics graphics) {
        graphics.drawImage(this.getImage(), this.locationX, this.locationY, BALL_SIZE, BALL_SIZE, null);

    }
}

