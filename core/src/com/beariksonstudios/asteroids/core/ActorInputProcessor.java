package com.beariksonstudios.asteroids.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * Created by Brian on 5/2/2015.
 */
public class ActorInputProcessor {
    public static void handleInput(AActor actor) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) actor.forward();
        else if (Gdx.input.isKeyPressed(Input.Keys.S)) actor.backward();

        if (Gdx.input.isKeyPressed(Input.Keys.D)) actor.right();
        else if (Gdx.input.isKeyPressed(Input.Keys.A)) actor.left();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) actor.action1();
    }
}
