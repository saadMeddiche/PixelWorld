package org.saadmeddiche.pixelworld.square;

import org.saadmeddiche.pixelworld.actions.square.SquareAction;
import org.saadmeddiche.pixelworld.actions.square.SquareMove;
import org.saadmeddiche.pixelworld.world.SpawnPoint;
import org.saadmeddiche.pixelworld.world.PixelWorld;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Square {

    public int currentX;
    public int currentY;
    public final PixelWorld world;

    public final String name;
    public final int length;
    public final int color;
    public final Queue<SquareAction> actions = new ConcurrentLinkedDeque<>();

    public Square(String name, SpawnPoint spawn, int length, int color) {

        this.currentX = spawn.x;
        this.currentY = spawn.y;
        this.world = spawn.world;

        this.name = name;
        this.length = length;
        this.color = color;

    }

    public void up() {
        moveBy(0, -1);
    }

    public void down() {
        moveBy(0, 1);
    }

    public void right() {
        moveBy(1, 0);
    }

    public void left() {
        moveBy(-1, 0);
    }

    public void moveBy(int dx, int dy) {
        this.actions.add(new SquareMove(this, dx, dy));
    }

}