package org.example;

import java.awt.*;


public class Block {
    private boolean destroyed;
    private Color color;
    private boolean destroyable;
    private Image image;
    private int x;
    private int y;
    private int width;
    private int height;

    public Block() {
        this.destroyed = false;
        this.color = Color.WHITE;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isDestroyable() {
        return destroyable;
    }

    public void setDestroyable(boolean destroyable) {
        this.destroyable = destroyable;
    }
    public void paint (Graphics graphics){
        graphics.setColor(Color.RED);
        graphics.fillRect(x, y, width, height);
    }
}
