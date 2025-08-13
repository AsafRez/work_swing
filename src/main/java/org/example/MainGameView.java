package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainGameView extends JPanel{
    Player player;
    Ball ball;
    List<Enemy> enemies;


public MainGameView (JFrame mainFrame){
    this.setLayout(null);
    this.setBackground(Color.BLACK);
    this.setBounds(0,0,mainFrame.getWidth(),(mainFrame.getHeight()));
    this.setVisible(true);
    this.player = new Player(Main.WINDOW_HEIGHT/2,Main.WINDOW_HEIGHT-5);
    this.ball = new Ball(Main.WINDOW_HEIGHT/2,Main.WINDOW_HEIGHT/2);
    JLabel title = new JLabel("Click Counter");
    Font font = new Font ("Ariel" , Font.ITALIC, 24);
    title.setBounds(0,0,Main.WINDOW_WITH/2,Main.WINDOW_HEIGHT/2);
}


}
