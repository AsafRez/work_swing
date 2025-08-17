package org.example;

import java.awt.*;


public class Block {
    private boolean not_visible;
    private Color color;
    private boolean destroyable;
    private Image image;
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle block_rect;

    public Block() {
        this.not_visible = false;
        this.color = Color.WHITE;
    }
    public void setRect(int rect_x, int rect_y, int rect_width, int rect_height) {
        this.block_rect = new Rectangle(rect_x-Blocks.BLOCK_WIDTH/2, rect_y-Blocks.BLOCK_HEIGHT/2, rect_width, rect_height);
    }
    public Rectangle getRect() {
        return this.block_rect;
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

    public boolean isNot_visible() {
        return not_visible;
    }

    public void setVisible() {
        if(!destroyable) {
        this.not_visible = true;
    }
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
        if(!not_visible) {
            graphics.setColor(Color.RED);
            graphics.drawRect(x, y, width, height);
        }
    }



    }

