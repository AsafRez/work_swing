package org.example;


import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class StartMenu extends JPanel {
   public class Background extends JPanel {
        private final Image image;
        public Background(String imageName) {
            this.image = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Images/"+ imageName +".png"))).getImage();
            this.setLayout(null);
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
    private Background image_Background;
    private String[] images={"stars","stars2","retro","hills"};
    public StartMenu(int x,int y,int width,int height) {
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.image_Background = new Background(images[(int)(Math.random()*images.length)]);
        this.image_Background.setBounds(0,0,width,height);
        SoundManager manger = new SoundManager("Main_page_music.wav");
        JButton start_button=this.create_Button((int)((width/2)),height/2+50,100,100,"Start");
        JButton exit_button=this.create_Button((int)((width/4)),height/2+50,100,100,"Exit");
        Font font = new Font ("Ariel" , Font.BOLD, 35);
        JLabel start_label=create_Label(width/2-200,0,650,45,"Blocks Bomber",font);
        start_label.setForeground(Color.WHITE);
        JTextField user_name=new JTextField(15);
        user_name.setBounds(width/5+70,height/2,200, 40);
        user_name.setEditable(true);
        user_name.setFont(new Font("Ariel",Font.BOLD,20));
        JLabel enter_Uname=create_Label(width/5+70,height/3,650,45,"Enter your name",font);
        enter_Uname.setForeground(Color.WHITE);
        this.add(user_name);
        this.setVisible(true);
        this.repaint();
        exit_button.addActionListener((event)->{
            System.exit(0);

        });
        start_button.addActionListener((event)->{
            MainGameView.USER_NAME=user_name.getText();
            MainGameView.game_background=image_Background;
            Main.start_Game();
            manger.stop_music();

        });
        this.add(image_Background);

    }

    public Background getImage_Background() {
        return image_Background;
    }

    private JLabel create_image(int x, int y, int width, int height, String name) {
        try {
            // טוען את התמונה כמשאב מתוך תיקיית images שב-classpath
            // שים לב לנתיב שמתחיל ב- `/`
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/"+name+".png"));
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setBounds(x,y,width,height);
            this.add(imageLabel);
            return imageLabel;
            // מוסיף את ה-JLabel ל-JPanel הנוכחי
        } catch (NullPointerException e) {
            System.err.println("שגיאה: התמונה לא נמצאה. ודא שהנתיב נכון.");
            return null;
        }
    }
    private JButton create_Button(int x,int y,int width,int height,String text) {
        JButton startButton = new JButton(text);
        startButton.setBounds(x, y, width, height);
        this.add(startButton);
        return startButton;
    }
    private JLabel create_Label(int x, int y, int width, int height, String text, Font font) {
        JLabel startLabel = new JLabel(text);
        startLabel.setBounds(x, y, width, height);
        startLabel.setFont(font);
        startLabel.setText(text);
        this.add(startLabel);
        return startLabel;
    }

}
