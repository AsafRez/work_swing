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
        if (e.getKeyCode() == KeyEvent.VK_LEFT|| e.getKeyCode() == KeyEvent.VK_A) {
            this.mainGameView.getPlayer().move_Left();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT|| e.getKeyCode() == KeyEvent.VK_D) {
            this.mainGameView.getPlayer().move_Right();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            MainGameView mainGameView = this.mainGameView;
            mainGameView.pause_Game();
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {

    }
}
