package org.example;


import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;

public class StartMenu extends JPanel {
    public StartMenu(int x,int y,int width,int height) {
        this.setBounds(x,y,width,height);
        this.setBackground(Color.blue);
        this.setLayout(null);
        this.setVisible(true);
        SoundManager manger = new SoundManager("Main_page_music.wav");
        JButton start_button=this.create_Button((int)((width/2)),height/2+50,100,100,"Start");
        JButton exit_button=this.create_Button((int)((width/4)),height/2+50,100,100,"Exit");
        exit_button.addActionListener((event)->{
            System.exit(0);

        });
        start_button.addActionListener((event)->{
          Main.start_Game();
          manger.stop_music();

        });
        Font font = new Font ("Ariel" , Font.BOLD, 35);
        JLabel start_label=create_Label(width/2,0,650,45,"Blocks Bomber",font);
        start_label.setForeground(Color.WHITE);

//        JLabel tennis_image=create_image(0,0,50,50,"Tennis_Ball");
//        JLabel basketball_image=create_image(51,0,50,50,"Football");
//        JLabel football_image=create_image(105,0,50,50,"Basic_Ball");



    }

    private JLabel create_image(int x,int y,int width,int height,String name) {
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
