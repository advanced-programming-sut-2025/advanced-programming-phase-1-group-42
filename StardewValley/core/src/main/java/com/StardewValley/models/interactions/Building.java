package com.StardewValley.models.interactions;

import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Tile;

import java.util.ArrayList;

public abstract class Building {
    protected Coordinate startCoordinate;
    protected Coordinate endCoordinate;
    protected ArrayList<Tile> tiles;
    private String name;


    public Building(Coordinate startCoordinate, Coordinate endCoordinate, ArrayList<Tile> tiles, String name) {
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
        this.tiles = tiles;
        this.name = name;
    }

    public Building(Coordinate startCoordinate, Coordinate endCoordinate) {
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
    }

    public String getName() {
        return name;
    }

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

    public boolean isInsideBuilding(Coordinate coordinate) {
        if(coordinate.getX() >= this.startCoordinate.getX() && coordinate.getY() >= this.startCoordinate.getY() &&
        coordinate.getX() <= this.endCoordinate.getX() && coordinate.getY() <= this.endCoordinate.getY())
            return true;
        return false;
    }

}
