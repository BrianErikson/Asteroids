package com.beariksonstudios.asteroids.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
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

    private Matrix3 transform;
    private Matrix3 translation;
    private Matrix3 rotation;
    private Matrix3 scale;
    private Vector2 velocity;
    private boolean isDirty;

    public ShapeActor(ShapeRenderer shapeRenderer) {
        sr = shapeRenderer;
        velocity = new Vector2(0,0);
        transform = new Matrix3().idt();
        translation = new Matrix3().idt();
        rotation = new Matrix3().idt();
        scale = new Matrix3().idt();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Vector2 pos = new Vector2(this.getX() + velocity.x, this.getY() + velocity.y);
        this.setPosition(this.getX() + velocity.x, this.getY() + velocity.y);

        updateTransform();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        ArrayList<Line> lines = model.getLines();

        Gdx.gl.glLineWidth(model.getLineThickness());

        sr.begin(ShapeRenderer.ShapeType.Filled);
        Vector2 pos = this.getPosition();
        for (Line line : lines) {
            Vector2 p1 = line.p1.cpy().mul(transform);
            Vector2 p2 = line.p2.cpy().mul(transform);

            sr.line(p1.x, p1.y, p2.x, p2.y);
        }
        sr.end();
    }

    public void setModel(ShapeModel model) {
        this.model = model;
    }

    public void setLineThickness(float num) {
        model.setLineThickness(num);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(float x, float y) {
        this.velocity.x = x;
        this.velocity.y = y;
    }

    private void updateTransform() {
        if (isDirty) {
            transform.idt();
            transform.mul(translation);
            transform.mul(rotation);
            transform.mul(scale);
            //System.out.println(transform);
            isDirty = false;
        }
    }

    public Vector2 getPosition() {
        return translation.getTranslation(new Vector2());
    }

    /**
     * Returns the X position of the actor's left edge.
     */
    @Override
    public float getX() {
        return translation.getTranslation(new Vector2(0,0)).x;
    }

    @Override
    public void setX(float x) {
        translation.setToTranslation(x, getX());
        isDirty = true;
    }

    /**
     * Returns the Y position of the actor's bottom edge.
     */
    @Override
    public float getY() {
        return translation.getTranslation(new Vector2(0,0)).y;
    }

    @Override
    public void setY(float y) {
        translation.setToTranslation(getX(), y);
        isDirty = true;
    }

    @Override
    public void setPosition(float x, float y) {
        translation.setToTranslation(x, y);
        isDirty = true;
    }

    @Override
    public float getScaleX() {
        return scale.getScale(new Vector2(0,0)).x;
    }

    @Override
    public void setScaleX(float scaleX) {
        scale.setToScaling(scaleX, getScaleY());
        isDirty = true;
    }

    @Override
    public float getScaleY() {
        return scale.getScale(new Vector2(0,0)).y;
    }

    @Override
    public void setScaleY(float scaleY) {
        scale.setToScaling(getScaleX(), scaleY);
        isDirty = true;
    }

    @Override
    public void setScale(float scaleXY) {
        scale.setToScaling(scaleXY, scaleXY);
        isDirty = true;
    }

    @Override
    public void setScale(float scaleX, float scaleY) {
        scale.setToScaling(scaleX, scaleY);
        isDirty = true;
    }

    /**
     * Adds the specified scale to the current scale.
     *
     * @param scale
     */
    @Override
    public void scaleBy(float scale) {
        this.scale.setToScaling(getScaleX() + scale, getScaleY() + scale);
        isDirty = true;
    }

    @Override
    public float getRotation() {
        return rotation.getRotation();
    }

    @Override
    public void setRotation(float degrees) {
        rotation.setToRotation(degrees);
        isDirty = true;
    }

    /**
     * Adds the specified rotation to the current rotation.
     *
     * @param amountInDegrees
     */
    @Override
    public void rotateBy(float amountInDegrees) {
        rotation.setToRotation(rotation.getRotation() + amountInDegrees);
        isDirty = true;
    }
}
