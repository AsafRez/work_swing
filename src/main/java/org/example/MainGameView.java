package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class MainGameView extends JPanel {
    public static String USER_NAME = "";
    public static int points_per_block;
    private JLabel statusLabel;
    private final Player player;
    private final Ball ball;
    private final Blocks blocks_enemy;
    public static int WIDTH;
    public static int HEIGHT;
    public static SoundManager sound;
    public boolean pause = false;
    public boolean end = false;
    private final JLabel scoreLabel;
    private int score;
    private int highest_score=getHighestScore();
    private final JLabel highesLabel;

    public Player getPlayer() {
        return player;
    }

    //יצירת חלון המשחק
    public MainGameView(int x, int y, int width, int height) {
        //יצירת אובייקטים הבלוקים
        this.blocks_enemy = new Blocks((width-((Blocks.BLOCK_WIDTH+5)*((width/StartMenu.BLOCKS_LEVEL)/(Blocks.BLOCK_WIDTH+5))))/2, height / 6, width/StartMenu.BLOCKS_LEVEL, height / 6);
        WIDTH = width;
        HEIGHT = height;
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setBounds(x, y, width, (height));
        this.setVisible(true);

        //הוספת טקסט ניקוד הכי גבוה
        this.highesLabel = new JLabel("Highest Score: " + highest_score);
        this.highesLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        this.highesLabel.setForeground(Color.white);
        this.highesLabel.setBounds(width/2 ,40,200,40);
        this.add(highesLabel);

        //הוספת האובייקטים למשחק
        this.player = new Player(width / 2, height - 200);
        this.ball = new Ball(width / 2, height - 400);

        //הוספת טקסט ניקוד נוכחי
        this.scoreLabel = new JLabel();
        this.scoreLabel.setForeground(Color.WHITE);
        this.scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.scoreLabel.setBounds(width/2,10,200,40);
        this.add(this.scoreLabel);
        this.statusLabel = new JLabel();

        //התחלת לולאת המשחק
        game_Loop();
    }

    //בדיקת חלק התנגשות של הכדור עם הbar, אמצע ימן או שמאל
    private void check_CollisionPart(boolean left, boolean middle, boolean right) {
        if (left) {
            CollisionPart.LEFT.move_ball();
        } else if (middle) {
            CollisionPart.MIDDLE.move_ball();
        } else if (right) {
            CollisionPart.RIGHT.move_ball();
        }
    }
    //בדיקת התנגשות עם הbar
    private void check_Collision(Ball ball) {
        Rectangle ballRect = new Rectangle(ball.getLocationX(), ball.getLocationY(), Ball.BALL_SIZE, Ball.BALL_SIZE);
        Rectangle playerRect_Left = new Rectangle(this.player.getBar()[0].getX(), this.player.getBar()[0].getY(), Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
        Rectangle playerRect_Middle = new Rectangle(this.player.getBar()[1].getX(), this.player.getBar()[1].getY(), Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
        Rectangle playerRect_Right = new Rectangle(this.player.getBar()[2].getX(), this.player.getBar()[2].getY(), Player.PLAYER_WIDTH, Player.PLAYER_HEIGHT);
        check_CollisionPart(ballRect.intersects(playerRect_Left), ballRect.intersects(playerRect_Middle), ballRect.intersects(playerRect_Right));
    }
    //בדיקת התנגשות עם הבלוקים
    private void check_Collision_with_blocks(Ball ball) {
        Rectangle ballRect = new Rectangle(
                ball.getLocationX(),
                ball.getLocationY(),
                Ball.BALL_SIZE,
                Ball.BALL_SIZE
        );

        for (int i = 0; i < this.blocks_enemy.getRows(); i++) {
            for (int j = 0; j < this.blocks_enemy.getColumns(); j++) {
                Rectangle block_rec = this.blocks_enemy.getRect(i, j);
                Block current_block = blocks_enemy.getBlock(i, j);

                if (ballRect.intersects(block_rec) && !current_block.isNot_visible()) {
                    score += 10;

                    // חישוב מרכזים
                    int ballCenterX = ball.getLocationX() + Ball.BALL_SIZE / 2;
                    int ballCenterY = ball.getLocationY() + Ball.BALL_SIZE / 2;

                    int blockCenterX = block_rec.x + block_rec.width / 2;
                    int blockCenterY = block_rec.y + block_rec.height / 2;

                    int dx = ballCenterX - blockCenterX;
                    int dy = ballCenterY - blockCenterY;

                    // קובעים כיוון פגיעה
                    if (Math.abs(dx) > Math.abs(dy)) {
                        Ball.X_MOVEMENT *= -1; // פגיעה בצדדים
                    } else {
                        Ball.Y_MOVEMENT *= -1; // פגיעה מלמעלה/למטה
                    }

                    // הסתרת הבלוק שנפגע
                    blocks_enemy.setBlockVisible(i, j);
                    repaint();

                    return; // יציאה מיידית כדי שלא יימחקו כמה בלוקים בטעות
                }
            }
        }
    }

    //השגת ציון הגבוה ביותר מקובץ חיצוני
    private int getHighestScore() {
        String line;
        try {
            FileReader read_score = new FileReader("Score_Board");
            BufferedReader br = new BufferedReader(read_score);
            while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|"); // מפריד לפי הסימן
                int current_score = Integer.parseInt(parts[1]);
                if (current_score > highest_score) {
                    highest_score = current_score;
                }
            highest_score = Integer.parseInt(parts[1].trim());
        }
            br.close();
            read_score.close();
        } catch (IOException _) {
        }
        return highest_score;

    }
    private void end_game(String status){
        end=true;
        try {
            FileWriter write_scores = new FileWriter("Score_Board",true);
            write_scores.write(USER_NAME + "|" + score + "\n");
            write_scores.close();
            if(status.equals("Lost")){
                edit_status_label("You Lose");
            }else {edit_status_label("You Win");}
        }catch (IOException e){
            System.out.println("בעיה בשמירת נתונים");
        }
        JButton backToMenu = new JButton("Back To Menu");
        this.add(backToMenu);
        backToMenu.setBounds(0,0,200,30);
        backToMenu.setHorizontalAlignment(SwingConstants.CENTER);
        backToMenu.setFont(new Font("Ariel",Font.BOLD,10));
        backToMenu.addActionListener((ActionEvent) ->{
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (topFrame != null) {
                topFrame.dispose(); // סוגר את חלון המשחק
                sound.stop_music();
            }
            Main.open_Menu(); // פותח את חלון התפריט
        });
    }

    //עדכון סטוטס משחק בהתאם למצב
    private void edit_status_label(String status){
        if(statusLabel ==null) {
            statusLabel = new JLabel(status);
            statusLabel.setForeground(Color.YELLOW);
            Font font = new Font("Ariel", Font.BOLD, 150);
            statusLabel.setFont(font);
            statusLabel.setBounds(0, HEIGHT/2, WIDTH, 200);
            statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
            revalidate();
            this.add(statusLabel);
            repaint();
        }
    }
    //פעולת המשחק עצמה, לולאה שמזיזה שחקן וכדור
private void game_Loop(){
    new Thread(()-> {
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new MovementListener(this));
        while (true) {
            if(score/points_per_block==blocks_enemy.getRows()*blocks_enemy.getColumns()){
                end_game("Win");
            }
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
            } catch (InterruptedException _) {
            }
        }
                }).start();
}

    //פעולת הפסקת המשחק חלקית לקחת הפסקה
    public void pause_Game(){
        pause=!pause;
        edit_status_label("Pause");
        }

public void paint(Graphics g){
    super.paint(g);
    this.player.paint(g);
    this.blocks_enemy.paint(g);
    if(ball!=null) {
        this.ball.paint(g);
    }
}
    //פעולת בדיקת מיקום הכדור לגבי גבולות המסך
    private void check_Ball_Bounds(){
        if(ball.getLocationY()<=0){
            move_Y();
        }else if(ball.getLocationX()<=0||ball.getLocationX()>WIDTH-Ball.BALL_SIZE){
            move_X();

        }else if(ball.getLocationY()>HEIGHT){
            end_game("Lost");
        }
    }
private void move_Y(){
    Ball.Y_MOVEMENT = (Ball.Y_MOVEMENT*-1);
}
    private void move_X(){
        Ball.X_MOVEMENT = (Ball.X_MOVEMENT*-1);
    }


}
