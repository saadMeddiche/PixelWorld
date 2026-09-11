package org.saadmeddiche.pixelworld;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.saadmeddiche.pixelworld.actions.world.SquareCreation;
import org.saadmeddiche.pixelworld.actions.world.TreesCreation;
import org.saadmeddiche.pixelworld.actions.world.WorldAction;
import org.saadmeddiche.pixelworld.square.SquareController;
import org.saadmeddiche.pixelworld.util.TimeSpentUtil;
import org.saadmeddiche.pixelworld.world.PixelWorld;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SimulationEngine {

    private long TICK_COUNTER =  0;
    private final static int TICK_RATE_TIME_IN_MILLIS = 15;
    private final static double TICK_RATE_TIME_IN_SECONDS = TICK_RATE_TIME_IN_MILLIS / 1e3;

    public static volatile double deltaTime = 0;
    public static volatile double accumulatedDeltaTime = 0;
    private static volatile long lastTime = System.nanoTime();

    private final PixelWorld mainWorld;
    private final RenderEngine renderEngine;

    @PostConstruct
    public void init() {

        this.mainWorld.actions.add(
                new SquareCreation("#1", mainWorld.width / 2 , mainWorld.height / 2,
                        0xFF0000, 10, 100, mainWorld,
                        square -> new SquareController(renderEngine.getFrame() , square)
                )
        );

        this.mainWorld.actions.add(
                new SquareCreation("#2", 0 , 0,
                        0x00FF00, 2, 100, mainWorld,
                        square -> {
                    square.moveToX(400);
                    square.moveToY(400);
                    square.moveToX(0);
                    square.moveToY(0);
                })
        );

        this.mainWorld.actions.add(new TreesCreation(mainWorld, new int[][]{{0,0},{0,400},{100,100}}, renderEngine::bg_script_paint_trees));

    }

    @Scheduled(fixedRate = TICK_RATE_TIME_IN_MILLIS)
    public void run() {

        double spentTimeInSeconds = TimeSpentUtil.inSeconds(this::main_script);

        if(spentTimeInSeconds > TICK_RATE_TIME_IN_SECONDS) {
            log.warn(AnsiOutput.toString(AnsiColor.RED, "Delay in Tick #{} Delta #{} Spent#{}"), TICK_COUNTER, deltaTime, spentTimeInSeconds);
        }

    }

    private void main_script() {

        accumulatedDeltaTime += deltaTime;

        log.info(AnsiOutput.toString(AnsiColor.MAGENTA, "Tick #{} delta #{}"), TICK_COUNTER++, deltaTime);

        updateClock();

        script_run_world_actions(mainWorld);

        script_run_square_actions(mainWorld);

    }

    private void script_run_world_actions(PixelWorld world) {

        if(world.actions.isEmpty()) return;

        WorldAction worldAction;
        while ((worldAction = world.actions.poll()) != null) {
            log.info("Launching world action {}", worldAction.toString());
            worldAction.execute();
        }

    }

    private void script_run_square_actions(PixelWorld world) {

        for(var square : world.getSquares()) {

            if(square.actions.isEmpty()) continue;

            square.actions.poll().execute();

        }

    }

    private void updateClock() {

        long current = System.nanoTime();

        deltaTime = (current - lastTime) / 1_000_000_000.0;

        lastTime = current;

    }

}