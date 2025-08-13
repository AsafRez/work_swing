package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class StartMenu extends JPanel {
    public StartMenu(int x,int y,int width,int height) {
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.setVisible(true);
       JButton startButton = new JButton("Start");
       startButton.setBounds(0, 250, 100, 100);
       this.add(startButton);
       JCheckBox full_screen = new JCheckBox("Full screen");
       full_screen.setBounds(540, 250, 100, 100);
       this.add(full_screen);
       full_screen.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
               Main.set_Screen(full_screen.isSelected());

           }
       });

    }

}
