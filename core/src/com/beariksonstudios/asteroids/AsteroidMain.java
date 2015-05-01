package com.beariksonstudios.asteroids;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

		player = new PlayerShip(shapeRenderer);
		player.setPosition(Gdx.graphics.getWidth() / 2.0f, Gdx.graphics.getHeight() / 2.0f);
		player.setScale(100);
		player.setLineThickness(5f);
		stage.addActor(player);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}
}
