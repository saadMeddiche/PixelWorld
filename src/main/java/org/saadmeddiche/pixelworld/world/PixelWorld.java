package org.saadmeddiche.pixelworld.world;

import org.saadmeddiche.pixelworld.square.Square;
import org.saadmeddiche.pixelworld.actions.world.WorldAction;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class PixelWorld {

    public final int width;
    public final int height;
    public final String name;
    private final List<Square> squares = new ArrayList<>();
    private final Map<String, Square> squaresMap = new HashMap<>();
    public final Queue<WorldAction> actions = new ConcurrentLinkedDeque<>();

    public PixelWorld(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public void addSquare(Square square) {

        if(square == null) return;

        squaresMap.put(square.name, square);
        squares.add(square);

    }

    public List<Square> getSquares() {
        return squares;
    }

    public Optional<Square> getSquare(String name) {

        if(name == null) return Optional.empty();

        return Optional.ofNullable(squaresMap.get(name));

    }

}