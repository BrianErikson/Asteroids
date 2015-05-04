package com.beariksonstudios.asteroids.actors;

import com.beariksonstudios.asteroids.core.AActor;
import com.beariksonstudios.asteroids.models.AsteroidModel;

/**
 * Created by Brian on 5/3/2015.
 */
public class Asteroid extends AActor {
    public Asteroid() {
        this.setModel(new AsteroidModel(30, 0.2f));
    }
}
