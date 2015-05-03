package com.beariksonstudios.asteroids.math.primitives;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Brian on 5/1/2015.
 */
public class Line {
    public Vector2 p1;
    public Vector2 p2;

    public Line(Vector2 p1, Vector2 p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line(float x1, float y1, float x2, float y2) {
        this.p1 = new Vector2(x1, y1);
        this.p2 = new Vector2(x2, y2);
    }
}
