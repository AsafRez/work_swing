package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class MainGameView extends JPanel{
    public static String USER_NAME="";
    public static StartMenu.Background game_background;
    private JLabel label;
    private Player player;
    private Ball ball;
    private Dimension screenSize;
    private Blocks blockss;
    public static int WIDTH;
    public static int HEIGHT;
    public  boolean pause=false;
    public  boolean end=false;

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
    this.blockss=new Blocks((Blocks.BLOCK_WIDTH%30)/2,height/6,width,height/3);
    this.WIDTH = width;
    this.HEIGHT = height;
    this.setLayout(null);
    this.setBackground(Color.BLACK);
    this.setBounds(x,y,width,(height));
    this.setVisible(true);
    this.player = new Player(width/2,height-200);
    this.ball=new Ball(width/2,height-400);
    this.game_Loop();
    game_background.setBounds(0,0,width,height);
    this.add(game_background);


}
private void check_CollisionPart(boolean left,boolean middle,boolean right){
        if(left){
            CollisionPart.LEFT.move_ball();
        }
        else if(middle){
             CollisionPart.MIDDLE.move_ball();
        }
        else if(right){
            CollisionPart.RIGHT.move_ball();
        }
}
    private void check_Collision(Ball ball) {
        Rectangle ballRect = new Rectangle(ball.getLocationX(), ball.getLocationY(), Ball.BALL_SIZE, Ball.BALL_SIZE);
        Rectangle playerRect_Left = new Rectangle(this.player.getBar()[0].getX(), this.player.getBar()[0].getY(), Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
        Rectangle playerRect_Middle = new Rectangle(this.player.getBar()[1].getX(), this.player.getBar()[1].getY(), Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
        Rectangle playerRect_Right = new Rectangle(this.player.getBar()[2].getX(), this.player.getBar()[2].getY(), Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
        check_CollisionPart(ballRect.intersects(playerRect_Left), ballRect.intersects(playerRect_Middle), ballRect.intersects(playerRect_Right));

    }

    private void check_Ball_Bounds(){
        if(ball.getLocationY()<=0){
            Ball.Y_MOVEMENT = (Ball.Y_MOVEMENT*-1);
            play_sound("Ball_Hitting_Wall.wav");
        }else if(ball.getLocationX()<=0){
            Ball.X_MOVEMENT = (Ball.X_MOVEMENT*-1);
            play_sound("Ball_Hitting_Wall.wav");
        }else if(ball.getLocationX()>WIDTH-Ball.BALL_SIZE){
            Ball.X_MOVEMENT = (Ball.X_MOVEMENT*-1);
            play_sound("Ball_Hitting_Wall.wav");
        }else if(ball.getLocationY()>HEIGHT){
            System.out.println("Lost");
            end_game();

        }
    }
    private void end_game(){
        end=true;
        try {
            FileWriter write_scores = new FileWriter("Score_Board", true);
            write_scores.write("הניקוד של "+USER_NAME+"  הוא:\n  ");
            write_scores.close();
        }catch (IOException e){
            System.out.println("בעיה בשמירת נתונים");
        }
    }
private void game_Loop(){
    new Thread(()-> {
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new MovementListener(this));
        while (true) {
            if (!pause&&!end) {
                if(label!=null){
                    remove(label);
                    revalidate();
                    label = null;
                }
                ball.move_Y();
                ball.move_X();
                repaint();
                check_Collision(ball);
                check_Ball_Bounds();

            } else{
                pause_Game();
            }
            try {
                Thread.sleep(1000 / 60); // בערך 60FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
                }).start();
}

    public void pause_Game(){
        if(label==null) {
            label = new JLabel("Pause");
            label.setForeground(Color.YELLOW);
            Font font = new Font("Ariel", Font.BOLD, 35);
            label.setFont(font);
            label.setBounds(0, 0, 400, 400);
            revalidate();
            add(label);
            repaint();
        }
}
public void paint(Graphics g){
    super.paint(g);
    this.player.paint(g);
    this.blockss.paint(g);
    if(ball!=null) {
        this.ball.paint(g);
    }
}
private void play_sound(String message){
        SoundManager hitting=new  SoundManager(message);
        hitting.stop_music();

}
}
