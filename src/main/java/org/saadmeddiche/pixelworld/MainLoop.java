package org.saadmeddiche.pixelworld;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MainLoop {

    private long counter =  0;

    private final PixelWorld mainWorld;
    private final PixelWorld secondaryWorld;

    @PostConstruct
    public void init() {
        new Square("#1",2, 0xFF0000, mainWorld);
        new Square("#2", 0, 0, 2, 0x00FF00, mainWorld);
    }

    @Scheduled(fixedRate = 1000)
    public void run() {

        log.info(AnsiOutput.toString(AnsiColor.YELLOW, "Frame #{}"), counter++);

        script_paint_squares(mainWorld);

        mainWorld.draw();

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