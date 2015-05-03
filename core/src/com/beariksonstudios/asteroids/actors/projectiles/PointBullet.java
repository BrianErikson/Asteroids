package com.beariksonstudios.asteroids.actors.projectiles;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.beariksonstudios.asteroids.core.AActor;

/**
 * Created by Brian on 5/2/2015.
 */
public class PointBullet extends AActor {
    public PointBullet() {
        super();
    }

    @Override
    public void draw(ShapeRenderer renderer) {
        Vector2 pos = getPosition();
        renderer.point(pos.x, pos.y, 0);
    }
}
