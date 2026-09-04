package org.saadmeddiche.pixelworld.actions.world;

import org.saadmeddiche.pixelworld.world.PixelWorld;
import org.saadmeddiche.pixelworld.actions.Action;

public abstract class WorldAction implements Action {

    protected final PixelWorld pixelWorld;

    public WorldAction(PixelWorld pixelWorld) {

        assert pixelWorld != null : "null pixel world";

        this.pixelWorld = pixelWorld;

    }

}