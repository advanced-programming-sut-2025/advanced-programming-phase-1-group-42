package org.example.models.interactions;

import org.example.models.game_structure.Coordinate;
import org.example.models.game_structure.Tile;

import java.util.ArrayList;

public class GreenHouse extends Building {
    private ArrayList<Tile> tiles;
    private boolean isAvailable;

    public GreenHouse(Coordinate startCoordinate, Coordinate endCoordinate, ArrayList<Tile> tiles) {
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;
        this.tiles = tiles;
        this.size = new Coordinate(6, 5);
        this.isAvailable = false;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Functions for GreenHouse usages
}
