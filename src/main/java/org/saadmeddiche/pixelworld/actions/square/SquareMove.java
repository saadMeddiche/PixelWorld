package org.saadmeddiche.pixelworld.actions.square;

import org.saadmeddiche.pixelworld.SimulationEngine;
import org.saadmeddiche.pixelworld.square.Square;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SquareMove extends SquareAction {

    private final Axis in;
    private final int destination;

    public enum Axis {
        X,
        Y
    }

    public SquareMove(Square square, Axis in, int destination) {
        super(square);
        this.in = in;
        this.destination = destination;
    }

    public void execute() {

        if(Axis.X.equals(in)) {
            execution(
                    square::setCurrentX, square::setExactX,
                    square::getCurrentX, square::getExactX,
                    0,square.world.width - square.length
            );
        }

        if(Axis.Y.equals(in)) {
            execution(
                    square::setCurrentY, square::setExactY,
                    square::getCurrentY, square::getExactY,
                    0,square.world.height - square.length
            );
        }

    }

    private void execution(Consumer<Integer> currentSetter, Consumer<Double> exactSetter,
                           Supplier<Integer> currentGetter, Supplier<Double> exactGetter,
                           int minDestination, int maxDestination) {

        if (destination < minDestination || destination > maxDestination) return;

        if(destination <= currentGetter.get() ) {
            currentSetter.accept(destination);
            exactSetter.accept((double) destination);
            return;
        }

        double moveByX = square.speed * SimulationEngine.deltaTime;

        if (exactGetter.get() + moveByX < minDestination) return;
        if (exactGetter.get() + moveByX + square.length > maxDestination) return;

        exactSetter.accept(exactGetter.get() + moveByX);
        currentSetter.accept((int) Math.round(exactGetter.get()));

        if(currentGetter.get() >= destination || exactGetter.get() >= destination) {
            currentSetter.accept(destination);
            exactSetter.accept((double) destination);
            return;
        }

        square.actions.add(this);

    }

}