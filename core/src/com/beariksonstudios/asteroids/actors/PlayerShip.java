package com.beariksonstudios.asteroids.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.beariksonstudios.asteroids.models.BasicShipModel;

/**
 * Created by Brian on 5/1/2015.
 */
public class PlayerShip extends ShapeActor {

    public PlayerShip(ShapeRenderer shapeRenderer) {
        super(shapeRenderer);

        this.setModel(new BasicShipModel());
    }
}
