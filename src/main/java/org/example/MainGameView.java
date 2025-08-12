package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainGameView extends JPanel{
    Player player;
    Ball ball;
    List<Enemy> enemies;


public MainGameView (){
    this.setLayout(null);
    this.setBounds(0,0,Main.WINDOW_WITH,(int) (Main.WINDOW_HEIGHT * 0.9));
    this.setVisible(true);
    this.player = new Player(Main.WINDOW_HEIGHT/2,Main.WINDOW_HEIGHT-5);
    this.ball = new Ball(Main.WINDOW_HEIGHT/2,Main.WINDOW_HEIGHT/2);
    JLabel title = new JLabel("Click Counter");
    Font font = new Font ("Ariel" , Font.ITALIC, 24);
    title.setBounds(0,0,Main.WINDOW_WITH/2,Main.WINDOW_HEIGHT/2);
}


}
