package org.saadmeddiche.pixelworld.actions.square;

import org.saadmeddiche.pixelworld.SimulationEngine;
import org.saadmeddiche.pixelworld.square.Square;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SquareMove extends SquareAction {

    private final Axis in;
    private int destination;

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

        boolean towardPositive = exactGetter.get() < destination;
        boolean towardNegative = exactGetter.get() > destination;

        // exist if already reached the borders
        if(towardPositive && exactGetter.get() == maxDestination) return;
        if(towardNegative && exactGetter.get() == minDestination) return;

        // if destination is outside the border, update it to match the border position
        if(towardNegative && destination < minDestination) destination = minDestination;
        if(towardPositive && destination > maxDestination) destination = maxDestination;

        double moveBy = square.speed * SimulationEngine.deltaTime;
        double nextPoint = towardPositive
                ? exactGetter.get() + moveBy
                : exactGetter.get() - moveBy;

        if (towardNegative && nextPoint <= destination) {
            currentSetter.accept(destination);
            exactSetter.accept((double) destination);
            return;
        }

        if (towardPositive && nextPoint + square.length >= destination) {
            currentSetter.accept(destination);
            exactSetter.accept((double) destination);
            return;
        }

        exactSetter.accept(nextPoint);
        currentSetter.accept((int) Math.round(nextPoint));

        square.actions.add(this);

    }

}