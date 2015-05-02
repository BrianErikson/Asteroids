package com.beariksonstudios.asteroids.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.beariksonstudios.asteroids.actors.projectiles.PointBullet;
import com.beariksonstudios.asteroids.models.BasicShipModel;

/**
 * Created by Brian on 5/1/2015.
 */
public class PlayerShip extends ShapeActor {

    private float accelRate;
    private float dtSinceLastShot;
    private float shootDelay;

    public PlayerShip(float accelRate) {
        super();

        this.accelRate = accelRate;
        this.setModel(new BasicShipModel());
        
        dtSinceLastShot = 0f;
        shootDelay = 0.2f;
    }

    private void setThrust(float amt) {
        Vector2 vec = new Vector2(0, amt);
        vec.rotate(this.getRotation());
        this.setVelocity(vec);
    }

    private void addThrust(float amt) {
        Vector2 vec = new Vector2(0, amt);
        vec.rotate(this.getRotation());
        this.setVelocity(this.getVelocity().add(vec));
    }

    public void accelerate() {
        addThrust(accelRate * Gdx.graphics.getDeltaTime());
    }

    public void decelerate() {
        addThrust(-accelRate * Gdx.graphics.getDeltaTime());
    }

    public Vector2 getNosePosition() {
        return this.getPosition();
    }

    public void shoot() {
        if (dtSinceLastShot >= shootDelay) {
            dtSinceLastShot = Gdx.graphics.getDeltaTime();
            
            PointBullet bull = new PointBullet();
            bull.setPosition(getNosePosition());

            Vector2 vec = new Vector2(0f, 100f);
            vec.rotate(this.getRotation());
            bull.setVelocity(this.getVelocity().cpy().add(vec));

            getStage().addActor(bull);
        } 
        else dtSinceLastShot += Gdx.graphics.getDeltaTime();
    }
}
