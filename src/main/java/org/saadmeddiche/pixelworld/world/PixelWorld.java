package org.saadmeddiche.pixelworld.world;

import org.saadmeddiche.pixelworld.square.Square;
import org.saadmeddiche.pixelworld.actions.world.WorldAction;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class PixelWorld {

    public final int width;
    public final int height;
    public final String name;
    private final Map<String, Square> squares = new HashMap<>();
    public final Queue<WorldAction> actions = new ConcurrentLinkedDeque<>();

    public PixelWorld(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public void addSquare(Square square) {

        if(square == null) return;

        squares.put(square.name, square);

    }

    public Collection<Square> getSquares() {

        return squares.values();

    }

    public Optional<Square> getSquare(String name) {

        if(name == null) return Optional.empty();

        return Optional.ofNullable(squares.get(name));

    }

}