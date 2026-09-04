package org.saadmeddiche.pixelworld.actions.world;

import lombok.extern.slf4j.Slf4j;
import org.saadmeddiche.pixelworld.world.PixelWorld;
import org.saadmeddiche.pixelworld.square.Square;
import org.saadmeddiche.pixelworld.world.SpawnPoint;
import org.saadmeddiche.pixelworld.world.SpawnPointOutsideWorldException;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;

@Slf4j
public class SquareCreation extends WorldAction {

    private final String squareName;
    private final int spawnX;
    private final int spawnY;
    private final int squareColor;
    private final int squareLength;

    public SquareCreation(String squareName, int spawnX, int spawnY, int squareColor, int squareLength, PixelWorld world) {
        super(world);
        this.squareName = squareName;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.squareColor = squareColor;
        this.squareLength = squareLength;
    }

    public void execute() {

        try {

            Square square = new Square(squareName, new SpawnPoint(spawnX, spawnY, pixelWorld), squareLength, squareColor);

            pixelWorld.addSquare(square);

        }
        catch (SpawnPointOutsideWorldException e) {
            log.warn(AnsiOutput.toString(AnsiColor.YELLOW , "Attempt to create square [{}] in point x:{} y:{} which is outside the range of world [{}]"), squareName, spawnX, spawnY, pixelWorld.name);
        }

    }

}