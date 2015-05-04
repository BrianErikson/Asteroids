package com.beariksonstudios.asteroids.math.physics;

import com.beariksonstudios.asteroids.core.AActor;

import java.util.ArrayList;

/**
 * Created by Brian on 5/3/2015.
 */
public class Cell {
    private final ArrayList<AActor> actors;

    private final int ID;

    public Cell(int ID) {
        this.ID = ID;
        this.actors = new ArrayList<AActor>();
    }

    public ArrayList<AActor> getActors() { return this.actors; }

    public int getID() {return ID;}

    public void addActor(AActor actor) {
        actor.setCellIndex(this.actors.size());
        actor.setCell(ID);
        this.actors.add(actor);
    }

    public void removeActor(AActor actor) {
        actors.remove(actor.getCellIndex());
    }
}
