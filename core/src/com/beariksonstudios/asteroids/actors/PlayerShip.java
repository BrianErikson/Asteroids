package com.beariksonstudios.asteroids.actors;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.beariksonstudios.asteroids.models.BasicShipModel;

/**
 * Created by Brian on 5/1/2015.
 */
public class PlayerShip extends ShapeActor {

    public PlayerShip(ShapeRenderer shapeRenderer) {
        super(shapeRenderer);

        this.setModel(new BasicShipModel());
    }

    public void setThrust(float amt) {
        Vector2 vec = new Vector2(0, amt);
        vec.rotate(this.getRotation());
        this.setVelocity(vec);
    }
}
