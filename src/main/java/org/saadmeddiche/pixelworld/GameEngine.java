package org.saadmeddiche.pixelworld;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.saadmeddiche.pixelworld.actions.world.SquareCreation;
import org.saadmeddiche.pixelworld.actions.world.WorldAction;
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
public class GameEngine {

    private long counter =  0;
    private static int FRAME_WIDTH = 500;
    private static int FRAME_HEIGHT = 500;

    private final JFrame frame;
    private final PixelWorld mainWorld;
    private final BufferedImage bufferedImage;

    public GameEngine(PixelWorld mainWorld) {

        this.mainWorld = mainWorld;
        this.frame = createJframe();
        this.bufferedImage = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, TYPE_INT_RGB);

    }

    public JFrame createJframe() {

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

    @PostConstruct
    public void init() {

        this.mainWorld.actions.add(
                new SquareCreation("#1", mainWorld.width / 2 , mainWorld.height / 2,
                        0xFF0000, 10, mainWorld
                )
        );

        this.mainWorld.actions.add(
                new SquareCreation("#2", 0 , 0,
                        0x00FF00, 2, mainWorld
                )
        );

    }

    @Scheduled(fixedRate = 16)
    public void run() {

        log.info(AnsiOutput.toString(AnsiColor.MAGENTA, "Frame #{}"), counter++);

        clear();

        script_run_world_actions(mainWorld);

        script_run_square_actions(mainWorld);

        script_paint_squares(mainWorld);

        draw();

    }

    private void script_run_world_actions(PixelWorld world) {

        if(world.actions.isEmpty()) return;

        WorldAction worldAction;
        while ((worldAction = world.actions.poll()) != null) {

            if(worldAction instanceof SquareCreation squareCreation) {
                log.info("Launching square creation action [{}]", squareCreation.toString());
                squareCreation.execute();
            }

        }

    }

    private void script_run_square_actions(PixelWorld world) {

        for(var square : world.getSquares()) {

            if(square.actions.isEmpty()) continue;

            square.actions.poll().execute();

        }

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

    private void clear() {
        this.bufferedImage.setRGB(0 , 0, FRAME_WIDTH, FRAME_HEIGHT, new int[FRAME_WIDTH * FRAME_HEIGHT], 0, FRAME_WIDTH);
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

}