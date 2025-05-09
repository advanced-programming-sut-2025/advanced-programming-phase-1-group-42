package org.example.models.interactions;

import org.example.models.game_structure.Coordinate;

public abstract class Building {
    protected Coordinate startCoordinate;
    protected Coordinate endCoordinate;
    protected Coordinate size;

    public Coordinate getStartCordinate() {
        return startCoordinate;
    }

    public void setStartCordinate(Coordinate startCoordinate) {
        this.startCoordinate = startCoordinate;
    }

    public Coordinate getEndCordinate() {
        return endCoordinate;
    }

    public void setEndCordinate(Coordinate endCoordinate) {
        this.endCoordinate = endCoordinate;
    }

    public Coordinate getSize() {
        return size;
    }

    public void setSize(Coordinate size) {
        this.size = size;
    }

    public boolean isInsideBuilding(Coordinate coordinate) {
        if(coordinate.getX() >= this.startCoordinate.getX() && coordinate.getY() >= this.startCoordinate.getY() &&
        coordinate.getX() <= this.endCoordinate.getX() && coordinate.getY() <= this.endCoordinate.getY())
            return true;
        return false;
    }
}
