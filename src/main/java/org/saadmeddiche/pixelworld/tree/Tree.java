package org.saadmeddiche.pixelworld.tree;

import org.saadmeddiche.pixelworld.world.PixelWorld;
import org.saadmeddiche.pixelworld.world.SpawnPoint;

public class Tree {

    public final int currentX;
    public final int currentY;
    public final PixelWorld world;

    private final static int SCALE = 2;
    public static final int greenPartWidth = 5 * SCALE;
    public static final int greenPartHeight = 4 * SCALE;

    public static final int brownPartWidth = 4 * SCALE;
    public static final int brownPartHeight = 2 * SCALE;


    public Tree(SpawnPoint spawn) {
        this.currentX = spawn.x;
        this.currentY = spawn.y;
        this.world = spawn.world;
    }

}