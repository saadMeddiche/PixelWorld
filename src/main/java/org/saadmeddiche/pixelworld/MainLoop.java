package org.saadmeddiche.pixelworld;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.awt.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainLoop {

    private long counter =  0;

    private final PixelWorld mainWorld;
    private final PixelWorld secondaryWorld;


    @PostConstruct
    public void init() {

        var square1 = new Square("#1",2, 0xFF0000, mainWorld);
        var square2 = new Square("#2", 0, 0, 2, 0x00FF00, mainWorld);

        new LiveSquare(square1);

        for(int i = 0 ; i < 500 ; i++) {
            square2.actions.add(() -> square2.moveBy(1,1));
        }

    }

    @Scheduled(fixedRate = 16)
    public void run() {

        log.info(AnsiOutput.toString(AnsiColor.YELLOW, "Frame #{}"), counter++);

        script_run_square_actions(mainWorld);

        script_paint_squares(mainWorld);

        mainWorld.draw();

    }

    private void script_run_square_actions(PixelWorld world) {

        for(var square : world.squares) {

            if(square.actions.isEmpty()) continue;

            square.actions.poll().run();

        }

    }

    private void script_paint_squares(PixelWorld world) {

        for(var square : world.squares) {

            if(!square.moved()) {
                log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "Square [{}] didn't move"), square.name);
                continue;
            }

            if(!square.newBorn()) {
                log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "Cleaning square [{}] previous position"), square.name);
                for(int x = 0 ; x < square.length; x++) {
                    for (int y = 0; y < square.length; y++) {
                        mainWorld.setPixel(square.previousX + x, square.previousY + y, 0xF);
                    }
                }
            }
            else {
                log.info(AnsiOutput.toString(AnsiColor.BLUE, "Square [{}] is a new born"), square.name);
            }

            log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "Drawing square [{}] current position"), square.name);

            for(int x = 0 ; x < square.length; x++) {
                for (int y = 0; y < square.length; y++) {
                    mainWorld.setPixel(square.currentX + x, square.currentY + y, square.color);
                }
            }

        }

    }

}