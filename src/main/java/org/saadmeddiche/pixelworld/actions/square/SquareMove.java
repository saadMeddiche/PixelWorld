package org.saadmeddiche.pixelworld.actions.square;

import org.saadmeddiche.pixelworld.GameEngine;
import org.saadmeddiche.pixelworld.square.Square;

public class SquareMove extends SquareAction {

    private final int x;
    private final int y;

    public SquareMove(Square square, int x, int y) {
        super(square);
        this.x = x;
        this.y = y;
    }

    public void execute() {

        double moveX = x * square.speed * GameEngine.deltaTime;
        double moveY = y * square.speed * GameEngine.deltaTime;

        if (square.exactX + moveX < 0) return;
        if (square.exactX + moveX + square.length > square.world.width) return;

        if (square.exactY + moveY < 0) return;
        if (square.exactY + moveY + square.length > square.world.height) return;

        square.exactX += moveX;
        square.exactY += moveY;

        square.currentX = (int) Math.round(square.exactX);
        square.currentY = (int) Math.round(square.exactY);

    }

}