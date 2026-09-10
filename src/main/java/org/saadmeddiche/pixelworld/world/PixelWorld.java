package org.saadmeddiche.pixelworld.world;

import lombok.Getter;
import org.saadmeddiche.pixelworld.square.Square;
import org.saadmeddiche.pixelworld.actions.world.WorldAction;
import org.saadmeddiche.pixelworld.tree.Tree;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class PixelWorld {

    public final int width;
    public final int height;
    public final String name;
    private @Getter final List<Tree> trees = new ArrayList<>();
    private @Getter final List<Square> squares = new ArrayList<>();
    private final Map<String, Square> squaresMap = new HashMap<>();
    public final Queue<WorldAction> actions = new ConcurrentLinkedDeque<>();

    public PixelWorld(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public void addTree(Tree tree) {

        if(tree == null) return;

        trees.add(tree);

    }

    public void addSquare(Square square) {

        if(square == null) return;

        squaresMap.put(square.name, square);

        squares.add(square);

    }

    public Optional<Square> getSquare(String name) {

        if(name == null) return Optional.empty();

        return Optional.ofNullable(squaresMap.get(name));

    }

}