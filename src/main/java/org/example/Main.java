package org.example;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static final int WINDOW_WITH = 1024;
    public static final int WINDOW_HEIGHT = 800;
    public static boolean FULL_SCREEN=false;
    public static JFrame window;

    public static void set_Screen(boolean screen){
        if(screen){
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else {
            window.setExtendedState(JFrame.NORMAL);
        }
    }
    public static void main(String[] args) {
        window = new JFrame();
        window.setVisible(true);
        window.setSize(WINDOW_WITH,WINDOW_HEIGHT);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setLayout(null);
        StartMenu maingameview = new StartMenu(0,0,WINDOW_WITH,WINDOW_HEIGHT);
        window.add(maingameview);

    }
}