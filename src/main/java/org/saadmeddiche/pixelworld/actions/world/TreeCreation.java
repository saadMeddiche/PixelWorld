package org.saadmeddiche.pixelworld.actions.world;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.saadmeddiche.pixelworld.tree.Tree;
import org.saadmeddiche.pixelworld.world.PixelWorld;
import org.saadmeddiche.pixelworld.world.SpawnPoint;
import org.saadmeddiche.pixelworld.world.SpawnPointOutsideWorldException;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;

@Slf4j
@ToString
public class TreeCreation extends WorldAction {

    private final int spawnX;
    private final int spawnY;

    public TreeCreation(PixelWorld world, int spawnX, int spawnY) {
        super(world);
        this.spawnX = spawnX;
        this.spawnY = spawnY;
    }

    public void execute() {

        try {

            SpawnPoint spawnPoint = new SpawnPoint(spawnX, spawnY, pixelWorld);

            if(spawnX < 0) {
                throw new SpawnPointOutsideWorldException("tree body outside left border");
            }

            if(spawnX + Tree.greenPartWidth > pixelWorld.width || spawnX + Tree.brownPartWidth > pixelWorld.width) {
                throw new SpawnPointOutsideWorldException("tree body outside right border");
            }

            if(spawnY < 0) {
                throw new SpawnPointOutsideWorldException("tree body outside top border");
            }

            if(spawnY + Tree.greenPartHeight + Tree.brownPartHeight > pixelWorld.height) {
                throw new SpawnPointOutsideWorldException("tree body outside bottom border");
            }

            Tree tree = new Tree(spawnPoint);

            this.pixelWorld.addTree(tree);

        }
        catch (SpawnPointOutsideWorldException e) {
            log.warn(AnsiOutput.toString(AnsiColor.RED , "Attempt to create tree with in point x:{} y:{} which is outside the range of world [{}] | mess: {}"), spawnX, spawnY, pixelWorld.name, e.getMessage());
        }

    }

}