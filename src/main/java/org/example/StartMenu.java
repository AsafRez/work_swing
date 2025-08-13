package org.example;

import javax.swing.*;
import java.awt.*;


public class StartMenu extends JPanel {
    public StartMenu(int x,int y,int width,int height) {
        this.setBounds(x,y,width,height);
        this.setBackground(Color.blue);
        this.setLayout(null);
        this.setVisible(true);
        JButton start_button=this.create_Button((int)((width/2)),height/2+50,100,100,"Start");
        JButton exit_button=this.create_Button((int)((width/4)),height/2+50,100,100,"Exit");
        exit_button.addActionListener((event)->{
            System.exit(0);
        });
        start_button.addActionListener((event)->{
          Main.start_Game();

        });
        Font font = new Font ("Ariel" , Font.ITALIC, 24);
        JLabel start_label=create_Label(width/2,0,650,45,"Blocks Bomber",font);
        start_label.setForeground(Color.WHITE);

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
