package org.saadmeddiche.pixelworld.actions.square;

import org.saadmeddiche.pixelworld.square.Square;
import org.saadmeddiche.pixelworld.actions.Action;

public abstract class SquareAction implements Action {

    protected final Square square;

    public SquareAction(Square square) {

        assert square != null : "null square";

        this.square = square;

    }

}