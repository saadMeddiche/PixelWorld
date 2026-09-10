package org.saadmeddiche.pixelworld.actions.square;

import org.saadmeddiche.pixelworld.SimulationEngine;
import org.saadmeddiche.pixelworld.square.Square;

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

            if (destination < 0 || destination >= square.world.width) return;

            if(destination <= square.currentX ) {
                square.currentX = destination;
                square.exactX = destination;
                return;
            }

            double moveByX = square.speed * SimulationEngine.deltaTime;

            if (square.exactX + moveByX < 0) return;
            if (square.exactX + moveByX + square.length > square.world.width) return;

            square.exactX += moveByX;

            square.currentX = (int) Math.round(square.exactX);

            if(square.currentX >= destination || square.exactX >= destination) {
                square.currentX = destination;
                square.exactX = destination;
                return;
            }

        }

        if(Axis.Y.equals(in)) {

            if (destination < 0 || destination >= square.world.height) return;

            if(destination <= square.currentY ) {
                square.currentY = destination;
                square.exactY = destination;
                return;
            }

            double moveByY = square.speed * SimulationEngine.deltaTime;

            if (square.exactY + moveByY < 0) return;
            if (square.exactY + moveByY + square.length > square.world.height) return;

            square.exactY += moveByY;

            square.currentY = (int) Math.round(square.exactY);

            if(square.currentY >= destination || square.exactY >= destination) {
                square.currentY = destination;
                square.exactY = destination;
                return;
            }

        }

        square.actions.add(this);

    }


}