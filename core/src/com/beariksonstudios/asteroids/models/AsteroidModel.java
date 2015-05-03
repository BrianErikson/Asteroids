package com.beariksonstudios.asteroids.models;

import com.beariksonstudios.asteroids.math.primitives.Line;

/**
 * Created by Brian on 5/3/2015.
 */
public class AsteroidModel extends ShapeModel {

    public AsteroidModel() {
        addLine(0f, 1f, 0.5f, 0f);
        addLine(0.5f, 0f, 0f, -1f);
        addLine(0f, -1f, -0.5f, 0f);
        addLine(-0.5f, 0f, 0f, 1f);
    }
}
