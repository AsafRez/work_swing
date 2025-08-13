package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainGameView extends JPanel{
    Player player;
    Ball ball;
    List<Enemy> enemies;
    Dimension screenSize;


public MainGameView (int x, int y,int width,int height){

    this.setLayout(null);
    this.setBackground(Color.BLACK);
    this.setBounds(x,y,width,(height));
    this.setVisible(true);
    this.player = new Player(width/2,height-200);
    this.ball = new Ball(Main.WINDOW_HEIGHT/2,Main.WINDOW_HEIGHT/2);
    JLabel title = new JLabel("Click Counter");
    Font font = new Font ("Ariel" , Font.ITALIC, 24);
    title.setBounds(x,y,Main.WINDOW_WITH/2,Main.WINDOW_HEIGHT/2);
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
        while(true){
            repaint();

        }
    }).start();

}
public void paint(Graphics g){
    super.paint(g);
    this.player.paint(g);
}
}
