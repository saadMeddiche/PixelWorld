package org.saadmeddiche.pixelworld.actions.square;

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

        if(square.currentX + x < 0) return; // reached left border
        if(square.currentX + x + square.length > square.world.width) return; // reached right border

        if(square.currentY + y < 0) return; // reached up border
        if(square.currentY + y + square.length > square.world.height) return; // reached down border


        square.currentX += x;
        square.currentY += y;

    }

}