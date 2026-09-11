package org.saadmeddiche.pixelworld.square;

import lombok.Getter;
import lombok.Setter;
import org.saadmeddiche.pixelworld.actions.square.SquareAction;
import org.saadmeddiche.pixelworld.actions.square.SquareMove;
import org.saadmeddiche.pixelworld.world.SpawnPoint;
import org.saadmeddiche.pixelworld.world.PixelWorld;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Square {

    public int speed;
    public @Setter @Getter int currentX;
    public @Setter @Getter int currentY;
    public @Setter @Getter double exactX;
    public @Setter @Getter double exactY;
    public final PixelWorld world;

    public final String name;
    public final int length;
    public final int color;
    public final Queue<SquareAction> actions = new ConcurrentLinkedDeque<>();

    public Square(String name, SpawnPoint spawn, int length, int color, int speed) {

        this.speed = speed;
        this.currentX = spawn.x;
        this.currentY = spawn.y;
        this.exactX = spawn.x;
        this.exactY = spawn.y;
        this.world = spawn.world;

        this.name = name;
        this.length = length;
        this.color = color;

    }

    public void up() {
        moveToY(currentY - 1);
    }

    public void down() {
        moveToY(currentY + 1);
    }

    public void right() {
        moveToX(currentX + 1);
    }

    public void left() {
        moveToX(currentX - 1);
    }

    public void moveToX(int x) {
        this.actions.add(new SquareMove(this, SquareMove.Axis.X, x));
    }

    public void moveToY(int y) {
        this.actions.add(new SquareMove(this, SquareMove.Axis.Y, y));
    }

}