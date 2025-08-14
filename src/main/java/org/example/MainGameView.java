package org.example;

import javax.swing.*;
import java.awt.*;

public class MainGameView extends JPanel{
    Player player;
    Ball ball;
    Dimension screenSize;
    Blocks blockss;
    public static int WIDTH;
    public static int HEIGHT;
    public  boolean pause=false;

public MainGameView (int x, int y,int width,int height){
    this.blockss=new Blocks(width/4,height/6,width/2,height/3);
    this.WIDTH = width;
    this.HEIGHT = height;
    this.setLayout(null);
    this.setBackground(Color.BLACK);
    this.setBounds(x,y,width,(height));
    this.setVisible(true);
    this.player = new Player(width/2,height-200);
    this.gameLoop();


}
private void gameLoop(){
    new Thread(()->{
        try{
            Thread.sleep(1);
        }catch(InterruptedException e){

        }
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new MovementListener(this));
        while(true) {
            if (!pause) {
                repaint();
            }else{
                pause_Game();
                break;


            }
        }
    }).start();
}
private void pause_Game(){
    JLabel label = new JLabel("Paused");
    label.setForeground(Color.YELLOW);
    Font font = new Font ("Ariel" , Font.BOLD, 35);
    label.setFont(font);
    label.setBounds(this.getWidth()/2,this.getHeight()/2,400,400);
    this.add(label);

}
public void resume_game(){
    gameLoop();
}
public void paint(Graphics g){
    super.paint(g);
    this.player.paint(g);
    this.blockss.paint(g);

}
}
