package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class MainGameView extends JPanel {
    public static String USER_NAME = "";
    private JLabel statusLabel;
    private Player player;
    private Ball ball;
    private Blocks blockss;
    public static int WIDTH;
    public static int HEIGHT;
    public boolean pause = false;
    public boolean end = false;
    private JLabel scoreLabel;
    private int score;
    private int highest_score=getHighestScore();
    private JLabel highesLabel;

    public Player getPlayer() {
        return player;
    }

    public MainGameView(int x, int y, int width, int height) {
        this.blockss = new Blocks((width-((Blocks.BLOCK_WIDTH+5)*((width/StartMenu.BLOCKS_LEVEL)/(Blocks.BLOCK_WIDTH+5))))/2, height / 6, width/StartMenu.BLOCKS_LEVEL, height / 6);
        this.WIDTH = width;
        this.HEIGHT = height;
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setBounds(x, y, width, (height));
        this.setVisible(true);
        this.highesLabel = new JLabel("Highest Score: " + highest_score);
        this.highesLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.highesLabel.setForeground(Color.white);
        this.highesLabel.setBounds(width/2 ,40,200,40);
        this.add(highesLabel);
        this.player = new Player(width / 2, height - 200);
        this.ball = new Ball(width / 2, height - 400);
        this.scoreLabel = new JLabel();
        this.scoreLabel.setForeground(Color.WHITE);
        this.scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.scoreLabel.setBounds(width/2,10,200,40);
        this.add(this.scoreLabel);
        this.game_Loop();
    }

    private void check_CollisionPart(boolean left, boolean middle, boolean right) {
        if (left) {
            CollisionPart.LEFT.move_ball();
        } else if (middle) {
            CollisionPart.MIDDLE.move_ball();
        } else if (right) {
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

    private void check_Collision_with_blocks(Ball ball) {
        Rectangle ballRect = new Rectangle(ball.getLocationX()+Ball.BALL_SIZE/2, ball.getLocationY()+Ball.BALL_SIZE/2, 1, 1);
            for (int i = 0; i <this.blockss.getRows(); i++) {
                    for (int j = 0; j <this.blockss.getColumns() ; j++) {
                        Rectangle block_recta=this.blockss.getRect(i,j);
                        Block current_block=blockss.getBlock(i,j);
                     if(ballRect.intersects(block_recta) && !current_block.isNot_visible()){
                         score+=10;
                         int overlapLeft   = ballRect.x + ballRect.width  - block_recta.x;
                         int overlapRight  = block_recta.x + block_recta.width  - ballRect.x;
                         int overlapTop    = ballRect.y + ballRect.height - block_recta.y;
                         int overlapBottom = block_recta.y + block_recta.height - ballRect.y;
                         int minOverlap = Math.min(Math.min(overlapLeft, overlapRight),
                                 Math.min(overlapTop, overlapBottom));
                         if (minOverlap == overlapLeft||minOverlap == overlapRight) {
                             Ball.X_MOVEMENT*=-1;
                             System.out.println("פגיעה מהצד השמאלי או מצד ימני לא אכפת לי איפה");

                         }
                         if (minOverlap == overlapTop||minOverlap == overlapBottom) {
                             Ball.Y_MOVEMENT*=-1;
                             System.out.println("פגיעה מלמעלה או מלמטה לא משנה לי");
                         }
                    blockss.setBlockVisible(i,j);
                    repaint();
                }
            }
        }
    }
    private int getHighestScore() {
        String line= "";
        try {
            FileReader read_score = new FileReader("Score_Board");
            BufferedReader br = new BufferedReader(read_score);
            line=br.readLine();
            br.close();
            read_score.close();
            if(line.isEmpty()){
                return 0;
            }
            String[] parts = line.split("\\|"); // מפריד לפי הסימן
            highest_score = Integer.parseInt(parts[1].trim());

        } catch (IOException e) {
        }
        return highest_score;
    }
    //נהפוך אץ הפונקציה  הזאת שתקבל קלט והקלט הוא או שאתה מצנח או שאתה מפסיד
    //לעשות פונקצייה שאחר ישמנצחים מחזירה למסך ראשי גם קורה כאשר מפסידים
    //לעשות counter ששסופר את הבלוקים שנתפסו ואז בודקים אם זה בגודל המערך
    private void end_game(){
        end=true;
        try {
            FileWriter write_scores = new FileWriter("Score_Board");
            write_scores.write(USER_NAME + "|" + score + "\n");
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
                if(statusLabel !=null){
                    remove(statusLabel);
                    revalidate();
                    statusLabel = null;
                }
                check_Collision_with_blocks(this.ball);
                ball.move_Y();
                ball.move_X();
                repaint();
                check_Collision(ball);
                check_Ball_Bounds();
                this.scoreLabel.setText("Score: " + score);
                if (score>highest_score) {
                    highest_score = score;
                    this.highesLabel.setText("Highest Score: " + highest_score);

                }
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
        pause=!pause;
        if(statusLabel ==null) {
            statusLabel = new JLabel("Pause");
            statusLabel.setForeground(Color.YELLOW);
            Font font = new Font("Ariel", Font.BOLD, 35);
            statusLabel.setFont(font);
            statusLabel.setBounds(0, 0, 400, 40);
            revalidate();
            this.add(statusLabel);
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
    private void check_Ball_Bounds(){
        if(ball.getLocationY()<=0){
            move_Y("Ball_Hitting_Block.wav");
        }else if(ball.getLocationX()<=0||ball.getLocationX()>WIDTH-Ball.BALL_SIZE){
            move_X("Ball_Hitting_Block.wav");

        }else if(ball.getLocationY()>HEIGHT){
            System.out.println("Lost");
            end_game();
        }
    }
private void move_Y(String message){
    Ball.Y_MOVEMENT = (Ball.Y_MOVEMENT*-1);
    new SoundManager(message);
}
    private void move_X(String message){
        Ball.X_MOVEMENT = (Ball.X_MOVEMENT*-1);
        new SoundManager(message);
    }
}
