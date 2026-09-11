package org.saadmeddiche.pixelworld.actions.world;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.saadmeddiche.pixelworld.world.PixelWorld;

@Slf4j
@ToString
public class TreesCreation extends WorldAction {

    private final int[][] treesSpawnPoints;
    private @ToString.Exclude final Runnable afterTreesCreation;

    public TreesCreation(PixelWorld pixelWorld, int[][] treesSpawnPoints) {
        this(pixelWorld, treesSpawnPoints, null);
    }

    public TreesCreation(PixelWorld pixelWorld, int[][] treesSpawnPoints, Runnable afterTreesCreation) {
        super(pixelWorld);
        this.treesSpawnPoints = treesSpawnPoints;
        this.afterTreesCreation = afterTreesCreation;
    }

    @Override
    public void execute() {

        for (int[] treeSpawnPoint : treesSpawnPoints) {
            new TreeCreation(pixelWorld, treeSpawnPoint[0], treeSpawnPoint[1]).execute();
        }

        if(afterTreesCreation != null)
            this.afterTreesCreation.run();

    }

}