package org.saadmeddiche.pixelworld;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.saadmeddiche.pixelworld.tree.Tree;
import org.saadmeddiche.pixelworld.world.PixelWorld;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

@Slf4j
@Component
public class RenderEngine {

    private long FRAME_COUNTER = 0;
    private final static int FRAME_WIDTH = 500;
    private final static int FRAME_HEIGHT = 500;

    public static volatile double deltaTime = 0;
    private static volatile long lastTime = System.nanoTime();

    private @Getter final JFrame frame;
    private final PixelWorld mainWorld;
    private final BufferedImage bufferedImage;

    public RenderEngine(PixelWorld mainWorld) {

        this.mainWorld = mainWorld;
        this.frame = createJframe();
        this.bufferedImage = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, TYPE_INT_RGB);

    }

    @Scheduled(fixedRate = 30)
    public void run() {

        log.info(AnsiOutput.toString(AnsiColor.MAGENTA, "Frame #{} delta #{}"), FRAME_COUNTER++, deltaTime);

        updateClock();

        clear();

        script_paint_squares(mainWorld);

        script_paint_trees(mainWorld);

        draw();

    }

    private void script_paint_squares(PixelWorld world) {

        for(var square : world.getSquares()) {

            for(int x = 0 ; x < square.length; x++) {
                for (int y = 0; y < square.length; y++) {
                    setPixel(square.currentX + x, square.currentY + y, square.color);
                }
            }

        }

    }

    private void script_paint_trees(PixelWorld world) {

        for(var tree : world.getTrees()) {

            for(int x = 0 ; x < Tree.greenPartWidth; x++) {
                for (int y = 0; y < Tree.greenPartHeight; y++) {
                    setPixel(tree.currentX + x, tree.currentY + y, 0x00FF00);
                }
            }

            for(int x = 0 ; x < Tree.brownPartWidth; x++) {
                for (int y = 0; y < Tree.brownPartHeight; y++) {
                    setPixel(tree.currentX + x + ((Tree.greenPartWidth - Tree.brownPartWidth)  / 2), tree.currentY + Tree.greenPartHeight + y, 0xA52A2A);
                }
            }

        }

    }

    private void clear() {
        this.bufferedImage.setRGB(0 , 0, FRAME_WIDTH, FRAME_HEIGHT, new int[FRAME_WIDTH * FRAME_HEIGHT], 0, FRAME_WIDTH);
    }

    private void updateClock() {

        long current = System.nanoTime();

        deltaTime = (current - lastTime) / 1_000_000_000.0;

        lastTime = current;

    }

    private void setPixel(int x, int y , int rgb) {
        this.bufferedImage.setRGB(x, y, rgb);
    }

    private void draw() {

        Graphics graphics = this.frame.getGraphics();

        if(graphics == null) return;

        graphics.drawImage(this.bufferedImage, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);

        graphics.dispose();

    }

    private JFrame createJframe() {

        JFrame frame = new JFrame("Main Panel");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setUndecorated(true);
        frame.setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setLocation(
                new Point(
                        (screenSize.width / 2) - (FRAME_WIDTH / 2),
                        (screenSize.height / 2) - (FRAME_HEIGHT / 2)
                )
        );

        frame.pack();
        frame.setVisible(true);

        return frame;

    }

}