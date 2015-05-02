package com.beariksonstudios.asteroids;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.beariksonstudios.asteroids.actors.PlayerShip;

public class AsteroidMain extends ApplicationAdapter {
	Stage stage;
	ShapeRenderer shapeRenderer;
	PlayerShip player;

	@Override
	public void create () {
		stage = new Stage();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(stage.getCamera().combined);

		player = new PlayerShip(50f);
		player.setPosition(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f);
		player.setScale(10);
		player.setLineThickness(1f);
		stage.addActor(player);
	}

	@Override
	public void render () {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) player.accelerate();
		else if (Gdx.input.isKeyPressed(Input.Keys.S)) player.decelerate();

		if (Gdx.input.isKeyPressed(Input.Keys.D)) player.rotateBy(-5f);
		else if (Gdx.input.isKeyPressed(Input.Keys.A)) player.rotateBy(5f);

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) player.shoot();


		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
	}
}
