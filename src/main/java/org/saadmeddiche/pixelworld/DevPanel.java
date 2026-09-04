package org.saadmeddiche.pixelworld;

import lombok.extern.slf4j.Slf4j;
import org.saadmeddiche.pixelworld.world.PixelWorld;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Slf4j
@Component
public class DevPanel {

    private final static int FRAME_WIDTH  = 500;
    private final static int FRAME_HEIGHT = 500;

    private final JFrame frame;
    private final PixelWorld mainWorld;

    public DevPanel(PixelWorld mainWorld) {
        this.mainWorld = mainWorld;
        this.frame = createJframe();
    }

    @Scheduled(fixedRate = 100)
    public void run() {

        Graphics graphics = frame.getGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0 , 0, FRAME_WIDTH, FRAME_HEIGHT);

        graphics.setColor(Color.GREEN);
        graphics.setFont(new Font("Consolas", Font.BOLD, 12));

        for(int i = 0 ; i < mainWorld.getSquares().size() ; i++) {

            var square = mainWorld.getSquares().get(i);

            graphics.drawString(String.format("Square -> name:%s | x:%d | y:%d | e.x:%.2f | e.y:%.2f", square.name, square.currentX, square.currentY, square.exactX, square.exactY), 10, i * 20 + 20);

        }

        graphics.dispose();

    }

    public JFrame createJframe() {

        JFrame frame = new JFrame("Dev Panel");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setLocation(new Point(0, 0));
        frame.pack();
        frame.setVisible(true);

        return frame;

    }

}