package org.example.models.game_structure;

import org.example.models.interactions.game_buildings.GameBuilding;

import java.util.ArrayList;

public class Map {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<Farm> farms = new ArrayList<>();
    private final ArrayList<GameBuilding> gameBuildings = new ArrayList<>();

    // A function to print map
    public String printMap() {
        //TODO
        return "";
    }

    // function to help reading the map (Maybe RGB)
    public String helpReadingMap() {
        //TODO
        return "";
    }



    public ArrayList<Tile> getTiles() {
        return tiles;
    }


}
