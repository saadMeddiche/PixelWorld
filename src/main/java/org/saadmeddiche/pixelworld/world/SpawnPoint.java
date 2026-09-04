package org.saadmeddiche.pixelworld.world;

public class SpawnPoint {

    public final int x;
    public final int y;
    public final PixelWorld world;

    public SpawnPoint(int x, int y, PixelWorld world) throws SpawnPointOutsideWorldException {

        if (x < 0 || x >= world.width) {
            throw new SpawnPointOutsideWorldException("x not in world range");
        }

        if (y < 0 || y >= world.height) {
            throw new SpawnPointOutsideWorldException("y not in world range");
        }

        this.x = x;
        this.y = y;
        this.world = world;

    }

}