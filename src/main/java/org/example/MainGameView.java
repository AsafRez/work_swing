package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainGameView extends JPanel{
    Player player;
    Ball ball;
    List<Enemy> enemies;
    Dimension screenSize;
    public static int WIDTH;
    public static int HEIGHT;
    public  boolean pause=false;

public MainGameView (int x, int y,int width,int height){
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
            Thread.sleep(1000);
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
}
}
