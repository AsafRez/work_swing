package org.example;

import javax.swing.*;
import java.awt.*;

public class Blocks extends JPanel {
    private Block[][] blocks;
    public static final int BLOCK_WIDTH=Main.SCREEN_WIDTH/30;
    public static final int BLOCK_HEIGHT=Main.SCREEN_HEIGHT/50;
    public Blocks(int x,int y, int width, int height) {

        //פעולה שמוצאת את הכדור ביחס לבלוקים
        this.blocks = new Block[height/(BLOCK_HEIGHT+5)][width/(BLOCK_WIDTH+5)];
        int block_x=x;
        int block_y=y;
        for (int i = 0; i <blocks.length ; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                blocks[i][j] = new Block();
                blocks[i][j].setHeight(BLOCK_HEIGHT);
                blocks[i][j].setWidth(BLOCK_WIDTH);
                blocks[i][j].setX(block_x);
                block_x += BLOCK_WIDTH + 5;
                blocks[i][j].setY(block_y);
                blocks[i][j].setRect(block_x,block_y,BLOCK_WIDTH,BLOCK_HEIGHT);

            }
            block_x = x;
            block_y += BLOCK_HEIGHT + 5;
        }

    }
    public int getRows(){
        return blocks.length;
    }
    public Rectangle getRect(int i, int j){
        return blocks[i][j].getRect();
    }
    public void setBlockVisible(int i, int j){
        blocks[i][j].setVisible();
    }
    public int getColumns(){
        return blocks[0].length;
    }
    public Block getBlock(int i, int j){
        return blocks[i][j];
    }
    public void paint(Graphics g) {
        for (int i = 0; i <blocks.length ; i++) {
            for (int j = 0; j <blocks[0].length; j++) {
                if(!this.blocks[i][j].isNot_visible()) {
                    this.blocks[i][j].paint(g);
                }
            }
        }

    }}

