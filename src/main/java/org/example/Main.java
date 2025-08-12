package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static final int WINDOW_WITH = 1024;
    public static final int WINDOW_HEIGHT = 800;
    public static final int ENEMY_WIDTH = 1;
    public static final int ENEMY_HEIGHT= 1;
    public static final int BALL_SIZE = 2;
    public static final int PLAYER_WIDTH = 20;
    public static final int PLAYER_HEIGHT= 3;


    public static void main(String[] args) {

        Player player = new Player(1, 0, 0);
        Ball ball = new Ball(2,5,5);

    }
}