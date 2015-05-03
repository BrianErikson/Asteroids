package com.beariksonstudios.asteroids.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.beariksonstudios.asteroids.math.primitives.Line;
import com.beariksonstudios.asteroids.models.ShapeModel;

import java.util.ArrayList;

/**
 * Created by Brian on 5/1/2015.
 */
public class AActor {
    private AStage stage;
    private ShapeModel model;

    private Matrix3 transform;
    private Matrix3 translation;
    private Matrix3 rotation;
    private Matrix3 scale;

    private Vector2 velocity;

    // Rotation speed in degrees
    private float rotSpeed;

    private boolean isDirty;

    public AActor() {

        velocity = new Vector2(0, 0);
        rotSpeed = 0f;
        transform = new Matrix3().idt();
        translation = new Matrix3().idt();
        rotation = new Matrix3().idt();
        scale = new Matrix3().idt();
    }

    public void act(float delta) {
        float dt = Gdx.graphics.getDeltaTime();
        this.setPosition(this.getX() + (velocity.x * dt), this.getY() + (velocity.y * dt));
        this.setRotation(this.getRotation() + (rotSpeed * dt));

        updateTransform();
    }

    public void draw(ShapeRenderer renderer) {
        ArrayList<Line> lines = model.getLines();

        Gdx.gl.glLineWidth(model.getLineThickness());

        for (Line line : lines) {
            Vector2 p1 = line.p1.cpy().mul(transform);
            Vector2 p2 = line.p2.cpy().mul(transform);

            renderer.line(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public ShapeModel getModel() {
        return this.model;
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
            isDirty = false;
        }
    }

    public Vector2 getPosition() {
        return translation.getTranslation(new Vector2());
    }

    /**
     * Returns the X position of the actor's left edge.
     */

    public float getX() {
        return translation.getTranslation(new Vector2(0, 0)).x;
    }

    public void setX(float x) {
        translation.setToTranslation(x, getX());
        isDirty = true;
    }

    /**
     * Returns the Y position of the actor's bottom edge.
     */

    public float getY() {
        return translation.getTranslation(new Vector2(0, 0)).y;
    }

    public void setY(float y) {
        translation.setToTranslation(getX(), y);
        isDirty = true;
    }

    public void setPosition(float x, float y) {
        translation.setToTranslation(x, y);
        isDirty = true;
    }

    public void setPosition(Vector2 pos) {
        translation.setToTranslation(pos);
        isDirty = true;
    }

    public float getScaleX() {
        return scale.getScale(new Vector2(0, 0)).x;
    }

    public void setScaleX(float scaleX) {
        scale.setToScaling(scaleX, getScaleY());
        isDirty = true;
    }

    public float getScaleY() {
        return scale.getScale(new Vector2(0, 0)).y;
    }

    public void setScaleY(float scaleY) {
        scale.setToScaling(getScaleX(), scaleY);
        isDirty = true;
    }

    public void setScale(float scaleXY) {
        scale.setToScaling(scaleXY, scaleXY);
        isDirty = true;
    }

    public void setScale(float scaleX, float scaleY) {
        scale.setToScaling(scaleX, scaleY);
        isDirty = true;
    }

    /**
     * Adds the specified scale to the current scale.
     *
     * @param scale
     */

    public void scaleBy(float scale) {
        this.scale.setToScaling(getScaleX() + scale, getScaleY() + scale);
        isDirty = true;
    }

    public float getRotation() {
        return rotation.getRotation();
    }

    public void setRotation(float degrees) {
        rotation.setToRotation(degrees);
        isDirty = true;
    }

    /**
     * Adds the specified rotation to the current rotation.
     *
     * @param amountInDegrees
     */

    public void rotateBy(float amountInDegrees) {
        rotation.setToRotation(rotation.getRotation() + amountInDegrees);
        isDirty = true;
    }

    public Matrix3 getTransform() {
        return transform;
    }

    public Vector2 projectVec2(Vector2 vec) {
        return vec.mul(transform);
    }

    public float getRotationSpeed() {
        return rotSpeed;
    }

    public void setRotationSpeed(float rotSpeed) {
        this.rotSpeed = rotSpeed;
    }

    /*Input*/
    public void forward() {
    }

    public void backward() {
    }

    public void left() {
    }

    public void right() {
    }

    public void action1() {
    }

    public void action2() {
    }

    public AStage getStage() {
        return stage;
    }

    public void setStage(AStage stage) {
        this.stage = stage;
    }
}
