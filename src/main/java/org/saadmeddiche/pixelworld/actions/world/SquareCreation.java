package org.saadmeddiche.pixelworld.actions.world;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.saadmeddiche.pixelworld.world.PixelWorld;
import org.saadmeddiche.pixelworld.square.Square;
import org.saadmeddiche.pixelworld.world.SpawnPoint;
import org.saadmeddiche.pixelworld.world.SpawnPointOutsideWorldException;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;

import java.util.function.Consumer;

@Slf4j
@ToString
public class SquareCreation extends WorldAction {

    private final String squareName;
    private final int spawnX;
    private final int spawnY;
    private final int squareColor;
    private final int squareLength;
    private @ToString.Exclude final Consumer<Square> onCreated;

    public SquareCreation(String squareName, int spawnX, int spawnY, int squareColor, int squareLength, PixelWorld world) {
        this(squareName, spawnX, spawnY, squareColor, squareLength, world, null);
    }

    public SquareCreation(String squareName, int spawnX, int spawnY, int squareColor, int squareLength, PixelWorld world, Consumer<Square> onCreated) {
        super(world);
        this.squareName = squareName;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.squareColor = squareColor;
        this.squareLength = squareLength;
        this.onCreated = onCreated;
    }

    public void execute() {

        try {

            SpawnPoint spawnPoint = new SpawnPoint(spawnX, spawnY, pixelWorld);

            if(spawnX < 0) {
                throw new SpawnPointOutsideWorldException("square body outside left border");
            }

            if(spawnX + squareLength > pixelWorld.width) {
                throw new SpawnPointOutsideWorldException("square body outside right border");
            }

            if(spawnY < 0) {
                throw new SpawnPointOutsideWorldException("square body outside top border");
            }

            if(spawnY + squareLength > pixelWorld.height) {
                throw new SpawnPointOutsideWorldException("square body outside bottom border");
            }

            Square square = new Square(squareName, spawnPoint, squareLength, squareColor);

            pixelWorld.addSquare(square);

            if(onCreated != null) {
                onCreated.accept(square);
            }

        }
        catch (SpawnPointOutsideWorldException e) {
            log.warn(AnsiOutput.toString(AnsiColor.RED , "Attempt to create square with name:{} and length:{} in point x:{} y:{} which is outside the range of world [{}] | mess: {}"), squareName, squareLength, spawnX, spawnY, pixelWorld.name, e.getMessage());
        }

    }

}