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

            if(toward <= square.currentX ) {
                square.currentX = toward;
                square.exactX = toward;
                return;
            }

            double moveByX = square.speed * SimulationEngine.deltaTime;

            if (square.exactX + moveByX < 0) return;
            if (square.exactX + moveByX + square.length > square.world.width) return;

            square.exactX += moveByX;

            square.currentX = (int) Math.round(square.exactX);

            if(square.currentX >= toward || square.exactX >= toward) {
                square.currentX = toward;
                square.exactX = toward;
                return;
            }

        }

        if(MoveIn.Y.equals(in)) {

            if(toward <= square.currentY ) {
                square.currentY = toward;
                square.exactY = toward;
                return;
            }

            double moveByY = square.speed * SimulationEngine.deltaTime;

            if (square.exactY + moveByY < 0) return;
            if (square.exactY + moveByY + square.length > square.world.height) return;

            square.exactY += moveByY;

            square.currentY = (int) Math.round(square.exactY);

            if(square.currentY >= toward || square.exactY >= toward) {
                square.currentY = toward;
                square.exactY = toward;
                return;
            }

        }

        square.actions.add(this);

    }


}