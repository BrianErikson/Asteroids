package com.beariksonstudios.asteroids.core;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.beariksonstudios.asteroids.core.AActor;

import java.util.ArrayList;

/**
 * Created by Brian on 5/3/2015.
 */
public class AStage implements Disposable {
    private Viewport viewport;
    private float gutterDepth;
    private final ShapeRenderer renderer = new ShapeRenderer();
    private final ArrayList<AActor> actors;
    private boolean actionsRequestRendering = true;

    /**
     * Creates a AStage with a {@link ScalingViewport} set to {@link Scaling#stretch}. The AStage will use its own {@link ShapeRenderer}
     * which will be disposed when the AStage is disposed.
     */
    public AStage(float gutterDepth) {
        this(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera()),
                gutterDepth);
    }

    /**
     * Creates a AStage with the specified viewport and renderer. This can be used to avoid creating a new renderer (which can be somewhat
     * slow) if multiple AStages are used during an application's life time.
     */
    public AStage(Viewport viewport, float gutterDepth) {
        if (viewport == null) throw new IllegalArgumentException("viewport cannot be null.");
        this.gutterDepth = gutterDepth;
        this.viewport = viewport;

        actors = new ArrayList<AActor>();

        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    public void draw() {
        Camera camera = viewport.getCamera();
        camera.update();

        renderer.setAutoShapeType(true);
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin();
        for (AActor actor : actors) {
            actor.draw(renderer);
        }

        renderer.end();
    }

    /**
     * Calls {@link #act(float)} with {@link Graphics#getDeltaTime()}.
     */
    public void act() {
        act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
    }

    /**
     * Calls the {@link Actor#act(float)} method on each actor in the AStage. Typically called each frame. This method also fires
     * enter and exit events.
     *
     * @param delta Time in seconds since the last frame.
     */
    public void act(float delta) {
        for (AActor actor : actors) {
            Vector2 pos = actor.getPosition();
            float w = Gdx.graphics.getWidth();
            float h = Gdx.graphics.getHeight();

            if (pos.x > w + gutterDepth) actor.setPosition(-gutterDepth + 1, actor.getY());
            else if (pos.x < -gutterDepth) actor.setPosition(w + gutterDepth - 1, actor.getY());

            if (pos.y > h + gutterDepth) actor.setPosition(actor.getX(), -gutterDepth + 1);
            else if (pos.y < -gutterDepth) actor.setPosition(actor.getX(), h + gutterDepth - 1);

            actor.act(delta);
        }
    }

    /**
     * Adds an actor to the actors of the AStage.
     *
     * @see Group#addActor(Actor)
     */
    public void addActor(AActor actor) {
        actors.add(actor);
    }

    /**
     * Returns the actors's child actors.
     *
     * @see Group#getChildren()
     */
    public ArrayList<AActor> getActors() {
        return actors;
    }
    
    /**
     * Removes the actors's children, actions, and listeners.
     */
    public void clear() {
        actors.clear();
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    /**
     * The viewport's world width.
     */
    public float getWidth() {
        return viewport.getWorldWidth();
    }

    /**
     * The viewport's world height.
     */
    public float getHeight() {
        return viewport.getWorldHeight();
    }

    /**
     * The viewport's camera.
     */
    public Camera getCamera() {
        return viewport.getCamera();
    }

    /**
     * Transforms the screen coordinates to AStage coordinates.
     *
     * @param screenCoords Input screen coordinates and output for resulting AStage coordinates.
     */
    public Vector2 screenToAStageCoordinates(Vector2 screenCoords) {
        viewport.unproject(screenCoords);
        return screenCoords;
    }

    /**
     * Transforms the AStage coordinates to screen coordinates.
     *
     * @param AStageCoords Input AStage coordinatAes and output for resulting screen coordinates.
     */
    public Vector2 AStageToScreenCoordinates(Vector2 AStageCoords) {
        viewport.project(AStageCoords);
        AStageCoords.y = viewport.getScreenHeight() - AStageCoords.y;
        return AStageCoords;
    }

    /**
     * Transforms the coordinates to screen coordinates. The coordinates can be anywhere in the AStage since the transform matrix
     * describes how to convert them. The transform matrix is typically obtained from renderer.getTransformMatrix() during
     * Actor.draw()
     *
     */
    public Vector2 toScreenCoordinates(Vector2 coords, Matrix4 transformMatrix) {
        return viewport.toScreenCoordinates(coords, transformMatrix);
    }

    /**
     * Calculates window scissor coordinates from local coordinates using the renderer's current transformation matrix.
     *
     * @see ScissorStack#calculateScissors(Camera, float, float, float, float, Matrix4, Rectangle, Rectangle)
     */
    public void calculateScissors(Rectangle localRect, Rectangle scissorRect) {
        viewport.calculateScissors(renderer.getTransformMatrix(), localRect, scissorRect);
        Matrix4 transformMatrix = renderer.getTransformMatrix();
        viewport.calculateScissors(transformMatrix, localRect, scissorRect);
    }

    /**
     * If true, any actions executed during a call to {@link #act()}) will result in a call to {@link Graphics#requestRendering()}.
     * Widgets that animate or otherwise require additional rendering may check this setting before calling
     * {@link Graphics#requestRendering()}. Default is true.
     */
    public void setActionsRequestRendering(boolean actionsRequestRendering) {
        this.actionsRequestRendering = actionsRequestRendering;
    }

    public boolean getActionsRequestRendering() {
        return actionsRequestRendering;
    }

    public void dispose() {
        clear();
        renderer.dispose();
    }
}