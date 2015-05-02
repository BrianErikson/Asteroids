package com.beariksonstudios.asteroids.actors.projectiles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.beariksonstudios.asteroids.actors.ShapeActor;

/**
 * Created by Brian on 5/2/2015.
 */
public class PointBullet extends ShapeActor {
    public PointBullet() {
        super();
    }

    /**
     * Draws this actor's debug lines if {@link #getDebug()} is true.
     *
     * @param shapes
     */
    @Override
    public void drawDebug(ShapeRenderer shapes) {
        Vector2 pos = getPosition();
        shapes.point(pos.x, pos.y, 0);
    }
}
