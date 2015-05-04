package com.beariksonstudios.asteroids.models;

import com.badlogic.gdx.math.Vector2;
import com.beariksonstudios.asteroids.math.primitives.Line;

/**
 * Created by Brian on 5/3/2015.
 */
public class AsteroidModel extends ShapeModel {
    private final float pointResolution;
    private final float edgeNoise;

    public AsteroidModel(float pointResolution, float edgeNoise) {
        this.pointResolution = pointResolution;
        this.edgeNoise = edgeNoise;
        float deg = 360f / pointResolution;
        float curDeg = 0f;

        Vector2 r = new Vector2(0f, 1.0f);
        Vector2 p1 = r.cpy();

        for (int i = 0; i < pointResolution - 1; i++) {
            Vector2 p2 = changeLength(curDeg + deg);
            addLine(p1.x, p1.y, p2.x, p2.y);
            p1 = p2;
            curDeg += deg;
        }
        addLine(p1.x, p1.y, r.x, r.y);
    }

    public Vector2 changeLength(float curDeg) {
        double length = 1 - Math.random() * edgeNoise;
        Vector2 v = new Vector2(0.0f, (float)length);
        v.rotate(curDeg);
        return v;
    }
}
