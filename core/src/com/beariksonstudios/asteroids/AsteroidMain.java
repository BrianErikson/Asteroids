package com.beariksonstudios.asteroids;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.beariksonstudios.asteroids.actors.Asteroid;
import com.beariksonstudios.asteroids.actors.PlayerShip;
import com.beariksonstudios.asteroids.core.AStage;
import com.beariksonstudios.asteroids.core.ActorInputProcessor;

public class AsteroidMain extends ApplicationAdapter {
	AStage stage;
	ShapeRenderer shapeRenderer;
	PlayerShip player;

	@Override
	public void create () {
		stage = new AStage(20f);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(stage.getCamera().combined);

		player = new PlayerShip(50f);
		player.setPosition(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f);
		player.setScale(10);
		player.setLineThickness(1f);
		stage.addActor(player);

		Asteroid asteroid = new Asteroid();
		asteroid.setPosition(Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getHeight() / 1.5f);
		asteroid.setRotationSpeed(10f);
		asteroid.setScale(20f);
		asteroid.setLineThickness(1f);
		stage.addActor(asteroid);

		Asteroid asteroid1 = new Asteroid();
		asteroid1.setPosition(Gdx.graphics.getWidth() / 3.5f, Gdx.graphics.getHeight() / 3.5f);
		asteroid1.setRotationSpeed(10f);
		asteroid1.setScale(20f);
		asteroid1.setLineThickness(1f);
		stage.addActor(asteroid1);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		ActorInputProcessor.handleInput(player);

		stage.act();
		stage.draw();
	}
}
