package org.example.models.game_structure;

import java.util.ArrayList;

public class Map {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private final ArrayList<Farm> farms = new ArrayList<>();
    private int mapNumber;

    // A function to print map
    public String printMap() {
        //TODO
    }

    // function to help reading the map (Maybe RGB)
    public String helpReadingMap() {
        //TODO
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }


}
