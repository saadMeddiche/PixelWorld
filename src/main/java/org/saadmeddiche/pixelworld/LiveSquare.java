package org.saadmeddiche.pixelworld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LiveSquare implements KeyListener {

    private final Square square;
    private final JFrame frame;

    public LiveSquare(Square square) {
        this.square = square;

        JFrame frame = new JFrame("Live Square " + square.name);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(500, 100));
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.setFocusable(true);

        this.frame = frame;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) square.actions.add(square::up);
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) square.actions.add(square::down);
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) square.actions.add(square::left);
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) square.actions.add(square::right);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}