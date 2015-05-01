package com.beariksonstudios.asteroids.models;

import com.beariksonstudios.asteroids.math.primitives.Line;
import java.util.ArrayList;

/**
 * Created by Brian on 5/1/2015.
 */
public class ShapeModel {
    private float lineThickness;
    // Pair of Vector2's forming a line in local coordinates
    private ArrayList<Line> lines;

    public ShapeModel() {
        lineThickness = 1.0f;
        lines = new ArrayList<Line>();
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }

    public float getLineThickness() {
        return lineThickness;
    }

    public void setLineThickness(float lineThickness) {
        this.lineThickness = lineThickness;
    }
}
