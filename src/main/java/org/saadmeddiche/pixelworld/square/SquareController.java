package org.saadmeddiche.pixelworld.square;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SquareController implements KeyListener {

    private final JFrame frame;
    private final Square square;

    public SquareController(JFrame frame, Square square) {
        this.square = square;
        this.frame = frame;
        frame.addKeyListener(this);
        frame.setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) square.up();
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) square.down();
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) square.left();
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) square.right();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public void attachController() {
        this.frame.addKeyListener(this);
        this.frame.setFocusable(true);
    }

    public void detachController() {
        this.frame.removeKeyListener(this);
        this.frame.setFocusable(true);
    }

}