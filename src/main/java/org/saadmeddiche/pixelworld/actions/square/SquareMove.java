package org.saadmeddiche.pixelworld.actions.square;

import org.saadmeddiche.pixelworld.SimulationEngine;
import org.saadmeddiche.pixelworld.square.Square;

public class SquareMove extends SquareAction {

    private final MoveIn in;
    private final int toward;

    public enum MoveIn {
        X,
        Y
    }

    public SquareMove(Square square, MoveIn in, int toward) {
        super(square);
        this.in = in;
        this.toward = toward;
    }

    public void execute() {

        if(MoveIn.X.equals(in)) {

            double moveX = toward * square.speed * SimulationEngine.deltaTime;

            if (square.exactX + moveX < 0) return;
            if (square.exactX + moveX + square.length > square.world.width) return;

            square.exactX += moveX;

            square.currentX = (int) Math.round(square.exactX);

        }

        if(MoveIn.Y.equals(in)) {

            double moveY = toward * square.speed * SimulationEngine.deltaTime;

            if (square.exactY + moveY < 0) return;
            if (square.exactY + moveY + square.length > square.world.height) return;

            square.exactY += moveY;

            square.currentY = (int) Math.round(square.exactY);

        }

    }


}