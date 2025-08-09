package com.StardewValley.models.interactions;

import com.StardewValley.models.game_structure.Coordinate;
import com.StardewValley.models.game_structure.Tile;

import java.util.ArrayList;

public class GreenHouse extends Building {
    private Coordinate size;
    private boolean isAvailable;

    public GreenHouse(Coordinate startCoordinate, Coordinate endCoordinate, ArrayList<Tile> tiles) {
        super(startCoordinate, endCoordinate);
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
