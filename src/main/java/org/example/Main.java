package org.example;

import javax.swing.*;
import java.awt.*;


public class Main {

    public static final int MENU_WIDTH = 800;
    public static final int MENU_HEIGHT = 480;
    public static JFrame c_window;
    public static final int SCREEN_WIDTH= Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final int SCREEN_HEIGHT= Toolkit.getDefaultToolkit().getScreenSize().height;

    public static void main(String[] args) {
        c_window = create_Menu_Window();
        c_window.setVisible(true);

    }
        //יצירת מסך חדש המביא את מסך ראשי
    private static JFrame create_Menu_Window() {
        JFrame frame = new JFrame();
        frame.setSize(MENU_WIDTH, MENU_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        StartMenu main_menu = new StartMenu(0, 0, MENU_WIDTH, MENU_HEIGHT);
        frame.add(main_menu);
        return frame;
    }
    //יצירת מסך חדש המעביר למשחק עצמו
    public static void start_Game() {
        if(c_window != null) {
            c_window.dispose();
        }
        c_window = new JFrame();
        c_window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        c_window.setLocationRelativeTo(null);
        c_window.setResizable(false);
        c_window.setLayout(null);
        c_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        System.out.println(c_window.getHeight());
        MainGameView the_game = new MainGameView(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        c_window.add(the_game);
        c_window.setVisible(true);
    }
}
