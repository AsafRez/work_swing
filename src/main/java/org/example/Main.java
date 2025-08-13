package org.example;

import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static final int WINDOW_WITH = 1024;
    public static final int WINDOW_HEIGHT = 800;
    public static JFrame c_window;

    public static void main(String[] args) {
        c_window = create_Menu_Window();
        c_window.setVisible(true);


    }
    private static JFrame create_Menu_Window() {
        JFrame frame = new JFrame();
        frame.setSize(WINDOW_WITH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        StartMenu main_menu = new StartMenu(0, 0, WINDOW_WITH, WINDOW_HEIGHT);
        frame.add(main_menu);
        return frame;
    }
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
        MainGameView the_game = new MainGameView(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        c_window.add(the_game);
        c_window.setVisible(true);
    }

    }
