package com.beariksonstudios.asteroids.models;

import com.badlogic.gdx.math.Vector2;
import com.beariksonstudios.asteroids.math.primitives.Line;

import java.util.ArrayList;

/**
 * Created by Brian on 5/1/2015.
 */
public class BasicShipModel extends ShapeModel {


    public BasicShipModel() {
        addLine(new Line(new Vector2(-0.5f, -1f), new Vector2(0.0f, 1.0f)));
        addLine(new Line(new Vector2(0.0f, 1.0f), new Vector2(0.5f, -1f)));
        addLine(new Line(new Vector2(0.5f, -1f), new Vector2(0.0f, -0.5f)));
        addLine(new Line(new Vector2(0.0f, -0.5f), new Vector2(-0.5f, -1f)));
    }
}
