package org.example;

import javax.swing.*;
import java.awt.*;

public class MainGameView extends JPanel{
//    private static int final lives;
    private Player player;
    private Ball ball;
    private Dimension screenSize;
    private Blocks blockss;
    public static int WIDTH;
    public static int HEIGHT;
    public  boolean pause=false;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public MainGameView (int x, int y, int width, int height){
    this.blockss=new Blocks(width/4,height/6,width/2,height/3);
    this.WIDTH = width;
    this.HEIGHT = height;
    this.setLayout(null);
    this.setBackground(Color.BLACK);
    this.setBounds(x,y,width,(height));
    this.setVisible(true);
    this.player = new Player(width/2,height-200);
        System.out.println(player);
    this.ball=new Ball(width/2,height-400);
        System.out.println(ball.getLocationX());

    this.gameLoop();



}
    private boolean checkCollision (Ball ball) {
        Rectangle ballRect = new Rectangle(ball.getLocationX(), ball.getLocationY(), Ball.BALL_SIZE, Ball.BALL_SIZE);
        Rectangle playerRect = new Rectangle(this.player.getLocationX(), this.player.getLocationY(), (Player.PLAYER_WIDTH)*3, Player.PLAYER_HEIGHT);
        if (ballRect.intersects(playerRect)) {
            return true;
        } else {
            return false;
        }
    }
private void gameLoop(){
    new Thread(()->{
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new MovementListener(this));
        while (true) {
            if (!pause) {
                ball.move_Down(1);
                repaint();
                if(this.checkCollision(ball)){
                    System.out.println("Collision");
                }
            } else {
                break;
            }
        try {
            Thread.sleep(10); // בערך 60FPS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
    }).start();
}
private void pause_Game(){
    JLabel label = new JLabel("Paused");
    label.setForeground(Color.YELLOW);
    Font font = new Font ("Ariel" , Font.BOLD, 35);
    label.setFont(font);
    label.setBounds(0,0,400,400);
    System.out.println(label.getText());
    this.add(label);

}
public void resume_game(){
    gameLoop();

}
    public void stop_game(){
        pause_Game();
    }
public void paint(Graphics g){
    super.paint(g);
    this.player.paint(g);
    this.blockss.paint(g);
    this.ball.paint(g);

}
}
