package org.saadmeddiche.pixelworld;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

public class PixelWorld extends Canvas {

    public final int width;
    public final int height;
    public final String name;
    public final JFrame frame;
    private final BufferedImage bufferedImage;

    public final List<Square> squares = new ArrayList<>();
    public final Map<String, Square> squareMap = new HashMap<>();
    public final Queue<Runnable> actions = new ArrayDeque<>();

    private PixelWorld(int width, int height, String name, JFrame frame) {
        this.width = width;
        this.height = height;
        this.name = name;
        setPreferredSize(new Dimension(width, height));
        this.frame = frame;
        this.bufferedImage = new BufferedImage(width, height, TYPE_INT_RGB);
    }

    public void setPixel(int x, int y , int rgb) {
        this.bufferedImage.setRGB(x, y, rgb);
    }

    public void draw() {

        Graphics graphics = getGraphics();

        if(graphics == null) return;

        graphics.drawImage(bufferedImage, 0, 0, width, height, null);

        graphics.dispose();

    }

    public void addSquare(Square square) {

        if(square == null) return;

        squares.add(square);

        squareMap.put(square.name, square);

    }

    public static PixelWorld create(int width, int height, String name) {

        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        PixelWorld window = new PixelWorld(width, height, name, frame);

        frame.add(window);
        frame.pack();
        frame.setVisible(true);

        return window;

    }

}