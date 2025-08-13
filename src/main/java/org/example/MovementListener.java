package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MovementListener implements KeyListener {
    private MainGameView mainGameView;
    public MovementListener(MainGameView mainGameView) {
        this.mainGameView = mainGameView;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            this.mainGameView.player.move_Left();
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.mainGameView.player.move_Right();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
