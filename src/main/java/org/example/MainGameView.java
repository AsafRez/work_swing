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
    private JLabel label;

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
    this.ball=new Ball(width/2,height-400);
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
    private boolean checkBall(){
        if(this.ball.getLocationY()>HEIGHT-Ball.BALL_SIZE){
           pause_Game("Lost");
            return true;
        }
        return false;
    }
private void gameLoop(){
    new Thread(()->{
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new MovementListener(this));

        while (true) {
            if (!checkBall()) {
                if (!pause) {
                    ball.setLocationX(ball.getLocationX()+1);
                    ball.setLocationY(ball.getLocationY()+1);
                    repaint();
                } else {
                    break;
                }
                try {
                    Thread.sleep(1000 / 60); // בערך 60FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }).start();
}
private void pause_Game(String message){
    label = new JLabel(message);
    label.setForeground(Color.YELLOW);
    Font font = new Font ("Ariel" , Font.BOLD, 35);
    label.setFont(font);
    label.setBounds(0,0,400,400);
    this.add(label);
    this.revalidate();
    this.repaint();

}
public void resume_game(){
    gameLoop();
    label.setVisible(false);

}
    public void stop_game(){
        pause_Game("Paused");
    }
public void paint(Graphics g){
    super.paint(g);
    this.player.paint(g);
    this.blockss.paint(g);
    if(ball!=null) {
        this.ball.paint(g);
    }
}
}
