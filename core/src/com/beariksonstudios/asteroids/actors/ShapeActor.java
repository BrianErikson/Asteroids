package com.beariksonstudios.asteroids.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.beariksonstudios.asteroids.math.primitives.Line;
import com.beariksonstudios.asteroids.models.ShapeModel;

import java.util.ArrayList;

/**
 * Created by Brian on 5/1/2015.
 */
public class ShapeActor extends Actor {
    private ShapeRenderer sr;
    private ShapeModel model;
    private float scale;

    public ShapeActor(ShapeRenderer shapeRenderer) {
        sr = shapeRenderer;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        ArrayList<Line> lines = model.getLines();

        Gdx.gl.glLineWidth(model.getLineThickness());

        sr.begin(ShapeRenderer.ShapeType.Filled);
        for (Line line : lines) {
            float x1 = line.p1.x + getX() * this.getScaleX();
            float x2 = line.p2.x + getX() * this.getScaleX();
            float y1 = line.p1.y + getY() * this.getScaleY();
            float y2 = line.p1.y + getY() * this.getScaleY();

            sr.line(x1, y1, x2, y2);
        }
        sr.end();
    }

    public void setModel(ShapeModel model) {
        this.model = model;
    }
}
